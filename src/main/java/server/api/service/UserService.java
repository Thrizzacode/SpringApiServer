package server.api.service;

import server.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.DAO.UserDao;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public void addUser(User pUser){
        userDao.addUser(pUser);
    }

//    public AppUser getUser(){
//
//        return userDao.getUser();
//    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public boolean userLogin(User user) {
        User userVerify = userDao.getUser(user.getUsername());
        return userVerify.getPassword().equals(user.getPassword());
    }
}