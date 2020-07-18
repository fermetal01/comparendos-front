package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.GeneroService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.GeneroDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Genero}.
 */
@RestController
@RequestMapping("/api")
public class GeneroResource {

    private final Logger log = LoggerFactory.getLogger(GeneroResource.class);

    private static final String ENTITY_NAME = "genero";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeneroService generoService;

    public GeneroResource(GeneroService generoService) {
        this.generoService = generoService;
    }

    /**
     * {@code POST  /generos} : Create a new genero.
     *
     * @param generoDTO the generoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new generoDTO, or with status {@code 400 (Bad Request)} if the genero has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/generos")
    public ResponseEntity<GeneroDTO> createGenero(@RequestBody GeneroDTO generoDTO) throws URISyntaxException {
        log.debug("REST request to save Genero : {}", generoDTO);
        if (generoDTO.getId() != null) {
            throw new BadRequestAlertException("A new genero cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeneroDTO result = generoService.save(generoDTO);
        return ResponseEntity.created(new URI("/api/generos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /generos} : Updates an existing genero.
     *
     * @param generoDTO the generoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated generoDTO,
     * or with status {@code 400 (Bad Request)} if the generoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the generoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/generos")
    public ResponseEntity<GeneroDTO> updateGenero(@RequestBody GeneroDTO generoDTO) throws URISyntaxException {
        log.debug("REST request to update Genero : {}", generoDTO);
        if (generoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeneroDTO result = generoService.save(generoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, generoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /generos} : get all the generos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of generos in body.
     */
    @GetMapping("/generos")
    public ResponseEntity<List<GeneroDTO>> getAllGeneros(Pageable pageable) {
        log.debug("REST request to get a page of Generos");
        Page<GeneroDTO> page = generoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /generos/:id} : get the "id" genero.
     *
     * @param id the id of the generoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the generoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/generos/{id}")
    public ResponseEntity<GeneroDTO> getGenero(@PathVariable String id) {
        log.debug("REST request to get Genero : {}", id);
        Optional<GeneroDTO> generoDTO = generoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(generoDTO);
    }

    /**
     * {@code DELETE  /generos/:id} : delete the "id" genero.
     *
     * @param id the id of the generoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/generos/{id}")
    public ResponseEntity<Void> deleteGenero(@PathVariable String id) {
        log.debug("REST request to delete Genero : {}", id);
        generoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
