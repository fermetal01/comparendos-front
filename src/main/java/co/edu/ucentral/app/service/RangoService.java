package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.RangoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Rango}.
 */
public interface RangoService {

    /**
     * Save a rango.
     *
     * @param rangoDTO the entity to save.
     * @return the persisted entity.
     */
    RangoDTO save(RangoDTO rangoDTO);

    /**
     * Get all the rangos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RangoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" rango.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RangoDTO> findOne(String id);

    /**
     * Delete the "id" rango.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
