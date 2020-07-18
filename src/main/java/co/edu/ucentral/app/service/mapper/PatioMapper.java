package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.PatioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Patio} and its DTO {@link PatioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PatioMapper extends EntityMapper<PatioDTO, Patio> {


    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    Patio toEntity(PatioDTO patioDTO);

    default Patio fromId(String id) {
        if (id == null) {
            return null;
        }
        Patio patio = new Patio();
        patio.setId(id);
        return patio;
    }
}
