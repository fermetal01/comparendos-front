package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.PatioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Patio}.
 */
public interface PatioService {

    /**
     * Save a patio.
     *
     * @param patioDTO the entity to save.
     * @return the persisted entity.
     */
    PatioDTO save(PatioDTO patioDTO);

    /**
     * Get all the patios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PatioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" patio.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PatioDTO> findOne(String id);

    /**
     * Delete the "id" patio.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
