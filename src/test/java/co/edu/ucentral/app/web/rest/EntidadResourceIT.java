package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Entidad;
import co.edu.ucentral.app.domain.Vehiculo;
import co.edu.ucentral.app.repository.EntidadRepository;
import co.edu.ucentral.app.service.EntidadService;
import co.edu.ucentral.app.service.dto.EntidadDTO;
import co.edu.ucentral.app.service.mapper.EntidadMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EntidadResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntidadResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_CELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private EntidadRepository entidadRepository;

    @Autowired
    private EntidadMapper entidadMapper;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private MockMvc restEntidadMockMvc;

    private Entidad entidad;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entidad createEntity() {
        Entidad entidad = new Entidad()
            .nombre(DEFAULT_NOMBRE)
            .direccion(DEFAULT_DIRECCION)
            .telefono(DEFAULT_TELEFONO)
            .celular(DEFAULT_CELULAR)
            .email(DEFAULT_EMAIL);
        // Add required entity
        Vehiculo vehiculo;
        vehiculo = VehiculoResourceIT.createEntity();
        vehiculo.setId("fixed-id-for-tests");
        entidad.getVehiculos().add(vehiculo);
        return entidad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entidad createUpdatedEntity() {
        Entidad entidad = new Entidad()
            .nombre(UPDATED_NOMBRE)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO)
            .celular(UPDATED_CELULAR)
            .email(UPDATED_EMAIL);
        // Add required entity
        Vehiculo vehiculo;
        vehiculo = VehiculoResourceIT.createUpdatedEntity();
        vehiculo.setId("fixed-id-for-tests");
        entidad.getVehiculos().add(vehiculo);
        return entidad;
    }

    @BeforeEach
    public void initTest() {
        entidadRepository.deleteAll();
        entidad = createEntity();
    }

    @Test
    public void createEntidad() throws Exception {
        int databaseSizeBeforeCreate = entidadRepository.findAll().size();
        // Create the Entidad
        EntidadDTO entidadDTO = entidadMapper.toDto(entidad);
        restEntidadMockMvc.perform(post("/api/entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadDTO)))
            .andExpect(status().isCreated());

        // Validate the Entidad in the database
        List<Entidad> entidadList = entidadRepository.findAll();
        assertThat(entidadList).hasSize(databaseSizeBeforeCreate + 1);
        Entidad testEntidad = entidadList.get(entidadList.size() - 1);
        assertThat(testEntidad.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testEntidad.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testEntidad.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testEntidad.getCelular()).isEqualTo(DEFAULT_CELULAR);
        assertThat(testEntidad.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    public void createEntidadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entidadRepository.findAll().size();

        // Create the Entidad with an existing ID
        entidad.setId("existing_id");
        EntidadDTO entidadDTO = entidadMapper.toDto(entidad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntidadMockMvc.perform(post("/api/entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entidad in the database
        List<Entidad> entidadList = entidadRepository.findAll();
        assertThat(entidadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllEntidads() throws Exception {
        // Initialize the database
        entidadRepository.save(entidad);

        // Get all the entidadList
        restEntidadMockMvc.perform(get("/api/entidads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entidad.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    public void getEntidad() throws Exception {
        // Initialize the database
        entidadRepository.save(entidad);

        // Get the entidad
        restEntidadMockMvc.perform(get("/api/entidads/{id}", entidad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entidad.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.celular").value(DEFAULT_CELULAR))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    public void getNonExistingEntidad() throws Exception {
        // Get the entidad
        restEntidadMockMvc.perform(get("/api/entidads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEntidad() throws Exception {
        // Initialize the database
        entidadRepository.save(entidad);

        int databaseSizeBeforeUpdate = entidadRepository.findAll().size();

        // Update the entidad
        Entidad updatedEntidad = entidadRepository.findById(entidad.getId()).get();
        updatedEntidad
            .nombre(UPDATED_NOMBRE)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO)
            .celular(UPDATED_CELULAR)
            .email(UPDATED_EMAIL);
        EntidadDTO entidadDTO = entidadMapper.toDto(updatedEntidad);

        restEntidadMockMvc.perform(put("/api/entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadDTO)))
            .andExpect(status().isOk());

        // Validate the Entidad in the database
        List<Entidad> entidadList = entidadRepository.findAll();
        assertThat(entidadList).hasSize(databaseSizeBeforeUpdate);
        Entidad testEntidad = entidadList.get(entidadList.size() - 1);
        assertThat(testEntidad.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testEntidad.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testEntidad.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testEntidad.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testEntidad.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    public void updateNonExistingEntidad() throws Exception {
        int databaseSizeBeforeUpdate = entidadRepository.findAll().size();

        // Create the Entidad
        EntidadDTO entidadDTO = entidadMapper.toDto(entidad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntidadMockMvc.perform(put("/api/entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entidad in the database
        List<Entidad> entidadList = entidadRepository.findAll();
        assertThat(entidadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEntidad() throws Exception {
        // Initialize the database
        entidadRepository.save(entidad);

        int databaseSizeBeforeDelete = entidadRepository.findAll().size();

        // Delete the entidad
        restEntidadMockMvc.perform(delete("/api/entidads/{id}", entidad.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Entidad> entidadList = entidadRepository.findAll();
        assertThat(entidadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
