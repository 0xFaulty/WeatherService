package cloud.socify.server.model.info;

import lombok.Data;

@Data
public class InfoResponse {
    private String type;
    private Info info;
}
