package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Marca;
import co.edu.ucentral.app.repository.MarcaRepository;
import co.edu.ucentral.app.service.MarcaService;
import co.edu.ucentral.app.service.dto.MarcaDTO;
import co.edu.ucentral.app.service.mapper.MarcaMapper;

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
 * Integration tests for the {@link MarcaResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MarcaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaMapper marcaMapper;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private MockMvc restMarcaMockMvc;

    private Marca marca;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Marca createEntity() {
        Marca marca = new Marca()
            .nombre(DEFAULT_NOMBRE);
        return marca;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Marca createUpdatedEntity() {
        Marca marca = new Marca()
            .nombre(UPDATED_NOMBRE);
        return marca;
    }

    @BeforeEach
    public void initTest() {
        marcaRepository.deleteAll();
        marca = createEntity();
    }

    @Test
    public void createMarca() throws Exception {
        int databaseSizeBeforeCreate = marcaRepository.findAll().size();
        // Create the Marca
        MarcaDTO marcaDTO = marcaMapper.toDto(marca);
        restMarcaMockMvc.perform(post("/api/marcas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(marcaDTO)))
            .andExpect(status().isCreated());

        // Validate the Marca in the database
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeCreate + 1);
        Marca testMarca = marcaList.get(marcaList.size() - 1);
        assertThat(testMarca.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    public void createMarcaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = marcaRepository.findAll().size();

        // Create the Marca with an existing ID
        marca.setId("existing_id");
        MarcaDTO marcaDTO = marcaMapper.toDto(marca);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMarcaMockMvc.perform(post("/api/marcas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(marcaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Marca in the database
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllMarcas() throws Exception {
        // Initialize the database
        marcaRepository.save(marca);

        // Get all the marcaList
        restMarcaMockMvc.perform(get("/api/marcas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(marca.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    public void getMarca() throws Exception {
        // Initialize the database
        marcaRepository.save(marca);

        // Get the marca
        restMarcaMockMvc.perform(get("/api/marcas/{id}", marca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(marca.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }
    @Test
    public void getNonExistingMarca() throws Exception {
        // Get the marca
        restMarcaMockMvc.perform(get("/api/marcas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMarca() throws Exception {
        // Initialize the database
        marcaRepository.save(marca);

        int databaseSizeBeforeUpdate = marcaRepository.findAll().size();

        // Update the marca
        Marca updatedMarca = marcaRepository.findById(marca.getId()).get();
        updatedMarca
            .nombre(UPDATED_NOMBRE);
        MarcaDTO marcaDTO = marcaMapper.toDto(updatedMarca);

        restMarcaMockMvc.perform(put("/api/marcas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(marcaDTO)))
            .andExpect(status().isOk());

        // Validate the Marca in the database
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeUpdate);
        Marca testMarca = marcaList.get(marcaList.size() - 1);
        assertThat(testMarca.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    public void updateNonExistingMarca() throws Exception {
        int databaseSizeBeforeUpdate = marcaRepository.findAll().size();

        // Create the Marca
        MarcaDTO marcaDTO = marcaMapper.toDto(marca);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMarcaMockMvc.perform(put("/api/marcas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(marcaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Marca in the database
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMarca() throws Exception {
        // Initialize the database
        marcaRepository.save(marca);

        int databaseSizeBeforeDelete = marcaRepository.findAll().size();

        // Delete the marca
        restMarcaMockMvc.perform(delete("/api/marcas/{id}", marca.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
