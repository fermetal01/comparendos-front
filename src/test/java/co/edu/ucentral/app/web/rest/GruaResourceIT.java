package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Grua;
import co.edu.ucentral.app.repository.GruaRepository;
import co.edu.ucentral.app.service.GruaService;
import co.edu.ucentral.app.service.dto.GruaDTO;
import co.edu.ucentral.app.service.mapper.GruaMapper;

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
 * Integration tests for the {@link GruaResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GruaResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    @Autowired
    private GruaRepository gruaRepository;

    @Autowired
    private GruaMapper gruaMapper;

    @Autowired
    private GruaService gruaService;

    @Autowired
    private MockMvc restGruaMockMvc;

    private Grua grua;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Grua createEntity() {
        Grua grua = new Grua()
            .codigo(DEFAULT_CODIGO);
        return grua;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Grua createUpdatedEntity() {
        Grua grua = new Grua()
            .codigo(UPDATED_CODIGO);
        return grua;
    }

    @BeforeEach
    public void initTest() {
        gruaRepository.deleteAll();
        grua = createEntity();
    }

    @Test
    public void createGrua() throws Exception {
        int databaseSizeBeforeCreate = gruaRepository.findAll().size();
        // Create the Grua
        GruaDTO gruaDTO = gruaMapper.toDto(grua);
        restGruaMockMvc.perform(post("/api/gruas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gruaDTO)))
            .andExpect(status().isCreated());

        // Validate the Grua in the database
        List<Grua> gruaList = gruaRepository.findAll();
        assertThat(gruaList).hasSize(databaseSizeBeforeCreate + 1);
        Grua testGrua = gruaList.get(gruaList.size() - 1);
        assertThat(testGrua.getCodigo()).isEqualTo(DEFAULT_CODIGO);
    }

    @Test
    public void createGruaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gruaRepository.findAll().size();

        // Create the Grua with an existing ID
        grua.setId("existing_id");
        GruaDTO gruaDTO = gruaMapper.toDto(grua);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGruaMockMvc.perform(post("/api/gruas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gruaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Grua in the database
        List<Grua> gruaList = gruaRepository.findAll();
        assertThat(gruaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllGruas() throws Exception {
        // Initialize the database
        gruaRepository.save(grua);

        // Get all the gruaList
        restGruaMockMvc.perform(get("/api/gruas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grua.getId())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)));
    }
    
    @Test
    public void getGrua() throws Exception {
        // Initialize the database
        gruaRepository.save(grua);

        // Get the grua
        restGruaMockMvc.perform(get("/api/gruas/{id}", grua.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(grua.getId()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO));
    }
    @Test
    public void getNonExistingGrua() throws Exception {
        // Get the grua
        restGruaMockMvc.perform(get("/api/gruas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGrua() throws Exception {
        // Initialize the database
        gruaRepository.save(grua);

        int databaseSizeBeforeUpdate = gruaRepository.findAll().size();

        // Update the grua
        Grua updatedGrua = gruaRepository.findById(grua.getId()).get();
        updatedGrua
            .codigo(UPDATED_CODIGO);
        GruaDTO gruaDTO = gruaMapper.toDto(updatedGrua);

        restGruaMockMvc.perform(put("/api/gruas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gruaDTO)))
            .andExpect(status().isOk());

        // Validate the Grua in the database
        List<Grua> gruaList = gruaRepository.findAll();
        assertThat(gruaList).hasSize(databaseSizeBeforeUpdate);
        Grua testGrua = gruaList.get(gruaList.size() - 1);
        assertThat(testGrua.getCodigo()).isEqualTo(UPDATED_CODIGO);
    }

    @Test
    public void updateNonExistingGrua() throws Exception {
        int databaseSizeBeforeUpdate = gruaRepository.findAll().size();

        // Create the Grua
        GruaDTO gruaDTO = gruaMapper.toDto(grua);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGruaMockMvc.perform(put("/api/gruas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gruaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Grua in the database
        List<Grua> gruaList = gruaRepository.findAll();
        assertThat(gruaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGrua() throws Exception {
        // Initialize the database
        gruaRepository.save(grua);

        int databaseSizeBeforeDelete = gruaRepository.findAll().size();

        // Delete the grua
        restGruaMockMvc.perform(delete("/api/gruas/{id}", grua.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Grua> gruaList = gruaRepository.findAll();
        assertThat(gruaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
