package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.LicenciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Licencia} and its DTO {@link LicenciaDTO}.
 */
@Mapper(componentModel = "spring", uses = {RestriccionMapper.class, TipoLicenciaMapper.class, CategoriaMapper.class, ServicioMapper.class, OrganismoTransitoMapper.class})
public interface LicenciaMapper extends EntityMapper<LicenciaDTO, Licencia> {

    @Mapping(source = "tipoLicencia.tipo", target = "tipoLicenciaId")
    @Mapping(source = "categoria.tipo", target = "categoriaId")
    @Mapping(source = "servicio.tipo", target = "servicioId")
    @Mapping(source = "organismoTransito.nombre", target = "organismoTransitoId")
    LicenciaDTO toDto(Licencia licencia);

    @Mapping(target = "personas", ignore = true)
    @Mapping(target = "removePersona", ignore = true)
    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    @Mapping(target = "removeRestriccion", ignore = true)
    @Mapping(source = "tipoLicenciaId", target = "tipoLicencia")
    @Mapping(source = "categoriaId", target = "categoria")
    @Mapping(source = "servicioId", target = "servicio")
    @Mapping(source = "organismoTransitoId", target = "organismoTransito")
    Licencia toEntity(LicenciaDTO licenciaDTO);

    default Licencia fromId(String id) {
        if (id == null) {
            return null;
        }
        Licencia licencia = new Licencia();
        licencia.setId(id);
        return licencia;
    }
}
