package pillihuaman.com.pe.support.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.pe.basebd.common.MyJsonWebToken;
import pillihuaman.com.pe.basebd.system.System;
import pillihuaman.com.pe.basebd.system.dao.SystemDAO;
import pillihuaman.com.pe.lib.exception.CustomRegistrationException;
import pillihuaman.com.pe.lib.request.ReqBase;
import pillihuaman.com.pe.lib.response.RespBase;
import pillihuaman.com.pe.support.RequestResponse.dto.SystemRequest;
import pillihuaman.com.pe.support.Service.SystemService;
import pillihuaman.com.pe.support.Service.mapper.SystemMapper;

import java.util.Date;
import java.util.List;

@Component
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemDAO systemDAO;

    private final String NAME_OBJECT = "SystemServiceImpl";
    protected final Log log = LogFactory.getLog(getClass());


    @Override
    public RespBase<Object> listSystem(int page, int pageSize, SystemRequest systemRequest) {
        try {
            log.debug("Fetching system list with page: " + page + ", pageSize: " + pageSize + ", filters: " + systemRequest);

            // Mapear la solicitud DTO a la entidad para el filtro
            System filter = SystemMapper.INSTANCE.toEntity(systemRequest);

            // Obtener la lista de sistemas desde el DAO
            List<System> systems = systemDAO.listSystem(page, pageSize, filter);

            // Mapear la lista de entidades a respuestas DTO
            List<Object> responseList = systems.stream()
                    .map(SystemMapper.INSTANCE::toResponse)
                    .toList();

            // Crear y devolver la respuesta con la lista de sistemas
            RespBase<Object> respBase = new RespBase<>();
            respBase.setData(responseList);
            //  respBase.setSuccess(true);
            return respBase;
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ".listSystem: ", e);
            throw new CustomRegistrationException("Failed to list systems: " + e.getMessage());
        }
    }


    @Override
    public RespBase<Object> saveSystem(ReqBase<SystemRequest> req, MyJsonWebToken jwt) {
        try {
            log.debug("Saving system with request: " + req + ", token: " + jwt);

            // Map request DTO to entity
            System systemEntity = SystemMapper.INSTANCE.toEntity(req.getData());

            // Add audit information
            systemEntity.getAudit().setDateRegister(new Date());
            systemEntity.getAudit().setMail(jwt.getUser().getMail());
            systemEntity.getAudit().setCodUser(jwt.getUser().getId());

            // Save the entity using DAO
            System savedSystem = systemDAO.saveSystem(systemEntity, jwt);

            // Map saved entity to response DTO
            Object response = SystemMapper.INSTANCE.toResponse(savedSystem);

            // Wrap response in RespBase
            RespBase<Object> respBase = new RespBase<>();
            respBase.setData(response);
            // respBase.setSuccess(true);
            return respBase;
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ": ", e);
            throw new CustomRegistrationException("Failed to save system: " + e.getMessage());
        }
    }

    @Override
    public RespBase<Object> systemById(Object id) {
        try {
            log.debug("Fetching system by id: " + id);

            // Fetch system by ID
            System system = systemDAO.systemById(id);

            // Map entity to response DTO
            Object response = SystemMapper.INSTANCE.toResponse(system);

            // Wrap response in RespBase
            RespBase<Object> respBase = new RespBase<>();
            respBase.setData(response);
            //respBase.setSuccess(true);
            return respBase;
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ": ", e);
            throw new CustomRegistrationException("Failed to fetch system by ID: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteSystem(Object id, MyJsonWebToken jwt) {
        try {
            log.debug("Deleting system by id: " + id);

            // Perform delete operation
            boolean result = systemDAO.deleteSystem(id, jwt);

            log.debug("System deleted successfully");
            return result;
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ": ", e);
            throw new CustomRegistrationException("Failed to delete system: " + e.getMessage());
        }
    }


}
