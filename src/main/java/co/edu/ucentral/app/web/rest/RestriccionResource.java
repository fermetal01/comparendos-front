package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.RestriccionService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.RestriccionDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Restriccion}.
 */
@RestController
@RequestMapping("/api")
public class RestriccionResource {

    private final Logger log = LoggerFactory.getLogger(RestriccionResource.class);

    private static final String ENTITY_NAME = "restriccion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestriccionService restriccionService;

    public RestriccionResource(RestriccionService restriccionService) {
        this.restriccionService = restriccionService;
    }

    /**
     * {@code POST  /restriccions} : Create a new restriccion.
     *
     * @param restriccionDTO the restriccionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restriccionDTO, or with status {@code 400 (Bad Request)} if the restriccion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restriccions")
    public ResponseEntity<RestriccionDTO> createRestriccion(@RequestBody RestriccionDTO restriccionDTO) throws URISyntaxException {
        log.debug("REST request to save Restriccion : {}", restriccionDTO);
        if (restriccionDTO.getId() != null) {
            throw new BadRequestAlertException("A new restriccion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestriccionDTO result = restriccionService.save(restriccionDTO);
        return ResponseEntity.created(new URI("/api/restriccions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /restriccions} : Updates an existing restriccion.
     *
     * @param restriccionDTO the restriccionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restriccionDTO,
     * or with status {@code 400 (Bad Request)} if the restriccionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restriccionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restriccions")
    public ResponseEntity<RestriccionDTO> updateRestriccion(@RequestBody RestriccionDTO restriccionDTO) throws URISyntaxException {
        log.debug("REST request to update Restriccion : {}", restriccionDTO);
        if (restriccionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RestriccionDTO result = restriccionService.save(restriccionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, restriccionDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /restriccions} : get all the restriccions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restriccions in body.
     */
    @GetMapping("/restriccions")
    public ResponseEntity<List<RestriccionDTO>> getAllRestriccions(Pageable pageable) {
        log.debug("REST request to get a page of Restriccions");
        Page<RestriccionDTO> page = restriccionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restriccions/:id} : get the "id" restriccion.
     *
     * @param id the id of the restriccionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restriccionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restriccions/{id}")
    public ResponseEntity<RestriccionDTO> getRestriccion(@PathVariable String id) {
        log.debug("REST request to get Restriccion : {}", id);
        Optional<RestriccionDTO> restriccionDTO = restriccionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restriccionDTO);
    }

    /**
     * {@code DELETE  /restriccions/:id} : delete the "id" restriccion.
     *
     * @param id the id of the restriccionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restriccions/{id}")
    public ResponseEntity<Void> deleteRestriccion(@PathVariable String id) {
        log.debug("REST request to delete Restriccion : {}", id);
        restriccionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
