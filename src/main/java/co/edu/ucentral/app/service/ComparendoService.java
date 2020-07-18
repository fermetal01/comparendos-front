package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.ComparendoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Comparendo}.
 */
public interface ComparendoService {

    /**
     * Save a comparendo.
     *
     * @param comparendoDTO the entity to save.
     * @return the persisted entity.
     */
    ComparendoDTO save(ComparendoDTO comparendoDTO);

    /**
     * Get all the comparendos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ComparendoDTO> findAll(Pageable pageable);

    /**
     * Get all the comparendos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ComparendoDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" comparendo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComparendoDTO> findOne(String id);

    /**
     * Delete the "id" comparendo.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
