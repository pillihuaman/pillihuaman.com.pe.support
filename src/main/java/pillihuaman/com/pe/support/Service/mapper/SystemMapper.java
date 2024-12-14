package pillihuaman.com.pe.support.Service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pillihuaman.com.pe.basebd.system.System;
import pillihuaman.com.pe.support.RequestResponse.dto.SystemRequest;


@Mapper
public interface SystemMapper {
    SystemMapper INSTANCE = Mappers.getMapper(SystemMapper.class);

    @Mapping(target = "id", ignore = true)
        // Ignore mapping of 'id' when mapping from request to entity
    System toEntity(SystemRequest request);

    Object toResponse(System entity);
}
