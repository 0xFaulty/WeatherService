package cloud.socify.server.service.api;

import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {

    @Override
    public String getVersion() {
        return "v1";
    }

}
