package cloud.socify.server.dao.user;

import cloud.socify.server.model.User;

import java.util.List;

public interface UserRepositoryCustom {

    void addUser(User user);

    void updateUser(User user);

    void removeUser(long id);

    User getUserById(long id);

    List<User> listUsers();

    void activateToggleUser(long id);

    User getByCredentials(String username, String password);

}
