package cloud.socify.server.controller;

import cloud.socify.server.ex.ChekingCredentialsFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ChekingCredentialsFailedException.class)
    public ResponseEntity handleException(ChekingCredentialsFailedException e) {
        LOG.info("Wrong credits for username:" + e.getUsername());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Wrong credits");
    }

}