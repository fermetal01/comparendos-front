package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.LicenciaService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.LicenciaDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Licencia}.
 */
@RestController
@RequestMapping("/api")
public class LicenciaResource {

    private final Logger log = LoggerFactory.getLogger(LicenciaResource.class);

    private static final String ENTITY_NAME = "licencia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LicenciaService licenciaService;

    public LicenciaResource(LicenciaService licenciaService) {
        this.licenciaService = licenciaService;
    }

    /**
     * {@code POST  /licencias} : Create a new licencia.
     *
     * @param licenciaDTO the licenciaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new licenciaDTO, or with status {@code 400 (Bad Request)} if the licencia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/licencias")
    public ResponseEntity<LicenciaDTO> createLicencia(@Valid @RequestBody LicenciaDTO licenciaDTO) throws URISyntaxException {
        log.debug("REST request to save Licencia : {}", licenciaDTO);
        if (licenciaDTO.getId() != null) {
            throw new BadRequestAlertException("A new licencia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LicenciaDTO result = licenciaService.save(licenciaDTO);
        return ResponseEntity.created(new URI("/api/licencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /licencias} : Updates an existing licencia.
     *
     * @param licenciaDTO the licenciaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated licenciaDTO,
     * or with status {@code 400 (Bad Request)} if the licenciaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the licenciaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/licencias")
    public ResponseEntity<LicenciaDTO> updateLicencia(@Valid @RequestBody LicenciaDTO licenciaDTO) throws URISyntaxException {
        log.debug("REST request to update Licencia : {}", licenciaDTO);
        if (licenciaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LicenciaDTO result = licenciaService.save(licenciaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, licenciaDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /licencias} : get all the licencias.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of licencias in body.
     */
    @GetMapping("/licencias")
    public ResponseEntity<List<LicenciaDTO>> getAllLicencias(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Licencias");
        Page<LicenciaDTO> page;
        if (eagerload) {
            page = licenciaService.findAllWithEagerRelationships(pageable);
        } else {
            page = licenciaService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /licencias/:id} : get the "id" licencia.
     *
     * @param id the id of the licenciaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the licenciaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/licencias/{id}")
    public ResponseEntity<LicenciaDTO> getLicencia(@PathVariable String id) {
        log.debug("REST request to get Licencia : {}", id);
        Optional<LicenciaDTO> licenciaDTO = licenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(licenciaDTO);
    }

    /**
     * {@code DELETE  /licencias/:id} : delete the "id" licencia.
     *
     * @param id the id of the licenciaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/licencias/{id}")
    public ResponseEntity<Void> deleteLicencia(@PathVariable String id) {
        log.debug("REST request to delete Licencia : {}", id);
        licenciaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
