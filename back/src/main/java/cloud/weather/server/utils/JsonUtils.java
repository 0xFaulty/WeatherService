package cloud.weather.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String getOneParamJson(String name, String value) throws JsonProcessingException {
        ObjectNode node = mapper.createObjectNode();
        node.put(name, value);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

}
