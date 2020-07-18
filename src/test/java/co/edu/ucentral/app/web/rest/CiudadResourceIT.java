package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Ciudad;
import co.edu.ucentral.app.repository.CiudadRepository;
import co.edu.ucentral.app.service.CiudadService;
import co.edu.ucentral.app.service.dto.CiudadDTO;
import co.edu.ucentral.app.service.mapper.CiudadMapper;

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
 * Integration tests for the {@link CiudadResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CiudadResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CiudadMapper ciudadMapper;

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private MockMvc restCiudadMockMvc;

    private Ciudad ciudad;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ciudad createEntity() {
        Ciudad ciudad = new Ciudad()
            .nombre(DEFAULT_NOMBRE);
        return ciudad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ciudad createUpdatedEntity() {
        Ciudad ciudad = new Ciudad()
            .nombre(UPDATED_NOMBRE);
        return ciudad;
    }

    @BeforeEach
    public void initTest() {
        ciudadRepository.deleteAll();
        ciudad = createEntity();
    }

    @Test
    public void createCiudad() throws Exception {
        int databaseSizeBeforeCreate = ciudadRepository.findAll().size();
        // Create the Ciudad
        CiudadDTO ciudadDTO = ciudadMapper.toDto(ciudad);
        restCiudadMockMvc.perform(post("/api/ciudads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ciudadDTO)))
            .andExpect(status().isCreated());

        // Validate the Ciudad in the database
        List<Ciudad> ciudadList = ciudadRepository.findAll();
        assertThat(ciudadList).hasSize(databaseSizeBeforeCreate + 1);
        Ciudad testCiudad = ciudadList.get(ciudadList.size() - 1);
        assertThat(testCiudad.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    public void createCiudadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ciudadRepository.findAll().size();

        // Create the Ciudad with an existing ID
        ciudad.setId("existing_id");
        CiudadDTO ciudadDTO = ciudadMapper.toDto(ciudad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCiudadMockMvc.perform(post("/api/ciudads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ciudadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ciudad in the database
        List<Ciudad> ciudadList = ciudadRepository.findAll();
        assertThat(ciudadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllCiudads() throws Exception {
        // Initialize the database
        ciudadRepository.save(ciudad);

        // Get all the ciudadList
        restCiudadMockMvc.perform(get("/api/ciudads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ciudad.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    public void getCiudad() throws Exception {
        // Initialize the database
        ciudadRepository.save(ciudad);

        // Get the ciudad
        restCiudadMockMvc.perform(get("/api/ciudads/{id}", ciudad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ciudad.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }
    @Test
    public void getNonExistingCiudad() throws Exception {
        // Get the ciudad
        restCiudadMockMvc.perform(get("/api/ciudads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCiudad() throws Exception {
        // Initialize the database
        ciudadRepository.save(ciudad);

        int databaseSizeBeforeUpdate = ciudadRepository.findAll().size();

        // Update the ciudad
        Ciudad updatedCiudad = ciudadRepository.findById(ciudad.getId()).get();
        updatedCiudad
            .nombre(UPDATED_NOMBRE);
        CiudadDTO ciudadDTO = ciudadMapper.toDto(updatedCiudad);

        restCiudadMockMvc.perform(put("/api/ciudads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ciudadDTO)))
            .andExpect(status().isOk());

        // Validate the Ciudad in the database
        List<Ciudad> ciudadList = ciudadRepository.findAll();
        assertThat(ciudadList).hasSize(databaseSizeBeforeUpdate);
        Ciudad testCiudad = ciudadList.get(ciudadList.size() - 1);
        assertThat(testCiudad.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    public void updateNonExistingCiudad() throws Exception {
        int databaseSizeBeforeUpdate = ciudadRepository.findAll().size();

        // Create the Ciudad
        CiudadDTO ciudadDTO = ciudadMapper.toDto(ciudad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCiudadMockMvc.perform(put("/api/ciudads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ciudadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ciudad in the database
        List<Ciudad> ciudadList = ciudadRepository.findAll();
        assertThat(ciudadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCiudad() throws Exception {
        // Initialize the database
        ciudadRepository.save(ciudad);

        int databaseSizeBeforeDelete = ciudadRepository.findAll().size();

        // Delete the ciudad
        restCiudadMockMvc.perform(delete("/api/ciudads/{id}", ciudad.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ciudad> ciudadList = ciudadRepository.findAll();
        assertThat(ciudadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
