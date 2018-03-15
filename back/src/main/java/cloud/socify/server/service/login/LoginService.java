package cloud.socify.server.service.login;

import cloud.socify.server.ex.InvalidLineException;
import cloud.socify.server.ex.UsernameAlreadyTakenExeption;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    String login(HttpServletRequest request) throws CredentialException, InvalidLineException;

    String register(HttpServletRequest request) throws InvalidLineException, UsernameAlreadyTakenExeption;

}
