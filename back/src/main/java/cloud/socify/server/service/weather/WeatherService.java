package cloud.socify.server.service.weather;

import cloud.socify.server.model.info.InfoResponse;

public interface WeatherService {

    InfoResponse addRequestByCity(String city, String token) throws Exception;

}
