package co.edu.ucentral.app.service;

import co.edu.ucentral.app.service.dto.TipoLicenciaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link co.edu.ucentral.app.domain.TipoLicencia}.
 */
public interface TipoLicenciaService {

    /**
     * Save a tipoLicencia.
     *
     * @param tipoLicenciaDTO the entity to save.
     * @return the persisted entity.
     */
    TipoLicenciaDTO save(TipoLicenciaDTO tipoLicenciaDTO);

    /**
     * Get all the tipoLicencias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoLicenciaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoLicencia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoLicenciaDTO> findOne(String id);

    /**
     * Delete the "id" tipoLicencia.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
