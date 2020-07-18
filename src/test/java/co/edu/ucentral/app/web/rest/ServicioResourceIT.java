package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Servicio;
import co.edu.ucentral.app.repository.ServicioRepository;
import co.edu.ucentral.app.service.ServicioService;
import co.edu.ucentral.app.service.dto.ServicioDTO;
import co.edu.ucentral.app.service.mapper.ServicioMapper;

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
 * Integration tests for the {@link ServicioResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServicioResourceIT {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ServicioMapper servicioMapper;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private MockMvc restServicioMockMvc;

    private Servicio servicio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicio createEntity() {
        Servicio servicio = new Servicio()
            .tipo(DEFAULT_TIPO);
        return servicio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicio createUpdatedEntity() {
        Servicio servicio = new Servicio()
            .tipo(UPDATED_TIPO);
        return servicio;
    }

    @BeforeEach
    public void initTest() {
        servicioRepository.deleteAll();
        servicio = createEntity();
    }

    @Test
    public void createServicio() throws Exception {
        int databaseSizeBeforeCreate = servicioRepository.findAll().size();
        // Create the Servicio
        ServicioDTO servicioDTO = servicioMapper.toDto(servicio);
        restServicioMockMvc.perform(post("/api/servicios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicioDTO)))
            .andExpect(status().isCreated());

        // Validate the Servicio in the database
        List<Servicio> servicioList = servicioRepository.findAll();
        assertThat(servicioList).hasSize(databaseSizeBeforeCreate + 1);
        Servicio testServicio = servicioList.get(servicioList.size() - 1);
        assertThat(testServicio.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    public void createServicioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = servicioRepository.findAll().size();

        // Create the Servicio with an existing ID
        servicio.setId("existing_id");
        ServicioDTO servicioDTO = servicioMapper.toDto(servicio);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServicioMockMvc.perform(post("/api/servicios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Servicio in the database
        List<Servicio> servicioList = servicioRepository.findAll();
        assertThat(servicioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllServicios() throws Exception {
        // Initialize the database
        servicioRepository.save(servicio);

        // Get all the servicioList
        restServicioMockMvc.perform(get("/api/servicios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(servicio.getId())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)));
    }
    
    @Test
    public void getServicio() throws Exception {
        // Initialize the database
        servicioRepository.save(servicio);

        // Get the servicio
        restServicioMockMvc.perform(get("/api/servicios/{id}", servicio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(servicio.getId()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO));
    }
    @Test
    public void getNonExistingServicio() throws Exception {
        // Get the servicio
        restServicioMockMvc.perform(get("/api/servicios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateServicio() throws Exception {
        // Initialize the database
        servicioRepository.save(servicio);

        int databaseSizeBeforeUpdate = servicioRepository.findAll().size();

        // Update the servicio
        Servicio updatedServicio = servicioRepository.findById(servicio.getId()).get();
        updatedServicio
            .tipo(UPDATED_TIPO);
        ServicioDTO servicioDTO = servicioMapper.toDto(updatedServicio);

        restServicioMockMvc.perform(put("/api/servicios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicioDTO)))
            .andExpect(status().isOk());

        // Validate the Servicio in the database
        List<Servicio> servicioList = servicioRepository.findAll();
        assertThat(servicioList).hasSize(databaseSizeBeforeUpdate);
        Servicio testServicio = servicioList.get(servicioList.size() - 1);
        assertThat(testServicio.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    public void updateNonExistingServicio() throws Exception {
        int databaseSizeBeforeUpdate = servicioRepository.findAll().size();

        // Create the Servicio
        ServicioDTO servicioDTO = servicioMapper.toDto(servicio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicioMockMvc.perform(put("/api/servicios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(servicioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Servicio in the database
        List<Servicio> servicioList = servicioRepository.findAll();
        assertThat(servicioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteServicio() throws Exception {
        // Initialize the database
        servicioRepository.save(servicio);

        int databaseSizeBeforeDelete = servicioRepository.findAll().size();

        // Delete the servicio
        restServicioMockMvc.perform(delete("/api/servicios/{id}", servicio.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Servicio> servicioList = servicioRepository.findAll();
        assertThat(servicioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
