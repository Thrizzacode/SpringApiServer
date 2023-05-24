package server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.api.exception.NotFoundException;
import server.api.model.CmsUser;
import server.api.model.SpringUser;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    private CmsUserService cmsUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
           CmsUser cmsUser= cmsUserService.getUser(username);
            System.out.println(cmsUser);

           return new SpringUser(cmsUser);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
