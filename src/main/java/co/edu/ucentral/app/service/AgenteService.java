package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.AgenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Agente}.
 */
public interface AgenteService {

    /**
     * Save a agente.
     *
     * @param agenteDTO the entity to save.
     * @return the persisted entity.
     */
    AgenteDTO save(AgenteDTO agenteDTO);

    /**
     * Get all the agentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AgenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" agente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AgenteDTO> findOne(String id);

    /**
     * Delete the "id" agente.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
