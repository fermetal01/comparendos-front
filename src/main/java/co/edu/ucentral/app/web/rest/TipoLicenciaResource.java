package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.TipoLicenciaService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.TipoLicenciaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.edu.ucentral.app.domain.TipoLicencia}.
 */
@RestController
@RequestMapping("/api")
public class TipoLicenciaResource {

    private final Logger log = LoggerFactory.getLogger(TipoLicenciaResource.class);

    private static final String ENTITY_NAME = "tipoLicencia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoLicenciaService tipoLicenciaService;

    public TipoLicenciaResource(TipoLicenciaService tipoLicenciaService) {
        this.tipoLicenciaService = tipoLicenciaService;
    }

    /**
     * {@code POST  /tipo-licencias} : Create a new tipoLicencia.
     *
     * @param tipoLicenciaDTO the tipoLicenciaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoLicenciaDTO, or with status {@code 400 (Bad Request)} if the tipoLicencia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-licencias")
    public ResponseEntity<TipoLicenciaDTO> createTipoLicencia(@RequestBody TipoLicenciaDTO tipoLicenciaDTO) throws URISyntaxException {
        log.debug("REST request to save TipoLicencia : {}", tipoLicenciaDTO);
        if (tipoLicenciaDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoLicencia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoLicenciaDTO result = tipoLicenciaService.save(tipoLicenciaDTO);
        return ResponseEntity.created(new URI("/api/tipo-licencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-licencias} : Updates an existing tipoLicencia.
     *
     * @param tipoLicenciaDTO the tipoLicenciaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoLicenciaDTO,
     * or with status {@code 400 (Bad Request)} if the tipoLicenciaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoLicenciaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-licencias")
    public ResponseEntity<TipoLicenciaDTO> updateTipoLicencia(@RequestBody TipoLicenciaDTO tipoLicenciaDTO) throws URISyntaxException {
        log.debug("REST request to update TipoLicencia : {}", tipoLicenciaDTO);
        if (tipoLicenciaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoLicenciaDTO result = tipoLicenciaService.save(tipoLicenciaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoLicenciaDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-licencias} : get all the tipoLicencias.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoLicencias in body.
     */
    @GetMapping("/tipo-licencias")
    public ResponseEntity<List<TipoLicenciaDTO>> getAllTipoLicencias(Pageable pageable) {
        log.debug("REST request to get a page of TipoLicencias");
        Page<TipoLicenciaDTO> page = tipoLicenciaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tipo-licencias/:id} : get the "id" tipoLicencia.
     *
     * @param id the id of the tipoLicenciaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoLicenciaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-licencias/{id}")
    public ResponseEntity<TipoLicenciaDTO> getTipoLicencia(@PathVariable String id) {
        log.debug("REST request to get TipoLicencia : {}", id);
        Optional<TipoLicenciaDTO> tipoLicenciaDTO = tipoLicenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoLicenciaDTO);
    }

    /**
     * {@code DELETE  /tipo-licencias/:id} : delete the "id" tipoLicencia.
     *
     * @param id the id of the tipoLicenciaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-licencias/{id}")
    public ResponseEntity<Void> deleteTipoLicencia(@PathVariable String id) {
        log.debug("REST request to delete TipoLicencia : {}", id);
        tipoLicenciaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
