package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Combustible;
import co.edu.ucentral.app.repository.CombustibleRepository;
import co.edu.ucentral.app.service.CombustibleService;
import co.edu.ucentral.app.service.dto.CombustibleDTO;
import co.edu.ucentral.app.service.mapper.CombustibleMapper;

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
 * Integration tests for the {@link CombustibleResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CombustibleResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private CombustibleRepository combustibleRepository;

    @Autowired
    private CombustibleMapper combustibleMapper;

    @Autowired
    private CombustibleService combustibleService;

    @Autowired
    private MockMvc restCombustibleMockMvc;

    private Combustible combustible;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Combustible createEntity() {
        Combustible combustible = new Combustible()
            .nombre(DEFAULT_NOMBRE);
        return combustible;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Combustible createUpdatedEntity() {
        Combustible combustible = new Combustible()
            .nombre(UPDATED_NOMBRE);
        return combustible;
    }

    @BeforeEach
    public void initTest() {
        combustibleRepository.deleteAll();
        combustible = createEntity();
    }

    @Test
    public void createCombustible() throws Exception {
        int databaseSizeBeforeCreate = combustibleRepository.findAll().size();
        // Create the Combustible
        CombustibleDTO combustibleDTO = combustibleMapper.toDto(combustible);
        restCombustibleMockMvc.perform(post("/api/combustibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(combustibleDTO)))
            .andExpect(status().isCreated());

        // Validate the Combustible in the database
        List<Combustible> combustibleList = combustibleRepository.findAll();
        assertThat(combustibleList).hasSize(databaseSizeBeforeCreate + 1);
        Combustible testCombustible = combustibleList.get(combustibleList.size() - 1);
        assertThat(testCombustible.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    public void createCombustibleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = combustibleRepository.findAll().size();

        // Create the Combustible with an existing ID
        combustible.setId("existing_id");
        CombustibleDTO combustibleDTO = combustibleMapper.toDto(combustible);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCombustibleMockMvc.perform(post("/api/combustibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(combustibleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Combustible in the database
        List<Combustible> combustibleList = combustibleRepository.findAll();
        assertThat(combustibleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllCombustibles() throws Exception {
        // Initialize the database
        combustibleRepository.save(combustible);

        // Get all the combustibleList
        restCombustibleMockMvc.perform(get("/api/combustibles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(combustible.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    public void getCombustible() throws Exception {
        // Initialize the database
        combustibleRepository.save(combustible);

        // Get the combustible
        restCombustibleMockMvc.perform(get("/api/combustibles/{id}", combustible.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(combustible.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }
    @Test
    public void getNonExistingCombustible() throws Exception {
        // Get the combustible
        restCombustibleMockMvc.perform(get("/api/combustibles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCombustible() throws Exception {
        // Initialize the database
        combustibleRepository.save(combustible);

        int databaseSizeBeforeUpdate = combustibleRepository.findAll().size();

        // Update the combustible
        Combustible updatedCombustible = combustibleRepository.findById(combustible.getId()).get();
        updatedCombustible
            .nombre(UPDATED_NOMBRE);
        CombustibleDTO combustibleDTO = combustibleMapper.toDto(updatedCombustible);

        restCombustibleMockMvc.perform(put("/api/combustibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(combustibleDTO)))
            .andExpect(status().isOk());

        // Validate the Combustible in the database
        List<Combustible> combustibleList = combustibleRepository.findAll();
        assertThat(combustibleList).hasSize(databaseSizeBeforeUpdate);
        Combustible testCombustible = combustibleList.get(combustibleList.size() - 1);
        assertThat(testCombustible.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    public void updateNonExistingCombustible() throws Exception {
        int databaseSizeBeforeUpdate = combustibleRepository.findAll().size();

        // Create the Combustible
        CombustibleDTO combustibleDTO = combustibleMapper.toDto(combustible);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCombustibleMockMvc.perform(put("/api/combustibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(combustibleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Combustible in the database
        List<Combustible> combustibleList = combustibleRepository.findAll();
        assertThat(combustibleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCombustible() throws Exception {
        // Initialize the database
        combustibleRepository.save(combustible);

        int databaseSizeBeforeDelete = combustibleRepository.findAll().size();

        // Delete the combustible
        restCombustibleMockMvc.perform(delete("/api/combustibles/{id}", combustible.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Combustible> combustibleList = combustibleRepository.findAll();
        assertThat(combustibleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
