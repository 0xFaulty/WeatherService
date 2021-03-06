package cloud.weather.server.service.login;

import cloud.weather.server.ex.InvalidLineException;
import cloud.weather.server.ex.RequestException;
import cloud.weather.server.ex.UsernameAlreadyTakenExeption;
import cloud.weather.server.model.info.InfoResponse;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    String login(HttpServletRequest request) throws CredentialException, InvalidLineException, RequestException;

    String register(HttpServletRequest request) throws InvalidLineException, UsernameAlreadyTakenExeption, RequestException;

}
