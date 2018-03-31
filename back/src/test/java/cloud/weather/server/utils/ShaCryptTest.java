package cloud.weather.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShaCryptTest {

    @Test
    public void hash() {
        String password = "admin";
        String hash1 = ShaCrypt.hash(password);
        String hash2 = ShaCrypt.hash(password);
        assertEquals(hash1, hash2);
        password = "guest";
        hash1 = ShaCrypt.hash(password);
        hash2 = ShaCrypt.hash(password);
        assertEquals(hash1, hash2);
    }

}