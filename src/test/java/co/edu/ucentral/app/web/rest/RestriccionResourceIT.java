package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Restriccion;
import co.edu.ucentral.app.repository.RestriccionRepository;
import co.edu.ucentral.app.service.RestriccionService;
import co.edu.ucentral.app.service.dto.RestriccionDTO;
import co.edu.ucentral.app.service.mapper.RestriccionMapper;

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
 * Integration tests for the {@link RestriccionResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RestriccionResourceIT {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private RestriccionRepository restriccionRepository;

    @Autowired
    private RestriccionMapper restriccionMapper;

    @Autowired
    private RestriccionService restriccionService;

    @Autowired
    private MockMvc restRestriccionMockMvc;

    private Restriccion restriccion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Restriccion createEntity() {
        Restriccion restriccion = new Restriccion()
            .tipo(DEFAULT_TIPO);
        return restriccion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Restriccion createUpdatedEntity() {
        Restriccion restriccion = new Restriccion()
            .tipo(UPDATED_TIPO);
        return restriccion;
    }

    @BeforeEach
    public void initTest() {
        restriccionRepository.deleteAll();
        restriccion = createEntity();
    }

    @Test
    public void createRestriccion() throws Exception {
        int databaseSizeBeforeCreate = restriccionRepository.findAll().size();
        // Create the Restriccion
        RestriccionDTO restriccionDTO = restriccionMapper.toDto(restriccion);
        restRestriccionMockMvc.perform(post("/api/restriccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restriccionDTO)))
            .andExpect(status().isCreated());

        // Validate the Restriccion in the database
        List<Restriccion> restriccionList = restriccionRepository.findAll();
        assertThat(restriccionList).hasSize(databaseSizeBeforeCreate + 1);
        Restriccion testRestriccion = restriccionList.get(restriccionList.size() - 1);
        assertThat(testRestriccion.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    public void createRestriccionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = restriccionRepository.findAll().size();

        // Create the Restriccion with an existing ID
        restriccion.setId("existing_id");
        RestriccionDTO restriccionDTO = restriccionMapper.toDto(restriccion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRestriccionMockMvc.perform(post("/api/restriccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restriccionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Restriccion in the database
        List<Restriccion> restriccionList = restriccionRepository.findAll();
        assertThat(restriccionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllRestriccions() throws Exception {
        // Initialize the database
        restriccionRepository.save(restriccion);

        // Get all the restriccionList
        restRestriccionMockMvc.perform(get("/api/restriccions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(restriccion.getId())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)));
    }
    
    @Test
    public void getRestriccion() throws Exception {
        // Initialize the database
        restriccionRepository.save(restriccion);

        // Get the restriccion
        restRestriccionMockMvc.perform(get("/api/restriccions/{id}", restriccion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(restriccion.getId()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO));
    }
    @Test
    public void getNonExistingRestriccion() throws Exception {
        // Get the restriccion
        restRestriccionMockMvc.perform(get("/api/restriccions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRestriccion() throws Exception {
        // Initialize the database
        restriccionRepository.save(restriccion);

        int databaseSizeBeforeUpdate = restriccionRepository.findAll().size();

        // Update the restriccion
        Restriccion updatedRestriccion = restriccionRepository.findById(restriccion.getId()).get();
        updatedRestriccion
            .tipo(UPDATED_TIPO);
        RestriccionDTO restriccionDTO = restriccionMapper.toDto(updatedRestriccion);

        restRestriccionMockMvc.perform(put("/api/restriccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restriccionDTO)))
            .andExpect(status().isOk());

        // Validate the Restriccion in the database
        List<Restriccion> restriccionList = restriccionRepository.findAll();
        assertThat(restriccionList).hasSize(databaseSizeBeforeUpdate);
        Restriccion testRestriccion = restriccionList.get(restriccionList.size() - 1);
        assertThat(testRestriccion.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    public void updateNonExistingRestriccion() throws Exception {
        int databaseSizeBeforeUpdate = restriccionRepository.findAll().size();

        // Create the Restriccion
        RestriccionDTO restriccionDTO = restriccionMapper.toDto(restriccion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRestriccionMockMvc.perform(put("/api/restriccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restriccionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Restriccion in the database
        List<Restriccion> restriccionList = restriccionRepository.findAll();
        assertThat(restriccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRestriccion() throws Exception {
        // Initialize the database
        restriccionRepository.save(restriccion);

        int databaseSizeBeforeDelete = restriccionRepository.findAll().size();

        // Delete the restriccion
        restRestriccionMockMvc.perform(delete("/api/restriccions/{id}", restriccion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Restriccion> restriccionList = restriccionRepository.findAll();
        assertThat(restriccionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
