package cloud.socify.server.service.weather;

import cloud.socify.server.model.WeatherResponse;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WeatherServiceImpl implements WeatherService {

    //  API_URL: 'http://samples.openweathermap.org/data/2.5/weather?q=',
    //  API_KEY: 'APPID=c35e703a7be9014e07a7b302bfbd5cee'

    @Override
    public WeatherResponse getByCity(String city) {
        return new WeatherResponse(
                new Date(), city, 1, 770, "desc", 1.44, 2.55);
    }

}
