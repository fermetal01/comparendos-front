package co.edu.ucentral.app.service.mapper;


import co.edu.ucentral.app.domain.*;
import co.edu.ucentral.app.service.dto.CategoriaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categoria} and its DTO {@link CategoriaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoriaMapper extends EntityMapper<CategoriaDTO, Categoria> {


    @Mapping(target = "licencias", ignore = true)
    @Mapping(target = "removeLicencia", ignore = true)
    Categoria toEntity(CategoriaDTO categoriaDTO);

    default Categoria fromId(String id) {
        if (id == null) {
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setId(id);
        return categoria;
    }
}
