package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.CombustibleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Combustible} and its DTO {@link CombustibleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CombustibleMapper extends EntityMapper<CombustibleDTO, Combustible> {


    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    Combustible toEntity(CombustibleDTO combustibleDTO);

    default Combustible fromId(String id) {
        if (id == null) {
            return null;
        }
        Combustible combustible = new Combustible();
        combustible.setId(id);
        return combustible;
    }
}
