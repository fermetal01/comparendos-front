package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.TipoSanguineoService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.TipoSanguineoDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.TipoSanguineo}.
 */
@RestController
@RequestMapping("/api")
public class TipoSanguineoResource {

    private final Logger log = LoggerFactory.getLogger(TipoSanguineoResource.class);

    private static final String ENTITY_NAME = "tipoSanguineo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoSanguineoService tipoSanguineoService;

    public TipoSanguineoResource(TipoSanguineoService tipoSanguineoService) {
        this.tipoSanguineoService = tipoSanguineoService;
    }

    /**
     * {@code POST  /tipo-sanguineos} : Create a new tipoSanguineo.
     *
     * @param tipoSanguineoDTO the tipoSanguineoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoSanguineoDTO, or with status {@code 400 (Bad Request)} if the tipoSanguineo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-sanguineos")
    public ResponseEntity<TipoSanguineoDTO> createTipoSanguineo(@RequestBody TipoSanguineoDTO tipoSanguineoDTO) throws URISyntaxException {
        log.debug("REST request to save TipoSanguineo : {}", tipoSanguineoDTO);
        if (tipoSanguineoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoSanguineo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoSanguineoDTO result = tipoSanguineoService.save(tipoSanguineoDTO);
        return ResponseEntity.created(new URI("/api/tipo-sanguineos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-sanguineos} : Updates an existing tipoSanguineo.
     *
     * @param tipoSanguineoDTO the tipoSanguineoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoSanguineoDTO,
     * or with status {@code 400 (Bad Request)} if the tipoSanguineoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoSanguineoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-sanguineos")
    public ResponseEntity<TipoSanguineoDTO> updateTipoSanguineo(@RequestBody TipoSanguineoDTO tipoSanguineoDTO) throws URISyntaxException {
        log.debug("REST request to update TipoSanguineo : {}", tipoSanguineoDTO);
        if (tipoSanguineoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoSanguineoDTO result = tipoSanguineoService.save(tipoSanguineoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoSanguineoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-sanguineos} : get all the tipoSanguineos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoSanguineos in body.
     */
    @GetMapping("/tipo-sanguineos")
    public ResponseEntity<List<TipoSanguineoDTO>> getAllTipoSanguineos(Pageable pageable) {
        log.debug("REST request to get a page of TipoSanguineos");
        Page<TipoSanguineoDTO> page = tipoSanguineoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tipo-sanguineos/:id} : get the "id" tipoSanguineo.
     *
     * @param id the id of the tipoSanguineoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoSanguineoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-sanguineos/{id}")
    public ResponseEntity<TipoSanguineoDTO> getTipoSanguineo(@PathVariable String id) {
        log.debug("REST request to get TipoSanguineo : {}", id);
        Optional<TipoSanguineoDTO> tipoSanguineoDTO = tipoSanguineoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoSanguineoDTO);
    }

    /**
     * {@code DELETE  /tipo-sanguineos/:id} : delete the "id" tipoSanguineo.
     *
     * @param id the id of the tipoSanguineoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-sanguineos/{id}")
    public ResponseEntity<Void> deleteTipoSanguineo(@PathVariable String id) {
        log.debug("REST request to delete TipoSanguineo : {}", id);
        tipoSanguineoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
