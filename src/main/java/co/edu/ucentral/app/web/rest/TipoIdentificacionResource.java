package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.TipoIdentificacionService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.TipoIdentificacionDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.TipoIdentificacion}.
 */
@RestController
@RequestMapping("/api")
public class TipoIdentificacionResource {

    private final Logger log = LoggerFactory.getLogger(TipoIdentificacionResource.class);

    private static final String ENTITY_NAME = "tipoIdentificacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoIdentificacionService tipoIdentificacionService;

    public TipoIdentificacionResource(TipoIdentificacionService tipoIdentificacionService) {
        this.tipoIdentificacionService = tipoIdentificacionService;
    }

    /**
     * {@code POST  /tipo-identificacions} : Create a new tipoIdentificacion.
     *
     * @param tipoIdentificacionDTO the tipoIdentificacionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoIdentificacionDTO, or with status {@code 400 (Bad Request)} if the tipoIdentificacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-identificacions")
    public ResponseEntity<TipoIdentificacionDTO> createTipoIdentificacion(@RequestBody TipoIdentificacionDTO tipoIdentificacionDTO) throws URISyntaxException {
        log.debug("REST request to save TipoIdentificacion : {}", tipoIdentificacionDTO);
        if (tipoIdentificacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoIdentificacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoIdentificacionDTO result = tipoIdentificacionService.save(tipoIdentificacionDTO);
        return ResponseEntity.created(new URI("/api/tipo-identificacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-identificacions} : Updates an existing tipoIdentificacion.
     *
     * @param tipoIdentificacionDTO the tipoIdentificacionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoIdentificacionDTO,
     * or with status {@code 400 (Bad Request)} if the tipoIdentificacionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoIdentificacionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-identificacions")
    public ResponseEntity<TipoIdentificacionDTO> updateTipoIdentificacion(@RequestBody TipoIdentificacionDTO tipoIdentificacionDTO) throws URISyntaxException {
        log.debug("REST request to update TipoIdentificacion : {}", tipoIdentificacionDTO);
        if (tipoIdentificacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoIdentificacionDTO result = tipoIdentificacionService.save(tipoIdentificacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoIdentificacionDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-identificacions} : get all the tipoIdentificacions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoIdentificacions in body.
     */
    @GetMapping("/tipo-identificacions")
    public ResponseEntity<List<TipoIdentificacionDTO>> getAllTipoIdentificacions(Pageable pageable) {
        log.debug("REST request to get a page of TipoIdentificacions");
        Page<TipoIdentificacionDTO> page = tipoIdentificacionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tipo-identificacions/:id} : get the "id" tipoIdentificacion.
     *
     * @param id the id of the tipoIdentificacionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoIdentificacionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-identificacions/{id}")
    public ResponseEntity<TipoIdentificacionDTO> getTipoIdentificacion(@PathVariable String id) {
        log.debug("REST request to get TipoIdentificacion : {}", id);
        Optional<TipoIdentificacionDTO> tipoIdentificacionDTO = tipoIdentificacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoIdentificacionDTO);
    }

    /**
     * {@code DELETE  /tipo-identificacions/:id} : delete the "id" tipoIdentificacion.
     *
     * @param id the id of the tipoIdentificacionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-identificacions/{id}")
    public ResponseEntity<Void> deleteTipoIdentificacion(@PathVariable String id) {
        log.debug("REST request to delete TipoIdentificacion : {}", id);
        tipoIdentificacionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
