package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.ComparendoService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.ComparendoDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.edu.ucentral.app.domain.Comparendo}.
 */
@RestController
@RequestMapping("/api")
public class ComparendoResource {

    private final Logger log = LoggerFactory.getLogger(ComparendoResource.class);

    private static final String ENTITY_NAME = "comparendo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComparendoService comparendoService;

    public ComparendoResource(ComparendoService comparendoService) {
        this.comparendoService = comparendoService;
    }

    /**
     * {@code POST  /comparendos} : Create a new comparendo.
     *
     * @param comparendoDTO the comparendoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comparendoDTO, or with status {@code 400 (Bad Request)} if the comparendo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comparendos")
    public ResponseEntity<ComparendoDTO> createComparendo(@Valid @RequestBody ComparendoDTO comparendoDTO) throws URISyntaxException {
        log.debug("REST request to save Comparendo : {}", comparendoDTO);
        if (comparendoDTO.getId() != null) {
            throw new BadRequestAlertException("A new comparendo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComparendoDTO result = comparendoService.save(comparendoDTO);
        return ResponseEntity.created(new URI("/api/comparendos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /comparendos} : Updates an existing comparendo.
     *
     * @param comparendoDTO the comparendoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comparendoDTO,
     * or with status {@code 400 (Bad Request)} if the comparendoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comparendoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comparendos")
    public ResponseEntity<ComparendoDTO> updateComparendo(@Valid @RequestBody ComparendoDTO comparendoDTO) throws URISyntaxException {
        log.debug("REST request to update Comparendo : {}", comparendoDTO);
        if (comparendoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComparendoDTO result = comparendoService.save(comparendoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comparendoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /comparendos} : get all the comparendos.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comparendos in body.
     */
    @GetMapping("/comparendos")
    public ResponseEntity<List<ComparendoDTO>> getAllComparendos(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Comparendos");
        Page<ComparendoDTO> page;
        if (eagerload) {
            page = comparendoService.findAllWithEagerRelationships(pageable);
        } else {
            page = comparendoService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /comparendos/:id} : get the "id" comparendo.
     *
     * @param id the id of the comparendoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comparendoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comparendos/{id}")
    public ResponseEntity<ComparendoDTO> getComparendo(@PathVariable String id) {
        log.debug("REST request to get Comparendo : {}", id);
        Optional<ComparendoDTO> comparendoDTO = comparendoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comparendoDTO);
    }

    /**
     * {@code DELETE  /comparendos/:id} : delete the "id" comparendo.
     *
     * @param id the id of the comparendoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comparendos/{id}")
    public ResponseEntity<Void> deleteComparendo(@PathVariable String id) {
        log.debug("REST request to delete Comparendo : {}", id);
        comparendoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
