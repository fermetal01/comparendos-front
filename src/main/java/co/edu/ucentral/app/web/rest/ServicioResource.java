package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.ServicioService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.ServicioDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Servicio}.
 */
@RestController
@RequestMapping("/api")
public class ServicioResource {

    private final Logger log = LoggerFactory.getLogger(ServicioResource.class);

    private static final String ENTITY_NAME = "servicio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServicioService servicioService;

    public ServicioResource(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    /**
     * {@code POST  /servicios} : Create a new servicio.
     *
     * @param servicioDTO the servicioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new servicioDTO, or with status {@code 400 (Bad Request)} if the servicio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/servicios")
    public ResponseEntity<ServicioDTO> createServicio(@RequestBody ServicioDTO servicioDTO) throws URISyntaxException {
        log.debug("REST request to save Servicio : {}", servicioDTO);
        if (servicioDTO.getId() != null) {
            throw new BadRequestAlertException("A new servicio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServicioDTO result = servicioService.save(servicioDTO);
        return ResponseEntity.created(new URI("/api/servicios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /servicios} : Updates an existing servicio.
     *
     * @param servicioDTO the servicioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servicioDTO,
     * or with status {@code 400 (Bad Request)} if the servicioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the servicioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/servicios")
    public ResponseEntity<ServicioDTO> updateServicio(@RequestBody ServicioDTO servicioDTO) throws URISyntaxException {
        log.debug("REST request to update Servicio : {}", servicioDTO);
        if (servicioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServicioDTO result = servicioService.save(servicioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, servicioDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /servicios} : get all the servicios.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servicios in body.
     */
    @GetMapping("/servicios")
    public ResponseEntity<List<ServicioDTO>> getAllServicios(Pageable pageable) {
        log.debug("REST request to get a page of Servicios");
        Page<ServicioDTO> page = servicioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /servicios/:id} : get the "id" servicio.
     *
     * @param id the id of the servicioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the servicioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/servicios/{id}")
    public ResponseEntity<ServicioDTO> getServicio(@PathVariable String id) {
        log.debug("REST request to get Servicio : {}", id);
        Optional<ServicioDTO> servicioDTO = servicioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(servicioDTO);
    }

    /**
     * {@code DELETE  /servicios/:id} : delete the "id" servicio.
     *
     * @param id the id of the servicioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/servicios/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable String id) {
        log.debug("REST request to delete Servicio : {}", id);
        servicioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
