package cloud.socify.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class WeatherResponse {
    private Date date;
    private String city;
    private double temp;
    private int pressure;
    private String description;
    private double lon;
    private double lat;
}
