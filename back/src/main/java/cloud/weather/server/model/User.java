package cloud.weather.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @Size(min = 5, max = 15)
    private String username;

    @JsonIgnore
    @Column(name = "password")
    @Size(min = 5, max = 30)
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTimestamp;

}
