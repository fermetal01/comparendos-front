package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.ClaseVehiculoService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.ClaseVehiculoDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.ClaseVehiculo}.
 */
@RestController
@RequestMapping("/api")
public class ClaseVehiculoResource {

    private final Logger log = LoggerFactory.getLogger(ClaseVehiculoResource.class);

    private static final String ENTITY_NAME = "claseVehiculo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaseVehiculoService claseVehiculoService;

    public ClaseVehiculoResource(ClaseVehiculoService claseVehiculoService) {
        this.claseVehiculoService = claseVehiculoService;
    }

    /**
     * {@code POST  /clase-vehiculos} : Create a new claseVehiculo.
     *
     * @param claseVehiculoDTO the claseVehiculoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claseVehiculoDTO, or with status {@code 400 (Bad Request)} if the claseVehiculo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/clase-vehiculos")
    public ResponseEntity<ClaseVehiculoDTO> createClaseVehiculo(@RequestBody ClaseVehiculoDTO claseVehiculoDTO) throws URISyntaxException {
        log.debug("REST request to save ClaseVehiculo : {}", claseVehiculoDTO);
        if (claseVehiculoDTO.getId() != null) {
            throw new BadRequestAlertException("A new claseVehiculo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaseVehiculoDTO result = claseVehiculoService.save(claseVehiculoDTO);
        return ResponseEntity.created(new URI("/api/clase-vehiculos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /clase-vehiculos} : Updates an existing claseVehiculo.
     *
     * @param claseVehiculoDTO the claseVehiculoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claseVehiculoDTO,
     * or with status {@code 400 (Bad Request)} if the claseVehiculoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claseVehiculoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/clase-vehiculos")
    public ResponseEntity<ClaseVehiculoDTO> updateClaseVehiculo(@RequestBody ClaseVehiculoDTO claseVehiculoDTO) throws URISyntaxException {
        log.debug("REST request to update ClaseVehiculo : {}", claseVehiculoDTO);
        if (claseVehiculoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaseVehiculoDTO result = claseVehiculoService.save(claseVehiculoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claseVehiculoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /clase-vehiculos} : get all the claseVehiculos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claseVehiculos in body.
     */
    @GetMapping("/clase-vehiculos")
    public ResponseEntity<List<ClaseVehiculoDTO>> getAllClaseVehiculos(Pageable pageable) {
        log.debug("REST request to get a page of ClaseVehiculos");
        Page<ClaseVehiculoDTO> page = claseVehiculoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /clase-vehiculos/:id} : get the "id" claseVehiculo.
     *
     * @param id the id of the claseVehiculoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claseVehiculoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clase-vehiculos/{id}")
    public ResponseEntity<ClaseVehiculoDTO> getClaseVehiculo(@PathVariable String id) {
        log.debug("REST request to get ClaseVehiculo : {}", id);
        Optional<ClaseVehiculoDTO> claseVehiculoDTO = claseVehiculoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claseVehiculoDTO);
    }

    /**
     * {@code DELETE  /clase-vehiculos/:id} : delete the "id" claseVehiculo.
     *
     * @param id the id of the claseVehiculoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/clase-vehiculos/{id}")
    public ResponseEntity<Void> deleteClaseVehiculo(@PathVariable String id) {
        log.debug("REST request to delete ClaseVehiculo : {}", id);
        claseVehiculoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
