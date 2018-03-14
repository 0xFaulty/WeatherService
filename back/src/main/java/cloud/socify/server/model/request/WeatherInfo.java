package cloud.socify.server.model.request;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

@Data
@Entity
@Table(name = "requests_by_city")
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "finished")
    private boolean finished;
    @Column(name = "city")
    private String city;
    @Column(name = "date")
    private Date date;
    @Column(name = "temp")
    private double temp;
    @Column(name = "pressure")
    private double pressure;
    @Column(name = "description")
    private String description;
    @Column(name = "lon")
    private double lon;
    @Column(name = "lat")
    private double lat;
    @Transient
    private String imageUrl;
    @Transient
    private String type = "requests_by_city";
}
