package server.api.service;

import server.api.model.CmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.repository.UserRepository;

import javax.persistence.EntityManager;

@Service
public class CmsUserService {
    @Autowired
   private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    public CmsUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public CmsUser addUser(CmsUser cmsUser) {
        return userRepository.save(cmsUser);
    }

    public boolean userLogin(String username, String password) {
        CmsUser userVerify = userRepository.findByUsernameAndPassword(username, password);
        if (userVerify != null) {
            return userVerify.getPassword().equals(password);
        }
        return false;
    }
}