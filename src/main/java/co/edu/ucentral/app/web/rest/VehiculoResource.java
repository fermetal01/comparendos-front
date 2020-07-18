package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.service.VehiculoService;
import co.edu.ucentral.app.web.rest.errors.BadRequestAlertException;
import co.edu.ucentral.app.service.dto.VehiculoDTO;

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
 * REST controller for managing {@link co.edu.ucentral.app.domain.Vehiculo}.
 */
@RestController
@RequestMapping("/api")
public class VehiculoResource {

    private final Logger log = LoggerFactory.getLogger(VehiculoResource.class);

    private static final String ENTITY_NAME = "vehiculo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VehiculoService vehiculoService;

    public VehiculoResource(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    /**
     * {@code POST  /vehiculos} : Create a new vehiculo.
     *
     * @param vehiculoDTO the vehiculoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vehiculoDTO, or with status {@code 400 (Bad Request)} if the vehiculo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vehiculos")
    public ResponseEntity<VehiculoDTO> createVehiculo(@Valid @RequestBody VehiculoDTO vehiculoDTO) throws URISyntaxException {
        log.debug("REST request to save Vehiculo : {}", vehiculoDTO);
        if (vehiculoDTO.getId() != null) {
            throw new BadRequestAlertException("A new vehiculo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehiculoDTO result = vehiculoService.save(vehiculoDTO);
        return ResponseEntity.created(new URI("/api/vehiculos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /vehiculos} : Updates an existing vehiculo.
     *
     * @param vehiculoDTO the vehiculoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehiculoDTO,
     * or with status {@code 400 (Bad Request)} if the vehiculoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vehiculoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vehiculos")
    public ResponseEntity<VehiculoDTO> updateVehiculo(@Valid @RequestBody VehiculoDTO vehiculoDTO) throws URISyntaxException {
        log.debug("REST request to update Vehiculo : {}", vehiculoDTO);
        if (vehiculoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehiculoDTO result = vehiculoService.save(vehiculoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vehiculoDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /vehiculos} : get all the vehiculos.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vehiculos in body.
     */
    @GetMapping("/vehiculos")
    public ResponseEntity<List<VehiculoDTO>> getAllVehiculos(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Vehiculos");
        Page<VehiculoDTO> page;
        if (eagerload) {
            page = vehiculoService.findAllWithEagerRelationships(pageable);
        } else {
            page = vehiculoService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vehiculos/:id} : get the "id" vehiculo.
     *
     * @param id the id of the vehiculoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vehiculoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vehiculos/{id}")
    public ResponseEntity<VehiculoDTO> getVehiculo(@PathVariable String id) {
        log.debug("REST request to get Vehiculo : {}", id);
        Optional<VehiculoDTO> vehiculoDTO = vehiculoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehiculoDTO);
    }

    /**
     * {@code DELETE  /vehiculos/:id} : delete the "id" vehiculo.
     *
     * @param id the id of the vehiculoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vehiculos/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable String id) {
        log.debug("REST request to delete Vehiculo : {}", id);
        vehiculoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
