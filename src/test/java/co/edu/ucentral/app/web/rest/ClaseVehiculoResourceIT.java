package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.ClaseVehiculo;
import co.edu.ucentral.app.repository.ClaseVehiculoRepository;
import co.edu.ucentral.app.service.ClaseVehiculoService;
import co.edu.ucentral.app.service.dto.ClaseVehiculoDTO;
import co.edu.ucentral.app.service.mapper.ClaseVehiculoMapper;

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
 * Integration tests for the {@link ClaseVehiculoResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClaseVehiculoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private ClaseVehiculoRepository claseVehiculoRepository;

    @Autowired
    private ClaseVehiculoMapper claseVehiculoMapper;

    @Autowired
    private ClaseVehiculoService claseVehiculoService;

    @Autowired
    private MockMvc restClaseVehiculoMockMvc;

    private ClaseVehiculo claseVehiculo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaseVehiculo createEntity() {
        ClaseVehiculo claseVehiculo = new ClaseVehiculo()
            .nombre(DEFAULT_NOMBRE);
        return claseVehiculo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClaseVehiculo createUpdatedEntity() {
        ClaseVehiculo claseVehiculo = new ClaseVehiculo()
            .nombre(UPDATED_NOMBRE);
        return claseVehiculo;
    }

    @BeforeEach
    public void initTest() {
        claseVehiculoRepository.deleteAll();
        claseVehiculo = createEntity();
    }

    @Test
    public void createClaseVehiculo() throws Exception {
        int databaseSizeBeforeCreate = claseVehiculoRepository.findAll().size();
        // Create the ClaseVehiculo
        ClaseVehiculoDTO claseVehiculoDTO = claseVehiculoMapper.toDto(claseVehiculo);
        restClaseVehiculoMockMvc.perform(post("/api/clase-vehiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(claseVehiculoDTO)))
            .andExpect(status().isCreated());

        // Validate the ClaseVehiculo in the database
        List<ClaseVehiculo> claseVehiculoList = claseVehiculoRepository.findAll();
        assertThat(claseVehiculoList).hasSize(databaseSizeBeforeCreate + 1);
        ClaseVehiculo testClaseVehiculo = claseVehiculoList.get(claseVehiculoList.size() - 1);
        assertThat(testClaseVehiculo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    public void createClaseVehiculoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = claseVehiculoRepository.findAll().size();

        // Create the ClaseVehiculo with an existing ID
        claseVehiculo.setId("existing_id");
        ClaseVehiculoDTO claseVehiculoDTO = claseVehiculoMapper.toDto(claseVehiculo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClaseVehiculoMockMvc.perform(post("/api/clase-vehiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(claseVehiculoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaseVehiculo in the database
        List<ClaseVehiculo> claseVehiculoList = claseVehiculoRepository.findAll();
        assertThat(claseVehiculoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllClaseVehiculos() throws Exception {
        // Initialize the database
        claseVehiculoRepository.save(claseVehiculo);

        // Get all the claseVehiculoList
        restClaseVehiculoMockMvc.perform(get("/api/clase-vehiculos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(claseVehiculo.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    public void getClaseVehiculo() throws Exception {
        // Initialize the database
        claseVehiculoRepository.save(claseVehiculo);

        // Get the claseVehiculo
        restClaseVehiculoMockMvc.perform(get("/api/clase-vehiculos/{id}", claseVehiculo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(claseVehiculo.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }
    @Test
    public void getNonExistingClaseVehiculo() throws Exception {
        // Get the claseVehiculo
        restClaseVehiculoMockMvc.perform(get("/api/clase-vehiculos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateClaseVehiculo() throws Exception {
        // Initialize the database
        claseVehiculoRepository.save(claseVehiculo);

        int databaseSizeBeforeUpdate = claseVehiculoRepository.findAll().size();

        // Update the claseVehiculo
        ClaseVehiculo updatedClaseVehiculo = claseVehiculoRepository.findById(claseVehiculo.getId()).get();
        updatedClaseVehiculo
            .nombre(UPDATED_NOMBRE);
        ClaseVehiculoDTO claseVehiculoDTO = claseVehiculoMapper.toDto(updatedClaseVehiculo);

        restClaseVehiculoMockMvc.perform(put("/api/clase-vehiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(claseVehiculoDTO)))
            .andExpect(status().isOk());

        // Validate the ClaseVehiculo in the database
        List<ClaseVehiculo> claseVehiculoList = claseVehiculoRepository.findAll();
        assertThat(claseVehiculoList).hasSize(databaseSizeBeforeUpdate);
        ClaseVehiculo testClaseVehiculo = claseVehiculoList.get(claseVehiculoList.size() - 1);
        assertThat(testClaseVehiculo.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    public void updateNonExistingClaseVehiculo() throws Exception {
        int databaseSizeBeforeUpdate = claseVehiculoRepository.findAll().size();

        // Create the ClaseVehiculo
        ClaseVehiculoDTO claseVehiculoDTO = claseVehiculoMapper.toDto(claseVehiculo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClaseVehiculoMockMvc.perform(put("/api/clase-vehiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(claseVehiculoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClaseVehiculo in the database
        List<ClaseVehiculo> claseVehiculoList = claseVehiculoRepository.findAll();
        assertThat(claseVehiculoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteClaseVehiculo() throws Exception {
        // Initialize the database
        claseVehiculoRepository.save(claseVehiculo);

        int databaseSizeBeforeDelete = claseVehiculoRepository.findAll().size();

        // Delete the claseVehiculo
        restClaseVehiculoMockMvc.perform(delete("/api/clase-vehiculos/{id}", claseVehiculo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClaseVehiculo> claseVehiculoList = claseVehiculoRepository.findAll();
        assertThat(claseVehiculoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
