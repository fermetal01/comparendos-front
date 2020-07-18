package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Infraccion;
import co.edu.ucentral.app.repository.InfraccionRepository;
import co.edu.ucentral.app.service.InfraccionService;
import co.edu.ucentral.app.service.dto.InfraccionDTO;
import co.edu.ucentral.app.service.mapper.InfraccionMapper;

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
 * Integration tests for the {@link InfraccionResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfraccionResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private InfraccionRepository infraccionRepository;

    @Autowired
    private InfraccionMapper infraccionMapper;

    @Autowired
    private InfraccionService infraccionService;

    @Autowired
    private MockMvc restInfraccionMockMvc;

    private Infraccion infraccion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Infraccion createEntity() {
        Infraccion infraccion = new Infraccion()
            .codigo(DEFAULT_CODIGO)
            .descripcion(DEFAULT_DESCRIPCION);
        return infraccion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Infraccion createUpdatedEntity() {
        Infraccion infraccion = new Infraccion()
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION);
        return infraccion;
    }

    @BeforeEach
    public void initTest() {
        infraccionRepository.deleteAll();
        infraccion = createEntity();
    }

    @Test
    public void createInfraccion() throws Exception {
        int databaseSizeBeforeCreate = infraccionRepository.findAll().size();
        // Create the Infraccion
        InfraccionDTO infraccionDTO = infraccionMapper.toDto(infraccion);
        restInfraccionMockMvc.perform(post("/api/infraccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infraccionDTO)))
            .andExpect(status().isCreated());

        // Validate the Infraccion in the database
        List<Infraccion> infraccionList = infraccionRepository.findAll();
        assertThat(infraccionList).hasSize(databaseSizeBeforeCreate + 1);
        Infraccion testInfraccion = infraccionList.get(infraccionList.size() - 1);
        assertThat(testInfraccion.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testInfraccion.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    public void createInfraccionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infraccionRepository.findAll().size();

        // Create the Infraccion with an existing ID
        infraccion.setId("existing_id");
        InfraccionDTO infraccionDTO = infraccionMapper.toDto(infraccion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfraccionMockMvc.perform(post("/api/infraccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infraccionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Infraccion in the database
        List<Infraccion> infraccionList = infraccionRepository.findAll();
        assertThat(infraccionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllInfraccions() throws Exception {
        // Initialize the database
        infraccionRepository.save(infraccion);

        // Get all the infraccionList
        restInfraccionMockMvc.perform(get("/api/infraccions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(infraccion.getId())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    public void getInfraccion() throws Exception {
        // Initialize the database
        infraccionRepository.save(infraccion);

        // Get the infraccion
        restInfraccionMockMvc.perform(get("/api/infraccions/{id}", infraccion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(infraccion.getId()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }
    @Test
    public void getNonExistingInfraccion() throws Exception {
        // Get the infraccion
        restInfraccionMockMvc.perform(get("/api/infraccions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInfraccion() throws Exception {
        // Initialize the database
        infraccionRepository.save(infraccion);

        int databaseSizeBeforeUpdate = infraccionRepository.findAll().size();

        // Update the infraccion
        Infraccion updatedInfraccion = infraccionRepository.findById(infraccion.getId()).get();
        updatedInfraccion
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION);
        InfraccionDTO infraccionDTO = infraccionMapper.toDto(updatedInfraccion);

        restInfraccionMockMvc.perform(put("/api/infraccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infraccionDTO)))
            .andExpect(status().isOk());

        // Validate the Infraccion in the database
        List<Infraccion> infraccionList = infraccionRepository.findAll();
        assertThat(infraccionList).hasSize(databaseSizeBeforeUpdate);
        Infraccion testInfraccion = infraccionList.get(infraccionList.size() - 1);
        assertThat(testInfraccion.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testInfraccion.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    public void updateNonExistingInfraccion() throws Exception {
        int databaseSizeBeforeUpdate = infraccionRepository.findAll().size();

        // Create the Infraccion
        InfraccionDTO infraccionDTO = infraccionMapper.toDto(infraccion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfraccionMockMvc.perform(put("/api/infraccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infraccionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Infraccion in the database
        List<Infraccion> infraccionList = infraccionRepository.findAll();
        assertThat(infraccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteInfraccion() throws Exception {
        // Initialize the database
        infraccionRepository.save(infraccion);

        int databaseSizeBeforeDelete = infraccionRepository.findAll().size();

        // Delete the infraccion
        restInfraccionMockMvc.perform(delete("/api/infraccions/{id}", infraccion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Infraccion> infraccionList = infraccionRepository.findAll();
        assertThat(infraccionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
