package server.api.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class SpringUser implements UserDetails {
    private CmsUser cmsUser;

    public SpringUser(CmsUser cmsUser){
        this.cmsUser = cmsUser;
    }

    public String getId() {
        return cmsUser.getId();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return cmsUser.getIdentity().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return cmsUser.getPassword();
    }

    @Override
    public String getUsername() {
        return cmsUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "SpringUser{" +
                "cmsUser=" + cmsUser +
                '}';
    }
}
