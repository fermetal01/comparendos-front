package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.EstadoComparendoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadoComparendo} and its DTO {@link EstadoComparendoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstadoComparendoMapper extends EntityMapper<EstadoComparendoDTO, EstadoComparendo> {


    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    EstadoComparendo toEntity(EstadoComparendoDTO estadoComparendoDTO);

    default EstadoComparendo fromId(String id) {
        if (id == null) {
            return null;
        }
        EstadoComparendo estadoComparendo = new EstadoComparendo();
        estadoComparendo.setId(id);
        return estadoComparendo;
    }
}
