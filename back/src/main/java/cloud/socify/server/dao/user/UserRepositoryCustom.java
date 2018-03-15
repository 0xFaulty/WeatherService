package cloud.socify.server.dao.user;

import cloud.socify.server.model.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> listUsers();

    void activateToggleUser(long id);

    User getByCredentials(String username, String password);

}
