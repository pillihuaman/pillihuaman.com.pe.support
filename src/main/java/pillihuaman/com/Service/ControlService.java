package pillihuaman.com.Service;

import pillihuaman.com.base.commons.MyJsonWebToken;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqControl;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespControl;

import java.util.List;


public interface ControlService {

	RespBase<List<RespControl>> listControl(MyJsonWebToken token, ReqBase<ReqControl> request);

	RespBase<RespControl> saveControl(MyJsonWebToken token, ReqBase<ReqControl> request) ;
}



