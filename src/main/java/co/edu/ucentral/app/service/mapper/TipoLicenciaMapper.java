package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.TipoLicenciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoLicencia} and its DTO {@link TipoLicenciaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoLicenciaMapper extends EntityMapper<TipoLicenciaDTO, TipoLicencia> {


    @Mapping(target = "licencias", ignore = true)
    @Mapping(target = "removeLicencia", ignore = true)
    TipoLicencia toEntity(TipoLicenciaDTO tipoLicenciaDTO);

    default TipoLicencia fromId(String id) {
        if (id == null) {
            return null;
        }
        TipoLicencia tipoLicencia = new TipoLicencia();
        tipoLicencia.setId(id);
        return tipoLicencia;
    }
}
