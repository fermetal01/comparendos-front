package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.CombustibleService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.CombustibleDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Combustible}.
 */
@RestController
@RequestMapping("/api")
public class CombustibleResource {

    private final Logger log = LoggerFactory.getLogger(CombustibleResource.class);

    private static final String ENTITY_NAME = "combustible";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CombustibleService combustibleService;

    public CombustibleResource(CombustibleService combustibleService) {
        this.combustibleService = combustibleService;
    }

    /**
     * {@code POST  /combustibles} : Create a new combustible.
     *
     * @param combustibleDTO the combustibleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new combustibleDTO, or with status {@code 400 (Bad Request)} if the combustible has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/combustibles")
    public ResponseEntity<CombustibleDTO> createCombustible(@RequestBody CombustibleDTO combustibleDTO) throws URISyntaxException {
        log.debug("REST request to save Combustible : {}", combustibleDTO);
        if (combustibleDTO.getId() != null) {
            throw new BadRequestAlertException("A new combustible cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CombustibleDTO result = combustibleService.save(combustibleDTO);
        return ResponseEntity.created(new URI("/api/combustibles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /combustibles} : Updates an existing combustible.
     *
     * @param combustibleDTO the combustibleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated combustibleDTO,
     * or with status {@code 400 (Bad Request)} if the combustibleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the combustibleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/combustibles")
    public ResponseEntity<CombustibleDTO> updateCombustible(@RequestBody CombustibleDTO combustibleDTO) throws URISyntaxException {
        log.debug("REST request to update Combustible : {}", combustibleDTO);
        if (combustibleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CombustibleDTO result = combustibleService.save(combustibleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, combustibleDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /combustibles} : get all the combustibles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of combustibles in body.
     */
    @GetMapping("/combustibles")
    public ResponseEntity<List<CombustibleDTO>> getAllCombustibles(Pageable pageable) {
        log.debug("REST request to get a page of Combustibles");
        Page<CombustibleDTO> page = combustibleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /combustibles/:id} : get the "id" combustible.
     *
     * @param id the id of the combustibleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the combustibleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/combustibles/{id}")
    public ResponseEntity<CombustibleDTO> getCombustible(@PathVariable String id) {
        log.debug("REST request to get Combustible : {}", id);
        Optional<CombustibleDTO> combustibleDTO = combustibleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(combustibleDTO);
    }

    /**
     * {@code DELETE  /combustibles/:id} : delete the "id" combustible.
     *
     * @param id the id of the combustibleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/combustibles/{id}")
    public ResponseEntity<Void> deleteCombustible(@PathVariable String id) {
        log.debug("REST request to delete Combustible : {}", id);
        combustibleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
