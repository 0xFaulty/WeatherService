package cloud.socify.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String token;
    private List<WeatherResponse> history;
}
