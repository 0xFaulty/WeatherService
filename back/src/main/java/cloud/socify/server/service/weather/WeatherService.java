package cloud.socify.server.service.weather;

public interface WeatherService {

    Object addRequestByCity(String city, String token) throws Exception;

}
