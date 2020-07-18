package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.OrganismoTransitoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrganismoTransito} and its DTO {@link OrganismoTransitoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganismoTransitoMapper extends EntityMapper<OrganismoTransitoDTO, OrganismoTransito> {


    @Mapping(target = "licencias", ignore = true)
    @Mapping(target = "removeLicencia", ignore = true)
    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    OrganismoTransito toEntity(OrganismoTransitoDTO organismoTransitoDTO);

    default OrganismoTransito fromId(String id) {
        if (id == null) {
            return null;
        }
        OrganismoTransito organismoTransito = new OrganismoTransito();
        organismoTransito.setId(id);
        return organismoTransito;
    }
}
