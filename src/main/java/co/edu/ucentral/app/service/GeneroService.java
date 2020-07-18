package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.GeneroDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Genero}.
 */
public interface GeneroService {

    /**
     * Save a genero.
     *
     * @param generoDTO the entity to save.
     * @return the persisted entity.
     */
    GeneroDTO save(GeneroDTO generoDTO);

    /**
     * Get all the generos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GeneroDTO> findAll(Pageable pageable);


    /**
     * Get the "id" genero.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GeneroDTO> findOne(String id);

    /**
     * Delete the "id" genero.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
