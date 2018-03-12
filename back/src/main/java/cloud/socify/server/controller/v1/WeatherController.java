package cloud.socify.server.controller.v1;

import cloud.socify.server.ex.UserNotFoundException;
import cloud.socify.server.service.weather.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/v1")
public class WeatherController {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/city/{city}/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByCity(@PathVariable("city") String city, @PathVariable("token") String token) {
        try {
            return new ResponseEntity<>(weatherService.addRequestByCity(city, token), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Errors while process request", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error");
        }
    }

}