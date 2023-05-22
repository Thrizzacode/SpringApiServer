package server.api.service;

import server.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.DAO.UserDao;
import server.api.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserService {
    @Autowired
   private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean userLogin(String username, String password) {
        User userVerify = userRepository.findByUsernameAndPassword(username, password);
        if (userVerify != null) {
            return userVerify.getPassword().equals(password);
        }
        return false;
    }
}