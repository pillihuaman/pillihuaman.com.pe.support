package pillihuaman.com.support.Service;


import org.springframework.http.ResponseEntity;
import pillihuaman.com.lib.commons.MyJsonWebToken;
import pillihuaman.com.lib.request.ReqParameter;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.ResponseParameter;


import java.util.List;

public interface ParameterService {
    ResponseEntity<Object> SaveParameter(MyJsonWebToken token, ReqParameter request);

    RespBase<List<ResponseParameter>> getParameterbyIdCode(MyJsonWebToken token, ReqParameter request);
}



