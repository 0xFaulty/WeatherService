package cloud.socify.server.dao.user;

import cloud.socify.server.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
    User findByUsername(String username);
}
