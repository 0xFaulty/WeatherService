package cloud.socify.server.controller.v1;

import cloud.socify.server.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/token/{login}/{pw}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getApiVersion(@PathVariable("login") String login,
                                        @PathVariable("pw") String pw) throws CredentialException {
        return new ResponseEntity<>(loginService.getApiToken(login, pw), HttpStatus.OK);
    }

}
