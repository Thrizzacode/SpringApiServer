package server.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import server.api.model.CmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@Service
public class CmsUserService {
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public CmsUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private EntityManager em;

    public CmsUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public CmsUser addUser(CmsUser cmsUser) {
        CmsUser dbUser = userRepository.findByUsername(cmsUser.getUsername());
        System.out.println("dbUser: " + dbUser);
        if(dbUser != null) {
            return null;
        }
        else{
            cmsUser.setPassword(passwordEncoder.encode(cmsUser.getPassword()));
            return userRepository.save(cmsUser);
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