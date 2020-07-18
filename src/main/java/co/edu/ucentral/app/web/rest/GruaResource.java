package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.GruaService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.GruaDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Grua}.
 */
@RestController
@RequestMapping("/api")
public class GruaResource {

    private final Logger log = LoggerFactory.getLogger(GruaResource.class);

    private static final String ENTITY_NAME = "grua";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GruaService gruaService;

    public GruaResource(GruaService gruaService) {
        this.gruaService = gruaService;
    }

    /**
     * {@code POST  /gruas} : Create a new grua.
     *
     * @param gruaDTO the gruaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gruaDTO, or with status {@code 400 (Bad Request)} if the grua has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gruas")
    public ResponseEntity<GruaDTO> createGrua(@RequestBody GruaDTO gruaDTO) throws URISyntaxException {
        log.debug("REST request to save Grua : {}", gruaDTO);
        if (gruaDTO.getId() != null) {
            throw new BadRequestAlertException("A new grua cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GruaDTO result = gruaService.save(gruaDTO);
        return ResponseEntity.created(new URI("/api/gruas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /gruas} : Updates an existing grua.
     *
     * @param gruaDTO the gruaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gruaDTO,
     * or with status {@code 400 (Bad Request)} if the gruaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gruaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gruas")
    public ResponseEntity<GruaDTO> updateGrua(@RequestBody GruaDTO gruaDTO) throws URISyntaxException {
        log.debug("REST request to update Grua : {}", gruaDTO);
        if (gruaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GruaDTO result = gruaService.save(gruaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gruaDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /gruas} : get all the gruas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gruas in body.
     */
    @GetMapping("/gruas")
    public ResponseEntity<List<GruaDTO>> getAllGruas(Pageable pageable) {
        log.debug("REST request to get a page of Gruas");
        Page<GruaDTO> page = gruaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gruas/:id} : get the "id" grua.
     *
     * @param id the id of the gruaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gruaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gruas/{id}")
    public ResponseEntity<GruaDTO> getGrua(@PathVariable String id) {
        log.debug("REST request to get Grua : {}", id);
        Optional<GruaDTO> gruaDTO = gruaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gruaDTO);
    }

    /**
     * {@code DELETE  /gruas/:id} : delete the "id" grua.
     *
     * @param id the id of the gruaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gruas/{id}")
    public ResponseEntity<Void> deleteGrua(@PathVariable String id) {
        log.debug("REST request to delete Grua : {}", id);
        gruaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
