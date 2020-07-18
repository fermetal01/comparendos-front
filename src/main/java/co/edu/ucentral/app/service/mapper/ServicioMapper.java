package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.ServicioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Servicio} and its DTO {@link ServicioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServicioMapper extends EntityMapper<ServicioDTO, Servicio> {


    @Mapping(target = "licencias", ignore = true)
    @Mapping(target = "removeLicencia", ignore = true)
    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    Servicio toEntity(ServicioDTO servicioDTO);

    default Servicio fromId(String id) {
        if (id == null) {
            return null;
        }
        Servicio servicio = new Servicio();
        servicio.setId(id);
        return servicio;
    }
}
