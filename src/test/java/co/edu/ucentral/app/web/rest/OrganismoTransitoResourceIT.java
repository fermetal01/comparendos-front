package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.OrganismoTransito;
import co.edu.ucentral.app.repository.OrganismoTransitoRepository;
import co.edu.ucentral.app.service.OrganismoTransitoService;
import co.edu.ucentral.app.service.dto.OrganismoTransitoDTO;
import co.edu.ucentral.app.service.mapper.OrganismoTransitoMapper;

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
 * Integration tests for the {@link OrganismoTransitoResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrganismoTransitoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private OrganismoTransitoRepository organismoTransitoRepository;

    @Autowired
    private OrganismoTransitoMapper organismoTransitoMapper;

    @Autowired
    private OrganismoTransitoService organismoTransitoService;

    @Autowired
    private MockMvc restOrganismoTransitoMockMvc;

    private OrganismoTransito organismoTransito;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrganismoTransito createEntity() {
        OrganismoTransito organismoTransito = new OrganismoTransito()
            .nombre(DEFAULT_NOMBRE);
        return organismoTransito;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrganismoTransito createUpdatedEntity() {
        OrganismoTransito organismoTransito = new OrganismoTransito()
            .nombre(UPDATED_NOMBRE);
        return organismoTransito;
    }

    @BeforeEach
    public void initTest() {
        organismoTransitoRepository.deleteAll();
        organismoTransito = createEntity();
    }

    @Test
    public void createOrganismoTransito() throws Exception {
        int databaseSizeBeforeCreate = organismoTransitoRepository.findAll().size();
        // Create the OrganismoTransito
        OrganismoTransitoDTO organismoTransitoDTO = organismoTransitoMapper.toDto(organismoTransito);
        restOrganismoTransitoMockMvc.perform(post("/api/organismo-transitos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organismoTransitoDTO)))
            .andExpect(status().isCreated());

        // Validate the OrganismoTransito in the database
        List<OrganismoTransito> organismoTransitoList = organismoTransitoRepository.findAll();
        assertThat(organismoTransitoList).hasSize(databaseSizeBeforeCreate + 1);
        OrganismoTransito testOrganismoTransito = organismoTransitoList.get(organismoTransitoList.size() - 1);
        assertThat(testOrganismoTransito.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    public void createOrganismoTransitoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organismoTransitoRepository.findAll().size();

        // Create the OrganismoTransito with an existing ID
        organismoTransito.setId("existing_id");
        OrganismoTransitoDTO organismoTransitoDTO = organismoTransitoMapper.toDto(organismoTransito);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganismoTransitoMockMvc.perform(post("/api/organismo-transitos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organismoTransitoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrganismoTransito in the database
        List<OrganismoTransito> organismoTransitoList = organismoTransitoRepository.findAll();
        assertThat(organismoTransitoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllOrganismoTransitos() throws Exception {
        // Initialize the database
        organismoTransitoRepository.save(organismoTransito);

        // Get all the organismoTransitoList
        restOrganismoTransitoMockMvc.perform(get("/api/organismo-transitos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organismoTransito.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    public void getOrganismoTransito() throws Exception {
        // Initialize the database
        organismoTransitoRepository.save(organismoTransito);

        // Get the organismoTransito
        restOrganismoTransitoMockMvc.perform(get("/api/organismo-transitos/{id}", organismoTransito.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organismoTransito.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }
    @Test
    public void getNonExistingOrganismoTransito() throws Exception {
        // Get the organismoTransito
        restOrganismoTransitoMockMvc.perform(get("/api/organismo-transitos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOrganismoTransito() throws Exception {
        // Initialize the database
        organismoTransitoRepository.save(organismoTransito);

        int databaseSizeBeforeUpdate = organismoTransitoRepository.findAll().size();

        // Update the organismoTransito
        OrganismoTransito updatedOrganismoTransito = organismoTransitoRepository.findById(organismoTransito.getId()).get();
        updatedOrganismoTransito
            .nombre(UPDATED_NOMBRE);
        OrganismoTransitoDTO organismoTransitoDTO = organismoTransitoMapper.toDto(updatedOrganismoTransito);

        restOrganismoTransitoMockMvc.perform(put("/api/organismo-transitos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organismoTransitoDTO)))
            .andExpect(status().isOk());

        // Validate the OrganismoTransito in the database
        List<OrganismoTransito> organismoTransitoList = organismoTransitoRepository.findAll();
        assertThat(organismoTransitoList).hasSize(databaseSizeBeforeUpdate);
        OrganismoTransito testOrganismoTransito = organismoTransitoList.get(organismoTransitoList.size() - 1);
        assertThat(testOrganismoTransito.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    public void updateNonExistingOrganismoTransito() throws Exception {
        int databaseSizeBeforeUpdate = organismoTransitoRepository.findAll().size();

        // Create the OrganismoTransito
        OrganismoTransitoDTO organismoTransitoDTO = organismoTransitoMapper.toDto(organismoTransito);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganismoTransitoMockMvc.perform(put("/api/organismo-transitos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organismoTransitoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrganismoTransito in the database
        List<OrganismoTransito> organismoTransitoList = organismoTransitoRepository.findAll();
        assertThat(organismoTransitoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOrganismoTransito() throws Exception {
        // Initialize the database
        organismoTransitoRepository.save(organismoTransito);

        int databaseSizeBeforeDelete = organismoTransitoRepository.findAll().size();

        // Delete the organismoTransito
        restOrganismoTransitoMockMvc.perform(delete("/api/organismo-transitos/{id}", organismoTransito.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrganismoTransito> organismoTransitoList = organismoTransitoRepository.findAll();
        assertThat(organismoTransitoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
