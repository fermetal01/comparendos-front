package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.RestriccionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Restriccion} and its DTO {@link RestriccionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RestriccionMapper extends EntityMapper<RestriccionDTO, Restriccion> {


    @Mapping(target = "licencias", ignore = true)
    @Mapping(target = "removeLicencia", ignore = true)
    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    Restriccion toEntity(RestriccionDTO restriccionDTO);

    default Restriccion fromId(String id) {
        if (id == null) {
            return null;
        }
        Restriccion restriccion = new Restriccion();
        restriccion.setId(id);
        return restriccion;
    }
}
