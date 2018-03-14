package cloud.socify.server.model.info.impl;

import cloud.socify.server.model.info.Info;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo implements Info {
    private String message;
}
