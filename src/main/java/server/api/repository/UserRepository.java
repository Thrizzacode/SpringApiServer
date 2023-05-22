package server.api.repository;

import org.springframework.data.repository.CrudRepository;
import server.api.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
