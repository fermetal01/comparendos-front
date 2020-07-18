package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.TipoSanguineoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoSanguineo} and its DTO {@link TipoSanguineoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoSanguineoMapper extends EntityMapper<TipoSanguineoDTO, TipoSanguineo> {


    @Mapping(target = "personas", ignore = true)
    @Mapping(target = "removePersona", ignore = true)
    TipoSanguineo toEntity(TipoSanguineoDTO tipoSanguineoDTO);

    default TipoSanguineo fromId(String id) {
        if (id == null) {
            return null;
        }
        TipoSanguineo tipoSanguineo = new TipoSanguineo();
        tipoSanguineo.setId(id);
        return tipoSanguineo;
    }
}
