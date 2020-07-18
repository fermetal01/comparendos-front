package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Agente;
import co.edu.ucentral.app.repository.AgenteRepository;
import co.edu.ucentral.app.service.AgenteService;
import co.edu.ucentral.app.service.dto.AgenteDTO;
import co.edu.ucentral.app.service.mapper.AgenteMapper;

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
 * Integration tests for the {@link AgenteResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AgenteResourceIT {

    private static final String DEFAULT_PLACA = "AAAAAAAAAA";
    private static final String UPDATED_PLACA = "BBBBBBBBBB";

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private AgenteMapper agenteMapper;

    @Autowired
    private AgenteService agenteService;

    @Autowired
    private MockMvc restAgenteMockMvc;

    private Agente agente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agente createEntity() {
        Agente agente = new Agente()
            .placa(DEFAULT_PLACA);
        return agente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agente createUpdatedEntity() {
        Agente agente = new Agente()
            .placa(UPDATED_PLACA);
        return agente;
    }

    @BeforeEach
    public void initTest() {
        agenteRepository.deleteAll();
        agente = createEntity();
    }

    @Test
    public void createAgente() throws Exception {
        int databaseSizeBeforeCreate = agenteRepository.findAll().size();
        // Create the Agente
        AgenteDTO agenteDTO = agenteMapper.toDto(agente);
        restAgenteMockMvc.perform(post("/api/agentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agenteDTO)))
            .andExpect(status().isCreated());

        // Validate the Agente in the database
        List<Agente> agenteList = agenteRepository.findAll();
        assertThat(agenteList).hasSize(databaseSizeBeforeCreate + 1);
        Agente testAgente = agenteList.get(agenteList.size() - 1);
        assertThat(testAgente.getPlaca()).isEqualTo(DEFAULT_PLACA);
    }

    @Test
    public void createAgenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agenteRepository.findAll().size();

        // Create the Agente with an existing ID
        agente.setId("existing_id");
        AgenteDTO agenteDTO = agenteMapper.toDto(agente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgenteMockMvc.perform(post("/api/agentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agente in the database
        List<Agente> agenteList = agenteRepository.findAll();
        assertThat(agenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAgentes() throws Exception {
        // Initialize the database
        agenteRepository.save(agente);

        // Get all the agenteList
        restAgenteMockMvc.perform(get("/api/agentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agente.getId())))
            .andExpect(jsonPath("$.[*].placa").value(hasItem(DEFAULT_PLACA)));
    }
    
    @Test
    public void getAgente() throws Exception {
        // Initialize the database
        agenteRepository.save(agente);

        // Get the agente
        restAgenteMockMvc.perform(get("/api/agentes/{id}", agente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(agente.getId()))
            .andExpect(jsonPath("$.placa").value(DEFAULT_PLACA));
    }
    @Test
    public void getNonExistingAgente() throws Exception {
        // Get the agente
        restAgenteMockMvc.perform(get("/api/agentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAgente() throws Exception {
        // Initialize the database
        agenteRepository.save(agente);

        int databaseSizeBeforeUpdate = agenteRepository.findAll().size();

        // Update the agente
        Agente updatedAgente = agenteRepository.findById(agente.getId()).get();
        updatedAgente
            .placa(UPDATED_PLACA);
        AgenteDTO agenteDTO = agenteMapper.toDto(updatedAgente);

        restAgenteMockMvc.perform(put("/api/agentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agenteDTO)))
            .andExpect(status().isOk());

        // Validate the Agente in the database
        List<Agente> agenteList = agenteRepository.findAll();
        assertThat(agenteList).hasSize(databaseSizeBeforeUpdate);
        Agente testAgente = agenteList.get(agenteList.size() - 1);
        assertThat(testAgente.getPlaca()).isEqualTo(UPDATED_PLACA);
    }

    @Test
    public void updateNonExistingAgente() throws Exception {
        int databaseSizeBeforeUpdate = agenteRepository.findAll().size();

        // Create the Agente
        AgenteDTO agenteDTO = agenteMapper.toDto(agente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgenteMockMvc.perform(put("/api/agentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agente in the database
        List<Agente> agenteList = agenteRepository.findAll();
        assertThat(agenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAgente() throws Exception {
        // Initialize the database
        agenteRepository.save(agente);

        int databaseSizeBeforeDelete = agenteRepository.findAll().size();

        // Delete the agente
        restAgenteMockMvc.perform(delete("/api/agentes/{id}", agente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Agente> agenteList = agenteRepository.findAll();
        assertThat(agenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
