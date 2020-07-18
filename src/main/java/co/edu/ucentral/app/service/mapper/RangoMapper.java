package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.RangoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rango} and its DTO {@link RangoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RangoMapper extends EntityMapper<RangoDTO, Rango> {


    @Mapping(target = "agentes", ignore = true)
    @Mapping(target = "removeAgente", ignore = true)
    Rango toEntity(RangoDTO rangoDTO);

    default Rango fromId(String id) {
        if (id == null) {
            return null;
        }
        Rango rango = new Rango();
        rango.setId(id);
        return rango;
    }
}
