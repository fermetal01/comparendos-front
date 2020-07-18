package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.AgenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agente} and its DTO {@link AgenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class, RangoMapper.class})
public interface AgenteMapper extends EntityMapper<AgenteDTO, Agente> {

    @Mapping(source = "persona.id", target = "personaId")
    @Mapping(source = "rango.id", target = "rangoId")
    AgenteDTO toDto(Agente agente);

    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    @Mapping(source = "personaId", target = "persona")
    @Mapping(source = "rangoId", target = "rango")
    Agente toEntity(AgenteDTO agenteDTO);

    default Agente fromId(String id) {
        if (id == null) {
            return null;
        }
        Agente agente = new Agente();
        agente.setId(id);
        return agente;
    }
}
