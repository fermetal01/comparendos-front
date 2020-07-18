package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.TipoIdentificacionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.TipoIdentificacion}.
 */
public interface TipoIdentificacionService {

    /**
     * Save a tipoIdentificacion.
     *
     * @param tipoIdentificacionDTO the entity to save.
     * @return the persisted entity.
     */
    TipoIdentificacionDTO save(TipoIdentificacionDTO tipoIdentificacionDTO);

    /**
     * Get all the tipoIdentificacions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoIdentificacionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoIdentificacion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoIdentificacionDTO> findOne(String id);

    /**
     * Delete the "id" tipoIdentificacion.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
