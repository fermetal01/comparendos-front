package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.OrganismoTransitoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.OrganismoTransito}.
 */
public interface OrganismoTransitoService {

    /**
     * Save a organismoTransito.
     *
     * @param organismoTransitoDTO the entity to save.
     * @return the persisted entity.
     */
    OrganismoTransitoDTO save(OrganismoTransitoDTO organismoTransitoDTO);

    /**
     * Get all the organismoTransitos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrganismoTransitoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" organismoTransito.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrganismoTransitoDTO> findOne(String id);

    /**
     * Delete the "id" organismoTransito.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
