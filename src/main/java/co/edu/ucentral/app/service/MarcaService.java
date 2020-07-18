package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.MarcaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.Marca}.
 */
public interface MarcaService {

    /**
     * Save a marca.
     *
     * @param marcaDTO the entity to save.
     * @return the persisted entity.
     */
    MarcaDTO save(MarcaDTO marcaDTO);

    /**
     * Get all the marcas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MarcaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" marca.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MarcaDTO> findOne(String id);

    /**
     * Delete the "id" marca.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
