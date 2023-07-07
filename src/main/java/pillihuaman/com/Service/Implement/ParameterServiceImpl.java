package pillihuaman.com.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.Service.ParameterService;
import pillihuaman.com.base.commons.MyJsonWebToken;
import pillihuaman.com.base.request.ReqParameter;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.ResponseParameter;
import pillihuaman.com.basebd.parameter.domain.Parameter;
import pillihuaman.com.basebd.parameter.domain.dao.implement.ParameterDaoImplement;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterDaoImplement parameterDaoImplement;


    protected final Log log = LogFactory.getLog(getClass());


    @Override
    public RespBase<ResponseParameter> SaveParameter(MyJsonWebToken token, ReqParameter request) {
        RespBase<ResponseParameter> response = new RespBase<ResponseParameter>();
        try {
            ModelMapper modelMapper = new ModelMapper();
            Parameter para = modelMapper.map(request, Parameter.class);
            parameterDaoImplement.saveParemeter(para, token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public RespBase<List<ResponseParameter>> getParameterbyIdCode(MyJsonWebToken token, ReqParameter request) {
        RespBase<List<ResponseParameter>> lst = new RespBase<>();
        List<ResponseParameter> lstres = new ArrayList<>();
        try {

            ModelMapper modelMapper = new ModelMapper();
            Parameter destination = modelMapper.map(request, Parameter.class);

            List<Parameter> re = parameterDaoImplement.getParameterByIdCode(destination);

            parameterDaoImplement.getParameterByIdCode(destination).stream().forEach(
                    item -> lstres.add(modelMapper.map(item, ResponseParameter.class)));

           /* if (re != null && !re.isEmpty() && re.size() > 0) {
                for (Parameter parameter :
                        re) {
                    ResponseParameter responseParameter = modelMapper.map(re, ResponseParameter.class);
                    lstres.add(responseParameter);
                }

            }*/
            lst.setPayload(lstres);
            return lst;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
