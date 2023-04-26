package server.api.service;

import server.api.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.DAO.UserDao;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public void addUser(AppUser pUser){
        userDao.addUser(pUser);
    }

    public AppUser getUser(){

        return userDao.getUser();
    }
}