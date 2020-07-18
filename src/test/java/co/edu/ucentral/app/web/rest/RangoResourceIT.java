package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Rango;
import co.edu.ucentral.app.repository.RangoRepository;
import co.edu.ucentral.app.service.RangoService;
import co.edu.ucentral.app.service.dto.RangoDTO;
import co.edu.ucentral.app.service.mapper.RangoMapper;

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
 * Integration tests for the {@link RangoResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RangoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_ACRONIMO = "AAAAAAAAAA";
    private static final String UPDATED_ACRONIMO = "BBBBBBBBBB";

    @Autowired
    private RangoRepository rangoRepository;

    @Autowired
    private RangoMapper rangoMapper;

    @Autowired
    private RangoService rangoService;

    @Autowired
    private MockMvc restRangoMockMvc;

    private Rango rango;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rango createEntity() {
        Rango rango = new Rango()
            .nombre(DEFAULT_NOMBRE)
            .acronimo(DEFAULT_ACRONIMO);
        return rango;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rango createUpdatedEntity() {
        Rango rango = new Rango()
            .nombre(UPDATED_NOMBRE)
            .acronimo(UPDATED_ACRONIMO);
        return rango;
    }

    @BeforeEach
    public void initTest() {
        rangoRepository.deleteAll();
        rango = createEntity();
    }

    @Test
    public void createRango() throws Exception {
        int databaseSizeBeforeCreate = rangoRepository.findAll().size();
        // Create the Rango
        RangoDTO rangoDTO = rangoMapper.toDto(rango);
        restRangoMockMvc.perform(post("/api/rangos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rangoDTO)))
            .andExpect(status().isCreated());

        // Validate the Rango in the database
        List<Rango> rangoList = rangoRepository.findAll();
        assertThat(rangoList).hasSize(databaseSizeBeforeCreate + 1);
        Rango testRango = rangoList.get(rangoList.size() - 1);
        assertThat(testRango.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testRango.getAcronimo()).isEqualTo(DEFAULT_ACRONIMO);
    }

    @Test
    public void createRangoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rangoRepository.findAll().size();

        // Create the Rango with an existing ID
        rango.setId("existing_id");
        RangoDTO rangoDTO = rangoMapper.toDto(rango);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRangoMockMvc.perform(post("/api/rangos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rangoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rango in the database
        List<Rango> rangoList = rangoRepository.findAll();
        assertThat(rangoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllRangos() throws Exception {
        // Initialize the database
        rangoRepository.save(rango);

        // Get all the rangoList
        restRangoMockMvc.perform(get("/api/rangos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rango.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].acronimo").value(hasItem(DEFAULT_ACRONIMO)));
    }
    
    @Test
    public void getRango() throws Exception {
        // Initialize the database
        rangoRepository.save(rango);

        // Get the rango
        restRangoMockMvc.perform(get("/api/rangos/{id}", rango.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rango.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.acronimo").value(DEFAULT_ACRONIMO));
    }
    @Test
    public void getNonExistingRango() throws Exception {
        // Get the rango
        restRangoMockMvc.perform(get("/api/rangos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRango() throws Exception {
        // Initialize the database
        rangoRepository.save(rango);

        int databaseSizeBeforeUpdate = rangoRepository.findAll().size();

        // Update the rango
        Rango updatedRango = rangoRepository.findById(rango.getId()).get();
        updatedRango
            .nombre(UPDATED_NOMBRE)
            .acronimo(UPDATED_ACRONIMO);
        RangoDTO rangoDTO = rangoMapper.toDto(updatedRango);

        restRangoMockMvc.perform(put("/api/rangos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rangoDTO)))
            .andExpect(status().isOk());

        // Validate the Rango in the database
        List<Rango> rangoList = rangoRepository.findAll();
        assertThat(rangoList).hasSize(databaseSizeBeforeUpdate);
        Rango testRango = rangoList.get(rangoList.size() - 1);
        assertThat(testRango.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testRango.getAcronimo()).isEqualTo(UPDATED_ACRONIMO);
    }

    @Test
    public void updateNonExistingRango() throws Exception {
        int databaseSizeBeforeUpdate = rangoRepository.findAll().size();

        // Create the Rango
        RangoDTO rangoDTO = rangoMapper.toDto(rango);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRangoMockMvc.perform(put("/api/rangos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rangoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rango in the database
        List<Rango> rangoList = rangoRepository.findAll();
        assertThat(rangoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRango() throws Exception {
        // Initialize the database
        rangoRepository.save(rango);

        int databaseSizeBeforeDelete = rangoRepository.findAll().size();

        // Delete the rango
        restRangoMockMvc.perform(delete("/api/rangos/{id}", rango.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rango> rangoList = rangoRepository.findAll();
        assertThat(rangoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
