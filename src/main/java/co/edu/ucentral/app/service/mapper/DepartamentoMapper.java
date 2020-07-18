package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.DepartamentoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Departamento} and its DTO {@link DepartamentoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DepartamentoMapper extends EntityMapper<DepartamentoDTO, Departamento> {


    @Mapping(target = "ciudads", ignore = true)
    @Mapping(target = "removeCiudad", ignore = true)
    Departamento toEntity(DepartamentoDTO departamentoDTO);

    default Departamento fromId(String id) {
        if (id == null) {
            return null;
        }
        Departamento departamento = new Departamento();
        departamento.setId(id);
        return departamento;
    }
}
