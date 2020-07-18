package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.PersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Persona} and its DTO {@link PersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {CiudadMapper.class, GeneroMapper.class, TipoSanguineoMapper.class, TipoIdentificacionMapper.class, LicenciaMapper.class})
public interface PersonaMapper extends EntityMapper<PersonaDTO, Persona> {

    @Mapping(source = "ciudad.id", target = "ciudadId")
    @Mapping(source = "genero.id", target = "generoId")
    @Mapping(source = "tipoSanguineo.id", target = "tipoSanguineoId")
    @Mapping(source = "tipoId.id", target = "tipoIdId")
    @Mapping(source = "licencia.id", target = "licenciaId")
    PersonaDTO toDto(Persona persona);

    @Mapping(target = "usuarios", ignore = true)
    @Mapping(target = "removeUsuario", ignore = true)
    @Mapping(target = "agentes", ignore = true)
    @Mapping(target = "removeAgente", ignore = true)
    @Mapping(target = "comparendos", ignore = true)
    @Mapping(target = "removeComparendo", ignore = true)
    @Mapping(source = "ciudadId", target = "ciudad")
    @Mapping(source = "generoId", target = "genero")
    @Mapping(source = "tipoSanguineoId", target = "tipoSanguineo")
    @Mapping(source = "tipoIdId", target = "tipoId")
    @Mapping(source = "licenciaId", target = "licencia")
    @Mapping(target = "vehiculos", ignore = true)
    @Mapping(target = "removeVehiculo", ignore = true)
    Persona toEntity(PersonaDTO personaDTO);

    default Persona fromId(String id) {
        if (id == null) {
            return null;
        }
        Persona persona = new Persona();
        persona.setId(id);
        return persona;
    }
}
