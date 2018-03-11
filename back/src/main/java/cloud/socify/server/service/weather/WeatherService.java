package cloud.socify.server.service.weather;

import cloud.socify.server.model.WeatherResponse;

public interface WeatherService {

    WeatherResponse getByCity(String city);

}
