package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.TipoIdentificacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoIdentificacion} and its DTO {@link TipoIdentificacionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoIdentificacionMapper extends EntityMapper<TipoIdentificacionDTO, TipoIdentificacion> {


    @Mapping(target = "personas", ignore = true)
    @Mapping(target = "removePersona", ignore = true)
    TipoIdentificacion toEntity(TipoIdentificacionDTO tipoIdentificacionDTO);

    default TipoIdentificacion fromId(String id) {
        if (id == null) {
            return null;
        }
        TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
        tipoIdentificacion.setId(id);
        return tipoIdentificacion;
    }
}
