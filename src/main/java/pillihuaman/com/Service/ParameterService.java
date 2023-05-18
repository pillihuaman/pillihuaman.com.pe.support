package pillihuaman.com.Service;


import pillihuaman.com.base.request.ReqParameter;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.ResponseParameter;
import pillihuaman.com.security.MyJsonWebToken;

public interface ParameterService {
    RespBase<ResponseParameter> SaveParameter(MyJsonWebToken token, ReqParameter request);

}



