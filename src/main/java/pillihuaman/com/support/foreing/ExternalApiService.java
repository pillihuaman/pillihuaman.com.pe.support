package pillihuaman.com.support.foreing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pillihuaman.com.basebd.common.MyJsonWebToken;
import pillihuaman.com.lib.response.ResponseUser;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    // Read the API URL from the configuration
    @Value("${external-api.url}")
    private String apiUrl;

    @Autowired
    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MyJsonWebToken fetchData(String apiurl, String tok) {
        return restTemplate.getForObject(apiUrl +apiurl+ tok, MyJsonWebToken.class);
    }

}