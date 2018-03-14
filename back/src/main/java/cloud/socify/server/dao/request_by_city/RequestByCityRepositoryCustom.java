package cloud.socify.server.dao.request_by_city;

import cloud.socify.server.model.info.impl.WeatherInfo;

import java.util.List;

public interface RequestByCityRepositoryCustom {

    void add(WeatherInfo object);

    void update(WeatherInfo object);

    void remove(long id);

    WeatherInfo getById(long id);

    List<WeatherInfo> list();

}
