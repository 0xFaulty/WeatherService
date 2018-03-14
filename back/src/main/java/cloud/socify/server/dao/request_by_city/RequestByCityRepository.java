package cloud.socify.server.dao.request_by_city;

import cloud.socify.server.model.info.impl.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestByCityRepository extends JpaRepository<WeatherInfo, Long>, RequestByCityRepositoryCustom {
}
