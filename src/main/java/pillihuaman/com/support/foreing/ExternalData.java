package pillihuaman.com.support.foreing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pillihuaman.com.lib.response.ResponseUser;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalData {
    private ResponseUser field1;

    // Getters and setters
}