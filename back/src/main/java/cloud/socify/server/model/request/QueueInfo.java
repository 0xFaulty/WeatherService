package cloud.socify.server.model.request;

import lombok.Data;

@Data
public class QueueInfo {
    private int pos;
    private String type = "queue_info";
}
