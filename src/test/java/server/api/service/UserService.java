package server.api.service;

import server.api.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
//    public void addUser(User pUser){
//        userDao.addUser(pUser);
//    }

    public Object getUser(){

        return userDao.getUser();
    }
}