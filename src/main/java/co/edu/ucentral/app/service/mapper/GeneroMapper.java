package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.GeneroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Genero} and its DTO {@link GeneroDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeneroMapper extends EntityMapper<GeneroDTO, Genero> {


    @Mapping(target = "personas", ignore = true)
    @Mapping(target = "removePersona", ignore = true)
    Genero toEntity(GeneroDTO generoDTO);

    default Genero fromId(String id) {
        if (id == null) {
            return null;
        }
        Genero genero = new Genero();
        genero.setId(id);
        return genero;
    }
}
