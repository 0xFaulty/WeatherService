package cloud.weather.server.ex;

import cloud.weather.server.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestException extends Exception {

    public RequestException(String message) {
        super(message);
    }

    public String getJson() throws JsonProcessingException {
        return JsonUtils.getOneParamJson("error", super.getMessage());
    }

}
