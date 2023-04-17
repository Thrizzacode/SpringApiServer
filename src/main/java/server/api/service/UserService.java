package server.api.service;

import server.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.DAO.UserDao;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public void addUser(User pUser){
        userDao.addUser(pUser);
    }

    public Object getUser(){

        return userDao.getUser();
    }
}