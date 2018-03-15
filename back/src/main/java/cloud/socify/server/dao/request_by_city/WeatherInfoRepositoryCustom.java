package cloud.socify.server.dao.request_by_city;

import cloud.socify.server.model.info.impl.WeatherInfo;

import java.util.List;

public interface WeatherInfoRepositoryCustom {

    List<WeatherInfo> getUserHistory(long userId);

    List<WeatherInfo> list();

}
