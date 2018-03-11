package cloud.socify.server.service;

import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {

    public String getVersion() {
        return "v1";
    }

}
