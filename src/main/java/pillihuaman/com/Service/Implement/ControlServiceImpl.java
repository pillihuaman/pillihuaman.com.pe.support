package pillihuaman.com.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pillihuaman.com.Service.ControlService;
import pillihuaman.com.base.commons.MyJsonWebToken;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqControl;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespControl;
import pillihuaman.com.basebd.control.domain.dao.ControlDAO;

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
