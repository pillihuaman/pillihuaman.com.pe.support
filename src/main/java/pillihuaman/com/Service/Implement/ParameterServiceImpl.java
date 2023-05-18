package pillihuaman.com.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.Help.MapperEntity;
import pillihuaman.com.Service.ParameterService;
import pillihuaman.com.base.request.ReqParameter;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.ResponseParameter;
import pillihuaman.com.basebd.parameter.domain.dao.implement.ParameterDaoImplement;
import pillihuaman.com.security.MyJsonWebToken;

@Component
public class ParameterServiceImpl implements ParameterService {
	@Autowired
	private ParameterDaoImplement parameterDaoImplement;
   @Autowired
    private MapperEntity mapperEntity;

	protected final Log log = LogFactory.getLog(getClass());




	@Override
	public RespBase<ResponseParameter> SaveParameter(MyJsonWebToken token, ReqParameter request) {
		RespBase<ResponseParameter> response = new RespBase<ResponseParameter>();
		try {
			parameterDaoImplement.saveParemeter(mapperEntity.parameterToReqParameter(request));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return null;
	}
}
