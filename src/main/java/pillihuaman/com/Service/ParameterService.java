package pillihuaman.com.Service;


import org.springframework.http.ResponseEntity;
import pillihuaman.com.base.commons.MyJsonWebToken;
import pillihuaman.com.base.request.ReqParameter;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.ResponseParameter;

import java.util.List;

public interface ParameterService {
    ResponseEntity<Object> SaveParameter(MyJsonWebToken token, ReqParameter request);

    RespBase<List<ResponseParameter>> getParameterbyIdCode(MyJsonWebToken token, ReqParameter request);
}



