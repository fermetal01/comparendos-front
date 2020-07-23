package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.ComparendoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comparendo} and its DTO {@link ComparendoDTO}.
 */
@Mapper(componentModel = "spring", uses = {InfraccionMapper.class, EstadoComparendoMapper.class, VehiculoMapper.class, LicenciaMapper.class, AgenteMapper.class, CiudadMapper.class, PersonaMapper.class, PatioMapper.class, GruaMapper.class, EntidadMapper.class})
public interface ComparendoMapper extends EntityMapper<ComparendoDTO, Comparendo> {

    @Mapping(source = "estado.tipo", target = "estadoId")
    @Mapping(source = "vehiculo.placa", target = "vehiculoId")
    @Mapping(source = "licenciaTransito.serial", target = "licenciaTransitoId")
    @Mapping(source = "licenciaConduccion.serial", target = "licenciaConduccionId")
    @Mapping(source = "agente.placa", target = "agenteId")
    @Mapping(source = "ciudad.nombre", target = "ciudadId")
    @Mapping(source = "infractor.nombres", target = "infractorId")
    @Mapping(source = "testigo.nombres", target = "testigoId")
    @Mapping(source = "patio.nombre", target = "patioId")
    @Mapping(source = "grua.codigo", target = "gruaId")
    @Mapping(source = "entidad.nombre", target = "entidadId")
    ComparendoDTO toDto(Comparendo comparendo);

    @Mapping(target = "removeInfracciones", ignore = true)
    @Mapping(source = "estadoId", target = "estado")
    @Mapping(source = "vehiculoId", target = "vehiculo")
    @Mapping(source = "licenciaTransitoId", target = "licenciaTransito")
    @Mapping(source = "licenciaConduccionId", target = "licenciaConduccion")
    @Mapping(source = "agenteId", target = "agente")
    @Mapping(source = "ciudadId", target = "ciudad")
    @Mapping(source = "infractorId", target = "infractor")
    @Mapping(source = "testigoId", target = "testigo")
    @Mapping(source = "patioId", target = "patio")
    @Mapping(source = "gruaId", target = "grua")
    @Mapping(source = "entidadId", target = "entidad")
    Comparendo toEntity(ComparendoDTO comparendoDTO);

    default Comparendo fromId(String id) {
        if (id == null) {
            return null;
        }
        Comparendo comparendo = new Comparendo();
        comparendo.setId(id);
        return comparendo;
    }
}
