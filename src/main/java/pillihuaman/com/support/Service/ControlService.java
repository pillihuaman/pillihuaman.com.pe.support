package pillihuaman.com.support.Service;

import pillihuaman.com.lib.commons.MyJsonWebToken;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqControl;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespControl;

import java.util.List;

public interface ControlService {

	RespBase<List<RespControl>> listControl(MyJsonWebToken token, ReqBase<ReqControl> request);

	RespBase<RespControl> saveControl(MyJsonWebToken token, ReqBase<ReqControl> request) ;
}



