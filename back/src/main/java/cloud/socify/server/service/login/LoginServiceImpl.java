package cloud.socify.server.service.login;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String getApiToken(String login, String pw) {
        return "token";
    }

    @Override
    public boolean isAvailableToken(String token) {
        return token.equals("token");
    }

}
