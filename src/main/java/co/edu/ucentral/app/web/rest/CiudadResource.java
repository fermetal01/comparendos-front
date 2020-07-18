package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.CiudadService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.CiudadDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Ciudad}.
 */
@RestController
@RequestMapping("/api")
public class CiudadResource {

    private final Logger log = LoggerFactory.getLogger(CiudadResource.class);

    private static final String ENTITY_NAME = "ciudad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CiudadService ciudadService;

    public CiudadResource(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    /**
     * {@code POST  /ciudads} : Create a new ciudad.
     *
     * @param ciudadDTO the ciudadDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ciudadDTO, or with status {@code 400 (Bad Request)} if the ciudad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ciudads")
    public ResponseEntity<CiudadDTO> createCiudad(@RequestBody CiudadDTO ciudadDTO) throws URISyntaxException {
        log.debug("REST request to save Ciudad : {}", ciudadDTO);
        if (ciudadDTO.getId() != null) {
            throw new BadRequestAlertException("A new ciudad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CiudadDTO result = ciudadService.save(ciudadDTO);
        return ResponseEntity.created(new URI("/api/ciudads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ciudads} : Updates an existing ciudad.
     *
     * @param ciudadDTO the ciudadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ciudadDTO,
     * or with status {@code 400 (Bad Request)} if the ciudadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ciudadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ciudads")
    public ResponseEntity<CiudadDTO> updateCiudad(@RequestBody CiudadDTO ciudadDTO) throws URISyntaxException {
        log.debug("REST request to update Ciudad : {}", ciudadDTO);
        if (ciudadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CiudadDTO result = ciudadService.save(ciudadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ciudadDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /ciudads} : get all the ciudads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ciudads in body.
     */
    @GetMapping("/ciudads")
    public ResponseEntity<List<CiudadDTO>> getAllCiudads(Pageable pageable) {
        log.debug("REST request to get a page of Ciudads");
        Page<CiudadDTO> page = ciudadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ciudads/:id} : get the "id" ciudad.
     *
     * @param id the id of the ciudadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ciudadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ciudads/{id}")
    public ResponseEntity<CiudadDTO> getCiudad(@PathVariable String id) {
        log.debug("REST request to get Ciudad : {}", id);
        Optional<CiudadDTO> ciudadDTO = ciudadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ciudadDTO);
    }

    /**
     * {@code DELETE  /ciudads/:id} : delete the "id" ciudad.
     *
     * @param id the id of the ciudadDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ciudads/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable String id) {
        log.debug("REST request to delete Ciudad : {}", id);
        ciudadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
