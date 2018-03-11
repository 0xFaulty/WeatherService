package cloud.socify.server.controller.v1;

import cloud.socify.server.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/v1")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/city/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByCity(@PathVariable("city") String city) {
        return new ResponseEntity<>(weatherService.getByCity(city), HttpStatus.OK);
    }

}