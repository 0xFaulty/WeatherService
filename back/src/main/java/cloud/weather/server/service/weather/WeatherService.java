package cloud.weather.server.service.weather;

import cloud.weather.server.ex.RequestException;
import cloud.weather.server.model.info.InfoResponse;

import java.util.List;

public interface WeatherService {

    InfoResponse getByCity(String city, String token) throws RequestException;

    InfoResponse getHistory(String token) throws RequestException;

    List<String> getAvailableCities();

}
