package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.ServicioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Servicio}.
 */
public interface ServicioService {

    /**
     * Save a servicio.
     *
     * @param servicioDTO the entity to save.
     * @return the persisted entity.
     */
    ServicioDTO save(ServicioDTO servicioDTO);

    /**
     * Get all the servicios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServicioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" servicio.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServicioDTO> findOne(String id);

    /**
     * Delete the "id" servicio.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
