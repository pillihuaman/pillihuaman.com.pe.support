package pillihuaman.com.support.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.basebd.control.Control;
import pillihuaman.com.basebd.control.dao.ControlDAO;
import pillihuaman.com.basebd.common.MyJsonWebToken;
import pillihuaman.com.lib.exception.CustomRegistrationException;
import pillihuaman.com.lib.request.ReqBase;
import pillihuaman.com.lib.request.ReqControl;
import pillihuaman.com.lib.response.RespBase;
import pillihuaman.com.lib.response.RespControl;
import pillihuaman.com.support.Service.ControlService;
import pillihuaman.com.support.Service.mapper.ControlMapper;
import pillihuaman.com.support.foreing.ExternalApiService;

import java.util.List;

@Component
public class ControlServiceImpl implements ControlService {
    @Autowired
    private ControlDAO controlDAO;

    protected final Log log = LogFactory.getLog(getClass());
    @Autowired
    private  ExternalApiService externalApiService;

    @Override
    public RespBase<List<RespControl>> listControl(MyJsonWebToken token, ReqBase<ReqControl> request) {
        ReqControl r = request.getData();
        ModelMapper modelMapper = new ModelMapper();
        Control para = modelMapper.map(r, Control.class);
        controlDAO.saveControl(para, token);
        return null;
    }

    @Override
    public Object saveControl(String token, ReqBase<ReqControl> request) {
        try {

            return RespBase.builder().payload(controlDAO.saveControl(ControlMapper.INSTANCE.toControl(request.getData()), null)).trace(RespBase.Trace.builder().traceId("1").build()).status(RespBase.Status.builder().success(true).error(null).build()).build();

        } catch (Exception ex) {
            throw new CustomRegistrationException("failed " + ex.getMessage());
        }
    }
}
