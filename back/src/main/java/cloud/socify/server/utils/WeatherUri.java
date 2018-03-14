package cloud.socify.server.utils;

import java.util.HashMap;
import java.util.Map;

public class WeatherUri {

    private static Map<String, String> wMap = new HashMap<>();
    private static String baseUri = "http://openweathermap.org/img/w/";

    static {
        wMap.put("clear sky", "01");
        wMap.put("few clouds", "02");
        wMap.put("scattered clouds", "03");
        wMap.put("broken clouds", "04");
        wMap.put("shower rain", "09");
        wMap.put("rain", "10");
        wMap.put("thunderstorm", "11");
        wMap.put("snow", "13");
        wMap.put("mist", "50");
    }

    public static String getDayUri(String name) {
        return baseUri + wMap.get(name.toLowerCase()) + "d.png";
    }

    public static String getNightUri(String name) {
        return baseUri + wMap.get(name.toLowerCase()) + "n.png";
    }

}
