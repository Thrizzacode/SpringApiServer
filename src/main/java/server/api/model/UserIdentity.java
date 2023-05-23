package server.api.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserIdentity {

    private final SpringUser EMPTY_USER = new SpringUser(new CmsUser());


    private SpringUser getSpringUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return principal.equals("anonymousUser") ? EMPTY_USER : (SpringUser) principal;
    }

    public boolean isAnonymous() {
        return getSpringUser().equals(EMPTY_USER);
    }

    public String getId() {
        return getSpringUser().getId();
    }

    public String getUsername() {
        return getSpringUser().getUsername();
    }
}
