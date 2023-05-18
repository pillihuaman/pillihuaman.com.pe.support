package pillihuaman.com.Help;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pillihuaman.com.base.request.ReqParameter;
import pillihuaman.com.basebd.parameter.domain.Parameter;

@Mapper(componentModel = "spring")
public interface MapperEntity {
    @Mapping(source = "id", target = "id")
    Parameter parameterToReqParameter(ReqParameter source);


}
