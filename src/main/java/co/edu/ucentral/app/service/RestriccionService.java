package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.RestriccionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Restriccion}.
 */
public interface RestriccionService {

    /**
     * Save a restriccion.
     *
     * @param restriccionDTO the entity to save.
     * @return the persisted entity.
     */
    RestriccionDTO save(RestriccionDTO restriccionDTO);

    /**
     * Get all the restriccions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RestriccionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" restriccion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RestriccionDTO> findOne(String id);

    /**
     * Delete the "id" restriccion.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
