package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.VehiculoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Vehiculo}.
 */
public interface VehiculoService {

    /**
     * Save a vehiculo.
     *
     * @param vehiculoDTO the entity to save.
     * @return the persisted entity.
     */
    VehiculoDTO save(VehiculoDTO vehiculoDTO);

    /**
     * Get all the vehiculos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VehiculoDTO> findAll(Pageable pageable);

    /**
     * Get all the vehiculos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<VehiculoDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" vehiculo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VehiculoDTO> findOne(String id);

    /**
     * Delete the "id" vehiculo.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
