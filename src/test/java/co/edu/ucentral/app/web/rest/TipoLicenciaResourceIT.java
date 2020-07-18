package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.TipoLicencia;
import co.edu.ucentral.app.repository.TipoLicenciaRepository;
import co.edu.ucentral.app.service.TipoLicenciaService;
import co.edu.ucentral.app.service.dto.TipoLicenciaDTO;
import co.edu.ucentral.app.service.mapper.TipoLicenciaMapper;

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
 * Integration tests for the {@link TipoLicenciaResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TipoLicenciaResourceIT {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private TipoLicenciaRepository tipoLicenciaRepository;

    @Autowired
    private TipoLicenciaMapper tipoLicenciaMapper;

    @Autowired
    private TipoLicenciaService tipoLicenciaService;

    @Autowired
    private MockMvc restTipoLicenciaMockMvc;

    private TipoLicencia tipoLicencia;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoLicencia createEntity() {
        TipoLicencia tipoLicencia = new TipoLicencia()
            .tipo(DEFAULT_TIPO);
        return tipoLicencia;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoLicencia createUpdatedEntity() {
        TipoLicencia tipoLicencia = new TipoLicencia()
            .tipo(UPDATED_TIPO);
        return tipoLicencia;
    }

    @BeforeEach
    public void initTest() {
        tipoLicenciaRepository.deleteAll();
        tipoLicencia = createEntity();
    }

    @Test
    public void createTipoLicencia() throws Exception {
        int databaseSizeBeforeCreate = tipoLicenciaRepository.findAll().size();
        // Create the TipoLicencia
        TipoLicenciaDTO tipoLicenciaDTO = tipoLicenciaMapper.toDto(tipoLicencia);
        restTipoLicenciaMockMvc.perform(post("/api/tipo-licencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoLicenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoLicencia in the database
        List<TipoLicencia> tipoLicenciaList = tipoLicenciaRepository.findAll();
        assertThat(tipoLicenciaList).hasSize(databaseSizeBeforeCreate + 1);
        TipoLicencia testTipoLicencia = tipoLicenciaList.get(tipoLicenciaList.size() - 1);
        assertThat(testTipoLicencia.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    public void createTipoLicenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoLicenciaRepository.findAll().size();

        // Create the TipoLicencia with an existing ID
        tipoLicencia.setId("existing_id");
        TipoLicenciaDTO tipoLicenciaDTO = tipoLicenciaMapper.toDto(tipoLicencia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoLicenciaMockMvc.perform(post("/api/tipo-licencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoLicenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoLicencia in the database
        List<TipoLicencia> tipoLicenciaList = tipoLicenciaRepository.findAll();
        assertThat(tipoLicenciaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllTipoLicencias() throws Exception {
        // Initialize the database
        tipoLicenciaRepository.save(tipoLicencia);

        // Get all the tipoLicenciaList
        restTipoLicenciaMockMvc.perform(get("/api/tipo-licencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoLicencia.getId())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)));
    }
    
    @Test
    public void getTipoLicencia() throws Exception {
        // Initialize the database
        tipoLicenciaRepository.save(tipoLicencia);

        // Get the tipoLicencia
        restTipoLicenciaMockMvc.perform(get("/api/tipo-licencias/{id}", tipoLicencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoLicencia.getId()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO));
    }
    @Test
    public void getNonExistingTipoLicencia() throws Exception {
        // Get the tipoLicencia
        restTipoLicenciaMockMvc.perform(get("/api/tipo-licencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTipoLicencia() throws Exception {
        // Initialize the database
        tipoLicenciaRepository.save(tipoLicencia);

        int databaseSizeBeforeUpdate = tipoLicenciaRepository.findAll().size();

        // Update the tipoLicencia
        TipoLicencia updatedTipoLicencia = tipoLicenciaRepository.findById(tipoLicencia.getId()).get();
        updatedTipoLicencia
            .tipo(UPDATED_TIPO);
        TipoLicenciaDTO tipoLicenciaDTO = tipoLicenciaMapper.toDto(updatedTipoLicencia);

        restTipoLicenciaMockMvc.perform(put("/api/tipo-licencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoLicenciaDTO)))
            .andExpect(status().isOk());

        // Validate the TipoLicencia in the database
        List<TipoLicencia> tipoLicenciaList = tipoLicenciaRepository.findAll();
        assertThat(tipoLicenciaList).hasSize(databaseSizeBeforeUpdate);
        TipoLicencia testTipoLicencia = tipoLicenciaList.get(tipoLicenciaList.size() - 1);
        assertThat(testTipoLicencia.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    public void updateNonExistingTipoLicencia() throws Exception {
        int databaseSizeBeforeUpdate = tipoLicenciaRepository.findAll().size();

        // Create the TipoLicencia
        TipoLicenciaDTO tipoLicenciaDTO = tipoLicenciaMapper.toDto(tipoLicencia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoLicenciaMockMvc.perform(put("/api/tipo-licencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoLicenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoLicencia in the database
        List<TipoLicencia> tipoLicenciaList = tipoLicenciaRepository.findAll();
        assertThat(tipoLicenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTipoLicencia() throws Exception {
        // Initialize the database
        tipoLicenciaRepository.save(tipoLicencia);

        int databaseSizeBeforeDelete = tipoLicenciaRepository.findAll().size();

        // Delete the tipoLicencia
        restTipoLicenciaMockMvc.perform(delete("/api/tipo-licencias/{id}", tipoLicencia.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoLicencia> tipoLicenciaList = tipoLicenciaRepository.findAll();
        assertThat(tipoLicenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
