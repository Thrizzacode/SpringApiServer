package server.api.repository;

import org.springframework.data.repository.CrudRepository;
import server.api.model.CmsUser;

public interface UserRepository extends CrudRepository<CmsUser, String> {

    CmsUser findByUsername(String username);

    CmsUser findByUsernameAndPassword(String username, String password);
}
