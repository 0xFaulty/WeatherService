package cloud.socify.server.dao.request_by_city;

import cloud.socify.server.model.info.impl.WeatherInfo;
import org.springframework.data.repository.CrudRepository;

public interface WeatherInfoRepository extends CrudRepository<WeatherInfo, Long>, WeatherInfoRepositoryCustom {
}
