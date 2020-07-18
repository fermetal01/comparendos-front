package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.MarcaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Marca} and its DTO {@link MarcaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MarcaMapper extends EntityMapper<MarcaDTO, Marca> {


    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    Marca toEntity(MarcaDTO marcaDTO);

    default Marca fromId(String id) {
        if (id == null) {
            return null;
        }
        Marca marca = new Marca();
        marca.setId(id);
        return marca;
    }
}
