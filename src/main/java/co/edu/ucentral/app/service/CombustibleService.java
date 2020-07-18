package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.CombustibleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Combustible}.
 */
public interface CombustibleService {

    /**
     * Save a combustible.
     *
     * @param combustibleDTO the entity to save.
     * @return the persisted entity.
     */
    CombustibleDTO save(CombustibleDTO combustibleDTO);

    /**
     * Get all the combustibles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CombustibleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" combustible.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CombustibleDTO> findOne(String id);

    /**
     * Delete the "id" combustible.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
