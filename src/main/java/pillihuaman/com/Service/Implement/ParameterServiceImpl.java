package pillihuaman.com.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pillihuaman.com.Help.Constantes;
import pillihuaman.com.Help.MaestrosUtilidades;
import pillihuaman.com.Service.ParameterService;
import pillihuaman.com.base.commons.MyJsonWebToken;
import pillihuaman.com.base.request.ReqParameter;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.ResponseParameter;
import pillihuaman.com.basebd.parameter.domain.Parameter;
import pillihuaman.com.basebd.parameter.domain.dao.implement.ParameterDaoImplement;
import pillihuaman.com.exception.CreatedException;
import pillihuaman.com.exception.CustomRestExceptionHandlerGeneric;
import pillihuaman.com.exception.UnprocessableEntityException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterDaoImplement parameterDaoImplement;


    protected final Log log = LogFactory.getLog(getClass());
    @Autowired
    private CustomRestExceptionHandlerGeneric exceptionHandler;

    @Override
    public ResponseEntity<Object> SaveParameter(MyJsonWebToken token, ReqParameter request) {
        RespBase<ResponseParameter> response = new RespBase<ResponseParameter>();

            ModelMapper modelMapper = new ModelMapper();
            Parameter para = modelMapper.map(request, Parameter.class);
            String name = "";
            if (!MaestrosUtilidades.isEmpty(request)) {
                if (!MaestrosUtilidades.isEmpty(request.getName())) {
                    name = request.getName().toUpperCase().trim();
                    List<Parameter> ls = parameterDaoImplement.getParameterByName(name);
                    if (ls.isEmpty()) {
                        para.setName(name);
                        parameterDaoImplement.saveParemeter(para, token);
                        String successMessage = "Resource created successfully";
                        throw new CreatedException(successMessage);

                    } else {
                        String errorMessage = "Parameter with name '" + name + "' already exists.";
                        ResponseEntity<Object> errorResponse = exceptionHandler.handleUnprocessableEntityException(new UnprocessableEntityException(errorMessage));
                        return errorResponse;
                    }
                }
            }
        ResponseEntity<Object> errorResponse = exceptionHandler.handleUnprocessableEntityException(new UnprocessableEntityException( Constantes.SUCCESS_CREATED));
        return errorResponse;
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
            lst.setPayload(lstres);
            return lst;
        } catch (Exception e) {
            int d=1/0;
            log.error(e +" ParameterServiceImpl ");
            throw new RuntimeException(e);
        }

    }
}
