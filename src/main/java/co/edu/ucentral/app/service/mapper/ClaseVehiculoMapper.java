package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.ClaseVehiculoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaseVehiculo} and its DTO {@link ClaseVehiculoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaseVehiculoMapper extends EntityMapper<ClaseVehiculoDTO, ClaseVehiculo> {


    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    ClaseVehiculo toEntity(ClaseVehiculoDTO claseVehiculoDTO);

    default ClaseVehiculo fromId(String id) {
        if (id == null) {
            return null;
        }
        ClaseVehiculo claseVehiculo = new ClaseVehiculo();
        claseVehiculo.setId(id);
        return claseVehiculo;
    }
}
