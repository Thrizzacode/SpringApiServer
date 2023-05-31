package server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.api.model.CmsUser;
import server.api.repository.UserRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Transactional
@Service
public class CmsUserService {
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public CmsUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private EntityManager em;

    public CmsUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public CmsUser addUser(CmsUser cmsUser, HttpSession session) {
        String code = cmsUser.getVerifyCode();
        String redisCode = stringRedisTemplate.opsForValue().get("verifyCode");
        if(code != null && code.equals(redisCode)){
            CmsUser dbUser = userRepository.findByUsername(cmsUser.getUsername());
            stringRedisTemplate.delete("verifyCode");
            System.out.println("dbUser: " + dbUser);
            if(dbUser != null) {
                return null;
            }
            else{
                cmsUser.setPassword(passwordEncoder.encode(cmsUser.getPassword()));
                return userRepository.save(cmsUser);
            }
        }else {
            return null;
        }
    }

    public boolean userLogin(String username, String password) {
        CmsUser userVerify = userRepository.findByUsername(username);
        if (userVerify != null) {
            String encodedPassword = userVerify.getPassword(); // 從數據庫中獲取已加密的密碼

            boolean passwordMatch = passwordEncoder.matches(password, encodedPassword);
            if (passwordMatch) {
                System.out.println("密碼驗證成功");
            } else {
                System.out.println("密碼驗證失敗");
            }
            return passwordMatch;
        }
        return false;
    }
}