package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.VehiculoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vehiculo} and its DTO {@link VehiculoDTO}.
 */
@Mapper(componentModel = "spring", uses = {RestriccionMapper.class, PersonaMapper.class, EntidadMapper.class, MarcaMapper.class, ServicioMapper.class, ClaseVehiculoMapper.class, CombustibleMapper.class, OrganismoTransitoMapper.class, LicenciaMapper.class})
public interface VehiculoMapper extends EntityMapper<VehiculoDTO, Vehiculo> {

    @Mapping(source = "marca.id", target = "marcaId")
    @Mapping(source = "servicio.id", target = "servicioId")
    @Mapping(source = "clase.id", target = "claseId")
    @Mapping(source = "combustible.id", target = "combustibleId")
    @Mapping(source = "organismoTransito.id", target = "organismoTransitoId")
    @Mapping(source = "licenciaTransito.id", target = "licenciaTransitoId")
    @Mapping(source = "persona.id", target = "personaId")
    VehiculoDTO toDto(Vehiculo vehiculo);

    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    @Mapping(target = "gruas", ignore = true)
    @Mapping(target = "removeGrua", ignore = true)
    @Mapping(target = "removeRestriccion", ignore = true)
    @Mapping(target = "removePersona", ignore = true)
    @Mapping(target = "removeEntidad", ignore = true)
    @Mapping(source = "marcaId", target = "marca")
    @Mapping(source = "servicioId", target = "servicio")
    @Mapping(source = "claseId", target = "clase")
    @Mapping(source = "combustibleId", target = "combustible")
    @Mapping(source = "organismoTransitoId", target = "organismoTransito")
    @Mapping(source = "licenciaTransitoId", target = "licenciaTransito")
    @Mapping(source = "personaId", target = "persona")
    Vehiculo toEntity(VehiculoDTO vehiculoDTO);

    default Vehiculo fromId(String id) {
        if (id == null) {
            return null;
        }
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(id);
        return vehiculo;
    }
}
