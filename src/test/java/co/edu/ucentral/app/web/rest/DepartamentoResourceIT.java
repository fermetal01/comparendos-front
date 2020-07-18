package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Departamento;
import co.edu.ucentral.app.repository.DepartamentoRepository;
import co.edu.ucentral.app.service.DepartamentoService;
import co.edu.ucentral.app.service.dto.DepartamentoDTO;
import co.edu.ucentral.app.service.mapper.DepartamentoMapper;

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
 * Integration tests for the {@link DepartamentoResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DepartamentoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private DepartamentoMapper departamentoMapper;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private MockMvc restDepartamentoMockMvc;

    private Departamento departamento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Departamento createEntity() {
        Departamento departamento = new Departamento()
            .nombre(DEFAULT_NOMBRE);
        return departamento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Departamento createUpdatedEntity() {
        Departamento departamento = new Departamento()
            .nombre(UPDATED_NOMBRE);
        return departamento;
    }

    @BeforeEach
    public void initTest() {
        departamentoRepository.deleteAll();
        departamento = createEntity();
    }

    @Test
    public void createDepartamento() throws Exception {
        int databaseSizeBeforeCreate = departamentoRepository.findAll().size();
        // Create the Departamento
        DepartamentoDTO departamentoDTO = departamentoMapper.toDto(departamento);
        restDepartamentoMockMvc.perform(post("/api/departamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departamentoDTO)))
            .andExpect(status().isCreated());

        // Validate the Departamento in the database
        List<Departamento> departamentoList = departamentoRepository.findAll();
        assertThat(departamentoList).hasSize(databaseSizeBeforeCreate + 1);
        Departamento testDepartamento = departamentoList.get(departamentoList.size() - 1);
        assertThat(testDepartamento.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    public void createDepartamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departamentoRepository.findAll().size();

        // Create the Departamento with an existing ID
        departamento.setId("existing_id");
        DepartamentoDTO departamentoDTO = departamentoMapper.toDto(departamento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartamentoMockMvc.perform(post("/api/departamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departamentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Departamento in the database
        List<Departamento> departamentoList = departamentoRepository.findAll();
        assertThat(departamentoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllDepartamentos() throws Exception {
        // Initialize the database
        departamentoRepository.save(departamento);

        // Get all the departamentoList
        restDepartamentoMockMvc.perform(get("/api/departamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departamento.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    public void getDepartamento() throws Exception {
        // Initialize the database
        departamentoRepository.save(departamento);

        // Get the departamento
        restDepartamentoMockMvc.perform(get("/api/departamentos/{id}", departamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(departamento.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }
    @Test
    public void getNonExistingDepartamento() throws Exception {
        // Get the departamento
        restDepartamentoMockMvc.perform(get("/api/departamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDepartamento() throws Exception {
        // Initialize the database
        departamentoRepository.save(departamento);

        int databaseSizeBeforeUpdate = departamentoRepository.findAll().size();

        // Update the departamento
        Departamento updatedDepartamento = departamentoRepository.findById(departamento.getId()).get();
        updatedDepartamento
            .nombre(UPDATED_NOMBRE);
        DepartamentoDTO departamentoDTO = departamentoMapper.toDto(updatedDepartamento);

        restDepartamentoMockMvc.perform(put("/api/departamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departamentoDTO)))
            .andExpect(status().isOk());

        // Validate the Departamento in the database
        List<Departamento> departamentoList = departamentoRepository.findAll();
        assertThat(departamentoList).hasSize(databaseSizeBeforeUpdate);
        Departamento testDepartamento = departamentoList.get(departamentoList.size() - 1);
        assertThat(testDepartamento.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    public void updateNonExistingDepartamento() throws Exception {
        int databaseSizeBeforeUpdate = departamentoRepository.findAll().size();

        // Create the Departamento
        DepartamentoDTO departamentoDTO = departamentoMapper.toDto(departamento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartamentoMockMvc.perform(put("/api/departamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departamentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Departamento in the database
        List<Departamento> departamentoList = departamentoRepository.findAll();
        assertThat(departamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDepartamento() throws Exception {
        // Initialize the database
        departamentoRepository.save(departamento);

        int databaseSizeBeforeDelete = departamentoRepository.findAll().size();

        // Delete the departamento
        restDepartamentoMockMvc.perform(delete("/api/departamentos/{id}", departamento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Departamento> departamentoList = departamentoRepository.findAll();
        assertThat(departamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
