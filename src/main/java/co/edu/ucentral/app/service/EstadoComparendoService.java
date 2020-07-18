package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.EstadoComparendoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.EstadoComparendo}.
 */
public interface EstadoComparendoService {

    /**
     * Save a estadoComparendo.
     *
     * @param estadoComparendoDTO the entity to save.
     * @return the persisted entity.
     */
    EstadoComparendoDTO save(EstadoComparendoDTO estadoComparendoDTO);

    /**
     * Get all the estadoComparendos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EstadoComparendoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" estadoComparendo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadoComparendoDTO> findOne(String id);

    /**
     * Delete the "id" estadoComparendo.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
