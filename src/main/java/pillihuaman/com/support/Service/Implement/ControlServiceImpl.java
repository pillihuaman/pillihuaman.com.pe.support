package pillihuaman.com.support.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pillihuaman.com.basebd.control.dao.ControlDAO;
import pillihuaman.com.lib.commons.MyJsonWebToken;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqControl;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespControl;
import pillihuaman.com.support.Service.ControlService;


import java.util.List;

@Component
public class ControlServiceImpl implements ControlService {
	@Autowired
	private ControlDAO controlDAO;


	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public RespBase<List<RespControl>> listControl(MyJsonWebToken token, ReqBase<ReqControl> request) {
		ReqControl r=request.getData();
		ModelMapper modelMapper = new ModelMapper();
		//Control para= modelMapper.map(r, Control.class);
		controlDAO.saveControl(r,token);
		return null;
	}

	@Override
	public RespBase<RespControl> saveControl(MyJsonWebToken token, ReqBase<ReqControl> request) {
		return null;
	}
}
