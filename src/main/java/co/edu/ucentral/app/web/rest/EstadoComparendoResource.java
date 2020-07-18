package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.EstadoComparendoService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.EstadoComparendoDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.EstadoComparendo}.
 */
@RestController
@RequestMapping("/api")
public class EstadoComparendoResource {

    private final Logger log = LoggerFactory.getLogger(EstadoComparendoResource.class);

    private static final String ENTITY_NAME = "estadoComparendo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadoComparendoService estadoComparendoService;

    public EstadoComparendoResource(EstadoComparendoService estadoComparendoService) {
        this.estadoComparendoService = estadoComparendoService;
    }

    /**
     * {@code POST  /estado-comparendos} : Create a new estadoComparendo.
     *
     * @param estadoComparendoDTO the estadoComparendoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadoComparendoDTO, or with status {@code 400 (Bad Request)} if the estadoComparendo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estado-comparendos")
    public ResponseEntity<EstadoComparendoDTO> createEstadoComparendo(@RequestBody EstadoComparendoDTO estadoComparendoDTO) throws URISyntaxException {
        log.debug("REST request to save EstadoComparendo : {}", estadoComparendoDTO);
        if (estadoComparendoDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadoComparendo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadoComparendoDTO result = estadoComparendoService.save(estadoComparendoDTO);
        return ResponseEntity.created(new URI("/api/estado-comparendos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /estado-comparendos} : Updates an existing estadoComparendo.
     *
     * @param estadoComparendoDTO the estadoComparendoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadoComparendoDTO,
     * or with status {@code 400 (Bad Request)} if the estadoComparendoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadoComparendoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estado-comparendos")
    public ResponseEntity<EstadoComparendoDTO> updateEstadoComparendo(@RequestBody EstadoComparendoDTO estadoComparendoDTO) throws URISyntaxException {
        log.debug("REST request to update EstadoComparendo : {}", estadoComparendoDTO);
        if (estadoComparendoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadoComparendoDTO result = estadoComparendoService.save(estadoComparendoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, estadoComparendoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /estado-comparendos} : get all the estadoComparendos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadoComparendos in body.
     */
    @GetMapping("/estado-comparendos")
    public ResponseEntity<List<EstadoComparendoDTO>> getAllEstadoComparendos(Pageable pageable) {
        log.debug("REST request to get a page of EstadoComparendos");
        Page<EstadoComparendoDTO> page = estadoComparendoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /estado-comparendos/:id} : get the "id" estadoComparendo.
     *
     * @param id the id of the estadoComparendoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadoComparendoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estado-comparendos/{id}")
    public ResponseEntity<EstadoComparendoDTO> getEstadoComparendo(@PathVariable String id) {
        log.debug("REST request to get EstadoComparendo : {}", id);
        Optional<EstadoComparendoDTO> estadoComparendoDTO = estadoComparendoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadoComparendoDTO);
    }

    /**
     * {@code DELETE  /estado-comparendos/:id} : delete the "id" estadoComparendo.
     *
     * @param id the id of the estadoComparendoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estado-comparendos/{id}")
    public ResponseEntity<Void> deleteEstadoComparendo(@PathVariable String id) {
        log.debug("REST request to delete EstadoComparendo : {}", id);
        estadoComparendoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
