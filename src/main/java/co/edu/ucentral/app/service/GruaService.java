package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.GruaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Grua}.
 */
public interface GruaService {

    /**
     * Save a grua.
     *
     * @param gruaDTO the entity to save.
     * @return the persisted entity.
     */
    GruaDTO save(GruaDTO gruaDTO);

    /**
     * Get all the gruas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GruaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" grua.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GruaDTO> findOne(String id);

    /**
     * Delete the "id" grua.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
