package cloud.socify.server.service.login;

import cloud.socify.server.ex.WrongTokenException;

import javax.security.auth.login.CredentialException;

public interface LoginService {

    String getApiToken(String login, String pw) throws CredentialException;

    long getUserId(String token) throws WrongTokenException;

}
