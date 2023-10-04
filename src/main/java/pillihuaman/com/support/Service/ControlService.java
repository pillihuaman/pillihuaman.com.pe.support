package pillihuaman.com.support.Service;

import pillihuaman.com.basebd.common.MyJsonWebToken;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqControl;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespControl;

import java.util.List;

public interface ControlService {

	RespBase<List<RespControl>> listControl(MyJsonWebToken token, ReqBase<ReqControl> request);

	Object saveControl(String token, ReqBase<ReqControl> request) ;
}



