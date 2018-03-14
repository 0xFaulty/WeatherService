package cloud.socify.server.service.weather;

import cloud.socify.server.controller.ExceptionHandlerAdvice;
import cloud.socify.server.dao.request_by_city.RequestByCityRepository;
import cloud.socify.server.ex.UserNotFoundException;
import cloud.socify.server.model.request.WeatherInfo;
import cloud.socify.server.service.login.LoginService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    private final static ScheduledExecutorService schExService = Executors.newScheduledThreadPool(1);
    private final static LinkedBlockingQueue<FutureTask<WeatherInfo>> queue = new LinkedBlockingQueue<>();
    private final static ExecutorService executor = Executors.newFixedThreadPool(1);

    private final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final String API_KEY = "&APPID=c35e703a7be9014e07a7b302bfbd5cee";

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private RequestByCityRepository repository;

    @Autowired
    private LoginService loginService;

    public WeatherServiceImpl() {
        schExService.scheduleWithFixedDelay(this::pollQueue, 0, 1, TimeUnit.SECONDS);
    }

    private void pollQueue() {
        try {
            if (!queue.isEmpty()) {
                executor.execute(queue.poll());
            }
        } catch (Exception e) {
            LOG.error("Error while execute task", e);
        }
    }

    @Override
    public Object addRequestByCity(String city, String token) throws Exception {
        city = city.replaceAll("_", " ");
//        long userId = loginService.getUserId(token);
        long userId = 1;
        String url = API_URL + city + API_KEY;
        Callable<WeatherInfo> infoCallable = () -> mapResponse(om.readTree(getUri(url)), userId);
        FutureTask<WeatherInfo> future = new FutureTask<>(infoCallable);
        queue.offer(future);
        WeatherInfo weatherInfo = future.get();
//        repository.add(weatherInfo);
        return weatherInfo;
    }

    private WeatherInfo mapResponse(JsonNode node, long userId) {
        WeatherInfo wr = new WeatherInfo();
        wr.setUserId(userId);
        wr.setFinished(true);
        wr.setCity(node.get("name").asText());
        wr.setDate(new Date());
        JsonNode main = node.get("main");
        wr.setTemp(main.get("temp").asDouble());
        wr.setPressure(main.get("pressure").asDouble());
        JsonNode weather = node.get("weather");
        wr.setDescription(weather.get(0).get("description").asText());
        JsonNode coord = node.get("coord");
        wr.setLon(coord.get("lon").asDouble());
        wr.setLat(coord.get("lat").asDouble());
        return wr;
    }

    private String getUri(String uri) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri);

        HttpResponse response = client.execute(request);

        int code = response.getStatusLine().getStatusCode();
        if (HttpStatus.valueOf(code) != HttpStatus.OK) {
            throw new IOException("Can't get response, code: " + code);
        }
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }


}
