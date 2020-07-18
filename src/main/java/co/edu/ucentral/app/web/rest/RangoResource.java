package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.RangoService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.RangoDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Rango}.
 */
@RestController
@RequestMapping("/api")
public class RangoResource {

    private final Logger log = LoggerFactory.getLogger(RangoResource.class);

    private static final String ENTITY_NAME = "rango";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RangoService rangoService;

    public RangoResource(RangoService rangoService) {
        this.rangoService = rangoService;
    }

    /**
     * {@code POST  /rangos} : Create a new rango.
     *
     * @param rangoDTO the rangoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rangoDTO, or with status {@code 400 (Bad Request)} if the rango has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rangos")
    public ResponseEntity<RangoDTO> createRango(@RequestBody RangoDTO rangoDTO) throws URISyntaxException {
        log.debug("REST request to save Rango : {}", rangoDTO);
        if (rangoDTO.getId() != null) {
            throw new BadRequestAlertException("A new rango cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RangoDTO result = rangoService.save(rangoDTO);
        return ResponseEntity.created(new URI("/api/rangos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /rangos} : Updates an existing rango.
     *
     * @param rangoDTO the rangoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rangoDTO,
     * or with status {@code 400 (Bad Request)} if the rangoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rangoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rangos")
    public ResponseEntity<RangoDTO> updateRango(@RequestBody RangoDTO rangoDTO) throws URISyntaxException {
        log.debug("REST request to update Rango : {}", rangoDTO);
        if (rangoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RangoDTO result = rangoService.save(rangoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rangoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /rangos} : get all the rangos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rangos in body.
     */
    @GetMapping("/rangos")
    public ResponseEntity<List<RangoDTO>> getAllRangos(Pageable pageable) {
        log.debug("REST request to get a page of Rangos");
        Page<RangoDTO> page = rangoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rangos/:id} : get the "id" rango.
     *
     * @param id the id of the rangoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rangoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rangos/{id}")
    public ResponseEntity<RangoDTO> getRango(@PathVariable String id) {
        log.debug("REST request to get Rango : {}", id);
        Optional<RangoDTO> rangoDTO = rangoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rangoDTO);
    }

    /**
     * {@code DELETE  /rangos/:id} : delete the "id" rango.
     *
     * @param id the id of the rangoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rangos/{id}")
    public ResponseEntity<Void> deleteRango(@PathVariable String id) {
        log.debug("REST request to delete Rango : {}", id);
        rangoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
