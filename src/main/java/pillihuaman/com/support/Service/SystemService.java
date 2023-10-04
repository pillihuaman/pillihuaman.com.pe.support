package pillihuaman.com.support.Service;

import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.support.RequestResponse.dto.SystemRequest;
import pillihuaman.com.support.RequestResponse.dto.SystemResponse;

import java.util.List;

public interface SystemService {
    RespBase<Object> listSystem(int page, int pageSize);

    RespBase<Object> saveSystem(ReqBase<SystemRequest> req, String to);

    RespBase<Object>  systemById(Object id);

    boolean deleteSystem(Object id);
}



