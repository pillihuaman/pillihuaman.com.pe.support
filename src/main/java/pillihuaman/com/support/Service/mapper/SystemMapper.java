package pillihuaman.com.support.Service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pillihuaman.com.basebd.system.System;
import pillihuaman.com.support.RequestResponse.dto.SystemRequest;
import pillihuaman.com.support.RequestResponse.dto.SystemResponse;

@Mapper
public interface SystemMapper {
    SystemMapper INSTANCE = Mappers.getMapper(SystemMapper.class);

    @Mapping(target = "id", ignore = true)
        // Ignore mapping of 'id' when mapping from request to entity
    System toEntity(SystemRequest request);

    SystemResponse toResponse(System entity);
}
