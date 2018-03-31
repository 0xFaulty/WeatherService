package cloud.weather.server.service.login;

import cloud.weather.server.dao.user.UserRepository;
import cloud.weather.server.ex.InvalidLineException;
import cloud.weather.server.ex.RequestException;
import cloud.weather.server.ex.UsernameAlreadyTakenExeption;
import cloud.weather.server.model.User;
import cloud.weather.server.service.session.SessionManager;
import cloud.weather.server.utils.JsonUtils;
import cloud.weather.server.utils.ShaCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements LoginService {

    private final static Pattern VALIDATE_PATTERN = Pattern.compile("[^a-zA-Zа-яА-Я0-9]");

    @Autowired
    private UserRepository repository;

    @Autowired
    private SessionManager sessionManager;

    @Override
    @Transactional
    public String login(HttpServletRequest request) throws CredentialException, InvalidLineException, RequestException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isNotValid(username) || isNotValid(password)) {
            throw new InvalidLineException();
        }

        User user = repository.getByCredentials(username, ShaCrypt.hash(password));
        if (user == null) {
            throw new CredentialException(username);
        }

        String token = request.getSession().getId();
        sessionManager.saveSession(token, username);

        try {
            return JsonUtils.getOneParamJson("token", token);
        } catch (JsonProcessingException e) {
            throw new RequestException("Server internal error");
        }
    }

    @Override
    @Transactional
    public String register(HttpServletRequest request) throws InvalidLineException, UsernameAlreadyTakenExeption, RequestException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isNotValid(username) || isNotValid(password)) {
            throw new InvalidLineException();
        }

        User user = repository.findByUsername(username);
        if (user != null) {
            throw new UsernameAlreadyTakenExeption(username);
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(ShaCrypt.hash(password));
        newUser.setActive(true);
        newUser.setCreatedTimestamp(new Date());
        repository.save(newUser);

        String token = request.getSession().getId();
        sessionManager.saveSession(token, username);

        try {
            return JsonUtils.getOneParamJson("token", token);
        } catch (JsonProcessingException e) {
            throw new RequestException("Server internal error");
        }
    }

    private boolean isNotValid(String s) {
        return s == null || VALIDATE_PATTERN.matcher(s).find();
    }

}
