package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.ClaseVehiculoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.ClaseVehiculo}.
 */
public interface ClaseVehiculoService {

    /**
     * Save a claseVehiculo.
     *
     * @param claseVehiculoDTO the entity to save.
     * @return the persisted entity.
     */
    ClaseVehiculoDTO save(ClaseVehiculoDTO claseVehiculoDTO);

    /**
     * Get all the claseVehiculos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaseVehiculoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" claseVehiculo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaseVehiculoDTO> findOne(String id);

    /**
     * Delete the "id" claseVehiculo.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
