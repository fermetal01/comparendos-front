package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Genero;
import co.edu.ucentral.app.repository.GeneroRepository;
import co.edu.ucentral.app.service.GeneroService;
import co.edu.ucentral.app.service.dto.GeneroDTO;
import co.edu.ucentral.app.service.mapper.GeneroMapper;

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
 * Integration tests for the {@link GeneroResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeneroResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private MockMvc restGeneroMockMvc;

    private Genero genero;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Genero createEntity() {
        Genero genero = new Genero()
            .nombre(DEFAULT_NOMBRE);
        return genero;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Genero createUpdatedEntity() {
        Genero genero = new Genero()
            .nombre(UPDATED_NOMBRE);
        return genero;
    }

    @BeforeEach
    public void initTest() {
        generoRepository.deleteAll();
        genero = createEntity();
    }

    @Test
    public void createGenero() throws Exception {
        int databaseSizeBeforeCreate = generoRepository.findAll().size();
        // Create the Genero
        GeneroDTO generoDTO = generoMapper.toDto(genero);
        restGeneroMockMvc.perform(post("/api/generos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generoDTO)))
            .andExpect(status().isCreated());

        // Validate the Genero in the database
        List<Genero> generoList = generoRepository.findAll();
        assertThat(generoList).hasSize(databaseSizeBeforeCreate + 1);
        Genero testGenero = generoList.get(generoList.size() - 1);
        assertThat(testGenero.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    public void createGeneroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = generoRepository.findAll().size();

        // Create the Genero with an existing ID
        genero.setId("existing_id");
        GeneroDTO generoDTO = generoMapper.toDto(genero);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeneroMockMvc.perform(post("/api/generos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Genero in the database
        List<Genero> generoList = generoRepository.findAll();
        assertThat(generoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllGeneros() throws Exception {
        // Initialize the database
        generoRepository.save(genero);

        // Get all the generoList
        restGeneroMockMvc.perform(get("/api/generos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(genero.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    public void getGenero() throws Exception {
        // Initialize the database
        generoRepository.save(genero);

        // Get the genero
        restGeneroMockMvc.perform(get("/api/generos/{id}", genero.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(genero.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }
    @Test
    public void getNonExistingGenero() throws Exception {
        // Get the genero
        restGeneroMockMvc.perform(get("/api/generos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGenero() throws Exception {
        // Initialize the database
        generoRepository.save(genero);

        int databaseSizeBeforeUpdate = generoRepository.findAll().size();

        // Update the genero
        Genero updatedGenero = generoRepository.findById(genero.getId()).get();
        updatedGenero
            .nombre(UPDATED_NOMBRE);
        GeneroDTO generoDTO = generoMapper.toDto(updatedGenero);

        restGeneroMockMvc.perform(put("/api/generos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generoDTO)))
            .andExpect(status().isOk());

        // Validate the Genero in the database
        List<Genero> generoList = generoRepository.findAll();
        assertThat(generoList).hasSize(databaseSizeBeforeUpdate);
        Genero testGenero = generoList.get(generoList.size() - 1);
        assertThat(testGenero.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    public void updateNonExistingGenero() throws Exception {
        int databaseSizeBeforeUpdate = generoRepository.findAll().size();

        // Create the Genero
        GeneroDTO generoDTO = generoMapper.toDto(genero);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeneroMockMvc.perform(put("/api/generos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Genero in the database
        List<Genero> generoList = generoRepository.findAll();
        assertThat(generoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGenero() throws Exception {
        // Initialize the database
        generoRepository.save(genero);

        int databaseSizeBeforeDelete = generoRepository.findAll().size();

        // Delete the genero
        restGeneroMockMvc.perform(delete("/api/generos/{id}", genero.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Genero> generoList = generoRepository.findAll();
        assertThat(generoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
