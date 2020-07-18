package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.MarcaService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.MarcaDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Marca}.
 */
@RestController
@RequestMapping("/api")
public class MarcaResource {

    private final Logger log = LoggerFactory.getLogger(MarcaResource.class);

    private static final String ENTITY_NAME = "marca";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MarcaService marcaService;

    public MarcaResource(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    /**
     * {@code POST  /marcas} : Create a new marca.
     *
     * @param marcaDTO the marcaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new marcaDTO, or with status {@code 400 (Bad Request)} if the marca has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/marcas")
    public ResponseEntity<MarcaDTO> createMarca(@RequestBody MarcaDTO marcaDTO) throws URISyntaxException {
        log.debug("REST request to save Marca : {}", marcaDTO);
        if (marcaDTO.getId() != null) {
            throw new BadRequestAlertException("A new marca cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MarcaDTO result = marcaService.save(marcaDTO);
        return ResponseEntity.created(new URI("/api/marcas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /marcas} : Updates an existing marca.
     *
     * @param marcaDTO the marcaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated marcaDTO,
     * or with status {@code 400 (Bad Request)} if the marcaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the marcaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/marcas")
    public ResponseEntity<MarcaDTO> updateMarca(@RequestBody MarcaDTO marcaDTO) throws URISyntaxException {
        log.debug("REST request to update Marca : {}", marcaDTO);
        if (marcaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MarcaDTO result = marcaService.save(marcaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, marcaDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /marcas} : get all the marcas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of marcas in body.
     */
    @GetMapping("/marcas")
    public ResponseEntity<List<MarcaDTO>> getAllMarcas(Pageable pageable) {
        log.debug("REST request to get a page of Marcas");
        Page<MarcaDTO> page = marcaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /marcas/:id} : get the "id" marca.
     *
     * @param id the id of the marcaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the marcaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/marcas/{id}")
    public ResponseEntity<MarcaDTO> getMarca(@PathVariable String id) {
        log.debug("REST request to get Marca : {}", id);
        Optional<MarcaDTO> marcaDTO = marcaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(marcaDTO);
    }

    /**
     * {@code DELETE  /marcas/:id} : delete the "id" marca.
     *
     * @param id the id of the marcaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/marcas/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable String id) {
        log.debug("REST request to delete Marca : {}", id);
        marcaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
