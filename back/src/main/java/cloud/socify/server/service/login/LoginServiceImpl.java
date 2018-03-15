package cloud.socify.server.service.login;

import cloud.socify.server.dao.user.UserRepository;
import cloud.socify.server.ex.InvalidLineException;
import cloud.socify.server.ex.UsernameAlreadyTakenExeption;
import cloud.socify.server.model.User;
import cloud.socify.server.service.session.SessionManager;
import cloud.socify.server.utils.ShaCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements LoginService {

    private final static Pattern validatePattern = Pattern.compile("[^a-zA-Zа-яА-Я0-9]");

    @Autowired
    private UserRepository repository;

    @Autowired
    private SessionManager sessionManager;

    @Override
    public String login(HttpServletRequest request) throws CredentialException, InvalidLineException {
        Cookie[] cookie = request.getCookies();
        String username = getCookieValue(cookie, "username");
        String password = getCookieValue(cookie, "password");

        if (isNotValid(username) || isNotValid(password)) {
            throw new InvalidLineException();
        }

        User user = repository.getByCredentials(username, ShaCrypt.hash(password));
        if (user == null) {
            throw new CredentialException(username);
        }

        String token = request.getSession().getId();
        sessionManager.saveSession(username, token);

        return token;
    }

    @Override
    public String register(HttpServletRequest request) throws InvalidLineException, UsernameAlreadyTakenExeption {
        Cookie[] cookie = request.getCookies();
        String username = getCookieValue(cookie, "username");
        String password = getCookieValue(cookie, "password");

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
        sessionManager.saveSession(username, token);

        return token;
    }

    private boolean isNotValid(String s) {
        return s == null || validatePattern.matcher(s).find();
    }

    private String getCookieValue(Cookie[] cookies, String param) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(param)) {
                return cookie.getValue();
            }
        }

        return null;
    }


}
