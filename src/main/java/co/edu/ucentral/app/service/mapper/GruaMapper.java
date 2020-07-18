package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.GruaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Grua} and its DTO {@link GruaDTO}.
 */
@Mapper(componentModel = "spring", uses = {VehiculoMapper.class})
public interface GruaMapper extends EntityMapper<GruaDTO, Grua> {

    @Mapping(source = "vehiculo.id", target = "vehiculoId")
    GruaDTO toDto(Grua grua);

    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    @Mapping(source = "vehiculoId", target = "vehiculo")
    Grua toEntity(GruaDTO gruaDTO);

    default Grua fromId(String id) {
        if (id == null) {
            return null;
        }
        Grua grua = new Grua();
        grua.setId(id);
        return grua;
    }
}
