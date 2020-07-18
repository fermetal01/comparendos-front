package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.InfraccionService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.InfraccionDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Infraccion}.
 */
@RestController
@RequestMapping("/api")
public class InfraccionResource {

    private final Logger log = LoggerFactory.getLogger(InfraccionResource.class);

    private static final String ENTITY_NAME = "infraccion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfraccionService infraccionService;

    public InfraccionResource(InfraccionService infraccionService) {
        this.infraccionService = infraccionService;
    }

    /**
     * {@code POST  /infraccions} : Create a new infraccion.
     *
     * @param infraccionDTO the infraccionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new infraccionDTO, or with status {@code 400 (Bad Request)} if the infraccion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/infraccions")
    public ResponseEntity<InfraccionDTO> createInfraccion(@RequestBody InfraccionDTO infraccionDTO) throws URISyntaxException {
        log.debug("REST request to save Infraccion : {}", infraccionDTO);
        if (infraccionDTO.getId() != null) {
            throw new BadRequestAlertException("A new infraccion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InfraccionDTO result = infraccionService.save(infraccionDTO);
        return ResponseEntity.created(new URI("/api/infraccions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /infraccions} : Updates an existing infraccion.
     *
     * @param infraccionDTO the infraccionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infraccionDTO,
     * or with status {@code 400 (Bad Request)} if the infraccionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the infraccionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/infraccions")
    public ResponseEntity<InfraccionDTO> updateInfraccion(@RequestBody InfraccionDTO infraccionDTO) throws URISyntaxException {
        log.debug("REST request to update Infraccion : {}", infraccionDTO);
        if (infraccionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InfraccionDTO result = infraccionService.save(infraccionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, infraccionDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /infraccions} : get all the infraccions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infraccions in body.
     */
    @GetMapping("/infraccions")
    public ResponseEntity<List<InfraccionDTO>> getAllInfraccions(Pageable pageable) {
        log.debug("REST request to get a page of Infraccions");
        Page<InfraccionDTO> page = infraccionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /infraccions/:id} : get the "id" infraccion.
     *
     * @param id the id of the infraccionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infraccionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/infraccions/{id}")
    public ResponseEntity<InfraccionDTO> getInfraccion(@PathVariable String id) {
        log.debug("REST request to get Infraccion : {}", id);
        Optional<InfraccionDTO> infraccionDTO = infraccionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(infraccionDTO);
    }

    /**
     * {@code DELETE  /infraccions/:id} : delete the "id" infraccion.
     *
     * @param id the id of the infraccionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/infraccions/{id}")
    public ResponseEntity<Void> deleteInfraccion(@PathVariable String id) {
        log.debug("REST request to delete Infraccion : {}", id);
        infraccionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
