package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.OrganismoTransitoService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.OrganismoTransitoDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.OrganismoTransito}.
 */
@RestController
@RequestMapping("/api")
public class OrganismoTransitoResource {

    private final Logger log = LoggerFactory.getLogger(OrganismoTransitoResource.class);

    private static final String ENTITY_NAME = "organismoTransito";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganismoTransitoService organismoTransitoService;

    public OrganismoTransitoResource(OrganismoTransitoService organismoTransitoService) {
        this.organismoTransitoService = organismoTransitoService;
    }

    /**
     * {@code POST  /organismo-transitos} : Create a new organismoTransito.
     *
     * @param organismoTransitoDTO the organismoTransitoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organismoTransitoDTO, or with status {@code 400 (Bad Request)} if the organismoTransito has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organismo-transitos")
    public ResponseEntity<OrganismoTransitoDTO> createOrganismoTransito(@RequestBody OrganismoTransitoDTO organismoTransitoDTO) throws URISyntaxException {
        log.debug("REST request to save OrganismoTransito : {}", organismoTransitoDTO);
        if (organismoTransitoDTO.getId() != null) {
            throw new BadRequestAlertException("A new organismoTransito cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganismoTransitoDTO result = organismoTransitoService.save(organismoTransitoDTO);
        return ResponseEntity.created(new URI("/api/organismo-transitos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /organismo-transitos} : Updates an existing organismoTransito.
     *
     * @param organismoTransitoDTO the organismoTransitoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organismoTransitoDTO,
     * or with status {@code 400 (Bad Request)} if the organismoTransitoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organismoTransitoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organismo-transitos")
    public ResponseEntity<OrganismoTransitoDTO> updateOrganismoTransito(@RequestBody OrganismoTransitoDTO organismoTransitoDTO) throws URISyntaxException {
        log.debug("REST request to update OrganismoTransito : {}", organismoTransitoDTO);
        if (organismoTransitoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganismoTransitoDTO result = organismoTransitoService.save(organismoTransitoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, organismoTransitoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /organismo-transitos} : get all the organismoTransitos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organismoTransitos in body.
     */
    @GetMapping("/organismo-transitos")
    public ResponseEntity<List<OrganismoTransitoDTO>> getAllOrganismoTransitos(Pageable pageable) {
        log.debug("REST request to get a page of OrganismoTransitos");
        Page<OrganismoTransitoDTO> page = organismoTransitoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /organismo-transitos/:id} : get the "id" organismoTransito.
     *
     * @param id the id of the organismoTransitoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organismoTransitoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organismo-transitos/{id}")
    public ResponseEntity<OrganismoTransitoDTO> getOrganismoTransito(@PathVariable String id) {
        log.debug("REST request to get OrganismoTransito : {}", id);
        Optional<OrganismoTransitoDTO> organismoTransitoDTO = organismoTransitoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organismoTransitoDTO);
    }

    /**
     * {@code DELETE  /organismo-transitos/:id} : delete the "id" organismoTransito.
     *
     * @param id the id of the organismoTransitoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organismo-transitos/{id}")
    public ResponseEntity<Void> deleteOrganismoTransito(@PathVariable String id) {
        log.debug("REST request to delete OrganismoTransito : {}", id);
        organismoTransitoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
