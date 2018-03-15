package cloud.socify.server.controller.v1;

import cloud.socify.server.ex.InvalidLineException;
import cloud.socify.server.ex.UsernameAlreadyTakenExeption;
import cloud.socify.server.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLoginToken(HttpServletRequest request,
                                        HttpServletResponse response) throws CredentialException, InvalidLineException {

        String token = loginService.login(request);
        Cookie userName = new Cookie("token2", token);
        response.addCookie(userName);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRegisterToken(HttpServletRequest request,
                                           HttpServletResponse response) throws InvalidLineException, UsernameAlreadyTakenExeption {

        String token = loginService.register(request);
        Cookie userName = new Cookie("token2", token);
        response.addCookie(userName);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
