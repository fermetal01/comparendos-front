package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.DepartamentoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Departamento}.
 */
public interface DepartamentoService {

    /**
     * Save a departamento.
     *
     * @param departamentoDTO the entity to save.
     * @return the persisted entity.
     */
    DepartamentoDTO save(DepartamentoDTO departamentoDTO);

    /**
     * Get all the departamentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DepartamentoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" departamento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepartamentoDTO> findOne(String id);

    /**
     * Delete the "id" departamento.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
