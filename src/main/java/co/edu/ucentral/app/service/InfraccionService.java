package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.InfraccionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Infraccion}.
 */
public interface InfraccionService {

    /**
     * Save a infraccion.
     *
     * @param infraccionDTO the entity to save.
     * @return the persisted entity.
     */
    InfraccionDTO save(InfraccionDTO infraccionDTO);

    /**
     * Get all the infraccions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InfraccionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" infraccion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InfraccionDTO> findOne(String id);

    /**
     * Delete the "id" infraccion.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
