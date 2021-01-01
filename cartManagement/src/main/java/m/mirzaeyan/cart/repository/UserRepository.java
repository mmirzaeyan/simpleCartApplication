package m.mirzaeyan.cart.repository;

import m.mirzaeyan.cart.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, String> {

    User findByUserName(String username);
}
