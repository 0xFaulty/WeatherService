package cloud.socify.server.controller.v1;

import cloud.socify.server.service.weather.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/v1")
public class WeatherController {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/city/{city}/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByCity(@PathVariable("city") String city, @PathVariable("token") String token) {
        try {
            return new ResponseEntity<>(weatherService.getByCity(city, token), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Errors while process request", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error");
        }
    }

    @GetMapping(value = "/history/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHistory(@PathVariable("token") String token) {
        try {
            return new ResponseEntity<>(weatherService.getHistory(token), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Errors while process request", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error");
        }
    }

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAvailableCities() {
        try {
            return new ResponseEntity<>(weatherService.getAvailableCities(), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Errors while process request", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error");
        }
    }

}