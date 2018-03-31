package cloud.weather.server.dao.weather;

import cloud.weather.server.model.info.impl.WeatherInfo;
import org.springframework.data.repository.CrudRepository;

public interface WeatherInfoRepository extends CrudRepository<WeatherInfo, Long>, WeatherInfoRepositoryCustom {
}
