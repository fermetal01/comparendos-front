package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Patio;
import co.edu.ucentral.app.repository.PatioRepository;
import co.edu.ucentral.app.service.PatioService;
import co.edu.ucentral.app.service.dto.PatioDTO;
import co.edu.ucentral.app.service.mapper.PatioMapper;

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
 * Integration tests for the {@link PatioResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PatioResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private PatioMapper patioMapper;

    @Autowired
    private PatioService patioService;

    @Autowired
    private MockMvc restPatioMockMvc;

    private Patio patio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patio createEntity() {
        Patio patio = new Patio()
            .nombre(DEFAULT_NOMBRE)
            .numero(DEFAULT_NUMERO)
            .direccion(DEFAULT_DIRECCION);
        return patio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patio createUpdatedEntity() {
        Patio patio = new Patio()
            .nombre(UPDATED_NOMBRE)
            .numero(UPDATED_NUMERO)
            .direccion(UPDATED_DIRECCION);
        return patio;
    }

    @BeforeEach
    public void initTest() {
        patioRepository.deleteAll();
        patio = createEntity();
    }

    @Test
    public void createPatio() throws Exception {
        int databaseSizeBeforeCreate = patioRepository.findAll().size();
        // Create the Patio
        PatioDTO patioDTO = patioMapper.toDto(patio);
        restPatioMockMvc.perform(post("/api/patios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patioDTO)))
            .andExpect(status().isCreated());

        // Validate the Patio in the database
        List<Patio> patioList = patioRepository.findAll();
        assertThat(patioList).hasSize(databaseSizeBeforeCreate + 1);
        Patio testPatio = patioList.get(patioList.size() - 1);
        assertThat(testPatio.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPatio.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testPatio.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
    }

    @Test
    public void createPatioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patioRepository.findAll().size();

        // Create the Patio with an existing ID
        patio.setId("existing_id");
        PatioDTO patioDTO = patioMapper.toDto(patio);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatioMockMvc.perform(post("/api/patios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Patio in the database
        List<Patio> patioList = patioRepository.findAll();
        assertThat(patioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllPatios() throws Exception {
        // Initialize the database
        patioRepository.save(patio);

        // Get all the patioList
        restPatioMockMvc.perform(get("/api/patios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patio.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)));
    }
    
    @Test
    public void getPatio() throws Exception {
        // Initialize the database
        patioRepository.save(patio);

        // Get the patio
        restPatioMockMvc.perform(get("/api/patios/{id}", patio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patio.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION));
    }
    @Test
    public void getNonExistingPatio() throws Exception {
        // Get the patio
        restPatioMockMvc.perform(get("/api/patios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePatio() throws Exception {
        // Initialize the database
        patioRepository.save(patio);

        int databaseSizeBeforeUpdate = patioRepository.findAll().size();

        // Update the patio
        Patio updatedPatio = patioRepository.findById(patio.getId()).get();
        updatedPatio
            .nombre(UPDATED_NOMBRE)
            .numero(UPDATED_NUMERO)
            .direccion(UPDATED_DIRECCION);
        PatioDTO patioDTO = patioMapper.toDto(updatedPatio);

        restPatioMockMvc.perform(put("/api/patios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patioDTO)))
            .andExpect(status().isOk());

        // Validate the Patio in the database
        List<Patio> patioList = patioRepository.findAll();
        assertThat(patioList).hasSize(databaseSizeBeforeUpdate);
        Patio testPatio = patioList.get(patioList.size() - 1);
        assertThat(testPatio.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPatio.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testPatio.getDireccion()).isEqualTo(UPDATED_DIRECCION);
    }

    @Test
    public void updateNonExistingPatio() throws Exception {
        int databaseSizeBeforeUpdate = patioRepository.findAll().size();

        // Create the Patio
        PatioDTO patioDTO = patioMapper.toDto(patio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatioMockMvc.perform(put("/api/patios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Patio in the database
        List<Patio> patioList = patioRepository.findAll();
        assertThat(patioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePatio() throws Exception {
        // Initialize the database
        patioRepository.save(patio);

        int databaseSizeBeforeDelete = patioRepository.findAll().size();

        // Delete the patio
        restPatioMockMvc.perform(delete("/api/patios/{id}", patio.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Patio> patioList = patioRepository.findAll();
        assertThat(patioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
