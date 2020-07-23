package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.CiudadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ciudad} and its DTO {@link CiudadDTO}.
 */
@Mapper(componentModel = "spring", uses = {DepartamentoMapper.class})
public interface CiudadMapper extends EntityMapper<CiudadDTO, Ciudad> {

    @Mapping(source = "departamento.nombre", target = "departamentoId")
    CiudadDTO toDto(Ciudad ciudad);

    @Mapping(target = "personas", ignore = true)
    @Mapping(target = "removePersona", ignore = true)
    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    @Mapping(target = "entidads", ignore = true)
    @Mapping(target = "removeEntidad", ignore = true)
    @Mapping(source = "departamentoId", target = "departamento")
    Ciudad toEntity(CiudadDTO ciudadDTO);

    default Ciudad fromId(String id) {
        if (id == null) {
            return null;
        }
        Ciudad ciudad = new Ciudad();
        ciudad.setId(id);
        return ciudad;
    }
}
