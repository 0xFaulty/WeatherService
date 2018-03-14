package cloud.socify.server.service.api;

import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {

    @Override
    public Object getVersion() {
        return new Version();
    }

    private class Version{
        private String version = "v1";

        public String getVersion() {
            return version;
        }
    }

}
