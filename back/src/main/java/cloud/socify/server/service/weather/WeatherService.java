package cloud.socify.server.service.weather;

import cloud.socify.server.model.info.InfoResponse;

import java.util.List;

public interface WeatherService {

    InfoResponse getByCity(String city, String token) throws Exception;

    InfoResponse getHistory(String token);

    List<String> getAvailableCities();

}
