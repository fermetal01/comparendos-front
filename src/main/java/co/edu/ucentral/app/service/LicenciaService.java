package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.LicenciaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Licencia}.
 */
public interface LicenciaService {

    /**
     * Save a licencia.
     *
     * @param licenciaDTO the entity to save.
     * @return the persisted entity.
     */
    LicenciaDTO save(LicenciaDTO licenciaDTO);

    /**
     * Get all the licencias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LicenciaDTO> findAll(Pageable pageable);

    /**
     * Get all the licencias with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<LicenciaDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" licencia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LicenciaDTO> findOne(String id);

    /**
     * Delete the "id" licencia.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
