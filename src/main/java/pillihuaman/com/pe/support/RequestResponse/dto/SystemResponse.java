package pillihuaman.com.pe.support.RequestResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pillihuaman.com.pe.basebd.menu.Menu;

@Data
@Builder
@AllArgsConstructor
public class SystemResponse {
    private String id; // You can use String for the ID here
    private String name;
    private String version;
    private Menu mainMenu;
    private String timezone;
    private boolean isActive;
    private String contactEmail;
    private String supportPhone;
}
