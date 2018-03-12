package cloud.socify.server.dao.request_by_city;

import cloud.socify.server.model.request.WeatherInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RequestByCityRepositoryImpl implements RequestByCityRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(RequestByCityRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(WeatherInfo wr) {
        entityManager.persist(wr);
        logger.info("Successfully saved. Details: " + wr);
    }

    @Override
    public void update(WeatherInfo wr) {
        entityManager.merge(wr);
        logger.info("Successfully update. Details: " + wr);
    }

    @Override
    public void remove(long id) {
        WeatherInfo wr = entityManager.find(WeatherInfo.class, id);

        if (wr != null) {
            entityManager.remove(wr);
        }
        logger.info("Successfully removed. Details: " + wr);
    }

    @Override
    public WeatherInfo getById(long id) {
        WeatherInfo wr = entityManager.find(WeatherInfo.class, id);
        logger.info("Successfully loaded. Details: " + wr);

        return wr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<WeatherInfo> list() {
        List<WeatherInfo> wrList = entityManager.createQuery("from WeatherInfo").getResultList();
        for (WeatherInfo wr : wrList) {
            logger.info(" list: " + wr);
        }

        return wrList;
    }

}
