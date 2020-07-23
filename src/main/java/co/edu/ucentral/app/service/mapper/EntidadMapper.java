package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.EntidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Entidad} and its DTO {@link EntidadDTO}.
 */
@Mapper(componentModel = "spring", uses = {CiudadMapper.class})
public interface EntidadMapper extends EntityMapper<EntidadDTO, Entidad> {

    @Mapping(source = "ciudad.nombre", target = "ciudadId")
    EntidadDTO toDto(Entidad entidad);

    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    @Mapping(source = "ciudadId", target = "ciudad")
    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    Entidad toEntity(EntidadDTO entidadDTO);

    default Entidad fromId(String id) {
        if (id == null) {
            return null;
        }
        Entidad entidad = new Entidad();
        entidad.setId(id);
        return entidad;
    }
}
