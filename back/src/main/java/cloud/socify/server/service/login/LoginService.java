package cloud.socify.server.service.login;

import cloud.socify.server.ex.ChekingCredentialsFailedException;

public interface LoginService {

    String getApiToken(String login, String pw) throws ChekingCredentialsFailedException;

    boolean isAvailableToken(String token);

}
