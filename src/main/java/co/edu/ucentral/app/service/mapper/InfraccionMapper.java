package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.InfraccionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Infraccion} and its DTO {@link InfraccionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InfraccionMapper extends EntityMapper<InfraccionDTO, Infraccion> {


    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    Infraccion toEntity(InfraccionDTO infraccionDTO);

    default Infraccion fromId(String id) {
        if (id == null) {
            return null;
        }
        Infraccion infraccion = new Infraccion();
        infraccion.setId(id);
        return infraccion;
    }
}
