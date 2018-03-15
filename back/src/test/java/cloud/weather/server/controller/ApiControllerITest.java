package cloud.weather.server.controller;

import cloud.weather.server.model.ApiVersion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiControllerITest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getApiVersion() {
        ResponseEntity responseEntity = restTemplate.exchange(
                "http://localhost:8080/service/version",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseEntity>() {
                });
        Object o = responseEntity.getBody();
//        Assert.assertEquals(true, o instanceof ApiVersion);
        if (o instanceof ApiVersion) {
            ApiVersion apiVersion = (ApiVersion) o;
            Assert.assertEquals("", apiVersion.getVersion());
        }
    }
}