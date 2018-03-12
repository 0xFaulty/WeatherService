package cloud.socify.server.service.login;

import cloud.socify.server.dao.user.UserRepository;
import cloud.socify.server.ex.WrongTokenException;
import cloud.socify.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginServiceImpl implements LoginService {

    private static Map<String, User> tokenMap = new ConcurrentHashMap<>();

    @Autowired
    private UserRepository repository;

    @Override
    public String getApiToken(String login, String pw) throws CredentialException {
        User user = repository.getByCredentials(login, pw);
        if (user != null) {
            // TODO: 11.03.2018 Token generation
            String token = user.hashCode() + "";
            tokenMap.put(token, user);
            return token;
        }
        throw new CredentialException(login);
    }

    @Override
    public long getUserId(String token) throws WrongTokenException {
        User user = tokenMap.get(token);
        if (user != null) {
            return user.getId();
        }
        throw new WrongTokenException(token);
    }

}
