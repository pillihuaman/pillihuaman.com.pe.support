package pillihuaman.com.support.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.basebd.common.MyJsonWebToken;
import pillihuaman.com.basebd.system.dao.SystemDAO;
import pillihuaman.com.lib.exception.CustomRegistrationException;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.ResponseUser;
import pillihuaman.com.support.Help.Constants;
import pillihuaman.com.support.RequestResponse.dto.SystemRequest;
import pillihuaman.com.support.Service.SystemService;
import pillihuaman.com.support.Service.mapper.SystemMapper;
import pillihuaman.com.support.foreing.ExternalApiService;
import pillihuaman.com.support.foreing.ExternalData;

@Component
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemDAO systemDAO;
    @Autowired
    private ExternalApiService externalApiService; // Inject the ExternalApiService


    private final String NAME_OBJECT = "SystemServiceImpl";
    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public RespBase<Object> listSystem(int page, int pageSize) {
        return null;
    }

    @Override
    public RespBase<Object> saveSystem(ReqBase<SystemRequest> req, String to) {
        try {
            log.debug(to);
            return RespBase.builder().payload(systemDAO.saveSystem(SystemMapper.INSTANCE.toEntity(req.getData()), externalApiService.fetchData(Constants.API_URL_ACCESS_MYWEBTOKEN_INFO, to))).trace(RespBase.Trace.builder().traceId("1").build()).status(RespBase.Status.builder().success(true).error(null).build()).build();
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ": ", e);
            throw new CustomRegistrationException("failed " + e.getMessage());
        }
    }

    @Override
    public RespBase<Object> systemById(Object id) {
        return null;
    }

    @Override
    public boolean deleteSystem(Object id) {
        return false;
    }
}
