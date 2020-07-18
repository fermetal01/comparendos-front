package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.CiudadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Ciudad}.
 */
public interface CiudadService {

    /**
     * Save a ciudad.
     *
     * @param ciudadDTO the entity to save.
     * @return the persisted entity.
     */
    CiudadDTO save(CiudadDTO ciudadDTO);

    /**
     * Get all the ciudads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CiudadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ciudad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CiudadDTO> findOne(String id);

    /**
     * Delete the "id" ciudad.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
