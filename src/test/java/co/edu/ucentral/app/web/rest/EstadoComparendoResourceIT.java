package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.EstadoComparendo;
import co.edu.ucentral.app.repository.EstadoComparendoRepository;
import co.edu.ucentral.app.service.EstadoComparendoService;
import co.edu.ucentral.app.service.dto.EstadoComparendoDTO;
import co.edu.ucentral.app.service.mapper.EstadoComparendoMapper;

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
 * Integration tests for the {@link EstadoComparendoResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EstadoComparendoResourceIT {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private EstadoComparendoRepository estadoComparendoRepository;

    @Autowired
    private EstadoComparendoMapper estadoComparendoMapper;

    @Autowired
    private EstadoComparendoService estadoComparendoService;

    @Autowired
    private MockMvc restEstadoComparendoMockMvc;

    private EstadoComparendo estadoComparendo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadoComparendo createEntity() {
        EstadoComparendo estadoComparendo = new EstadoComparendo()
            .tipo(DEFAULT_TIPO);
        return estadoComparendo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadoComparendo createUpdatedEntity() {
        EstadoComparendo estadoComparendo = new EstadoComparendo()
            .tipo(UPDATED_TIPO);
        return estadoComparendo;
    }

    @BeforeEach
    public void initTest() {
        estadoComparendoRepository.deleteAll();
        estadoComparendo = createEntity();
    }

    @Test
    public void createEstadoComparendo() throws Exception {
        int databaseSizeBeforeCreate = estadoComparendoRepository.findAll().size();
        // Create the EstadoComparendo
        EstadoComparendoDTO estadoComparendoDTO = estadoComparendoMapper.toDto(estadoComparendo);
        restEstadoComparendoMockMvc.perform(post("/api/estado-comparendos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoComparendoDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadoComparendo in the database
        List<EstadoComparendo> estadoComparendoList = estadoComparendoRepository.findAll();
        assertThat(estadoComparendoList).hasSize(databaseSizeBeforeCreate + 1);
        EstadoComparendo testEstadoComparendo = estadoComparendoList.get(estadoComparendoList.size() - 1);
        assertThat(testEstadoComparendo.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    public void createEstadoComparendoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadoComparendoRepository.findAll().size();

        // Create the EstadoComparendo with an existing ID
        estadoComparendo.setId("existing_id");
        EstadoComparendoDTO estadoComparendoDTO = estadoComparendoMapper.toDto(estadoComparendo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadoComparendoMockMvc.perform(post("/api/estado-comparendos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoComparendoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoComparendo in the database
        List<EstadoComparendo> estadoComparendoList = estadoComparendoRepository.findAll();
        assertThat(estadoComparendoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllEstadoComparendos() throws Exception {
        // Initialize the database
        estadoComparendoRepository.save(estadoComparendo);

        // Get all the estadoComparendoList
        restEstadoComparendoMockMvc.perform(get("/api/estado-comparendos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadoComparendo.getId())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)));
    }
    
    @Test
    public void getEstadoComparendo() throws Exception {
        // Initialize the database
        estadoComparendoRepository.save(estadoComparendo);

        // Get the estadoComparendo
        restEstadoComparendoMockMvc.perform(get("/api/estado-comparendos/{id}", estadoComparendo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(estadoComparendo.getId()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO));
    }
    @Test
    public void getNonExistingEstadoComparendo() throws Exception {
        // Get the estadoComparendo
        restEstadoComparendoMockMvc.perform(get("/api/estado-comparendos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEstadoComparendo() throws Exception {
        // Initialize the database
        estadoComparendoRepository.save(estadoComparendo);

        int databaseSizeBeforeUpdate = estadoComparendoRepository.findAll().size();

        // Update the estadoComparendo
        EstadoComparendo updatedEstadoComparendo = estadoComparendoRepository.findById(estadoComparendo.getId()).get();
        updatedEstadoComparendo
            .tipo(UPDATED_TIPO);
        EstadoComparendoDTO estadoComparendoDTO = estadoComparendoMapper.toDto(updatedEstadoComparendo);

        restEstadoComparendoMockMvc.perform(put("/api/estado-comparendos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoComparendoDTO)))
            .andExpect(status().isOk());

        // Validate the EstadoComparendo in the database
        List<EstadoComparendo> estadoComparendoList = estadoComparendoRepository.findAll();
        assertThat(estadoComparendoList).hasSize(databaseSizeBeforeUpdate);
        EstadoComparendo testEstadoComparendo = estadoComparendoList.get(estadoComparendoList.size() - 1);
        assertThat(testEstadoComparendo.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    public void updateNonExistingEstadoComparendo() throws Exception {
        int databaseSizeBeforeUpdate = estadoComparendoRepository.findAll().size();

        // Create the EstadoComparendo
        EstadoComparendoDTO estadoComparendoDTO = estadoComparendoMapper.toDto(estadoComparendo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadoComparendoMockMvc.perform(put("/api/estado-comparendos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoComparendoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoComparendo in the database
        List<EstadoComparendo> estadoComparendoList = estadoComparendoRepository.findAll();
        assertThat(estadoComparendoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEstadoComparendo() throws Exception {
        // Initialize the database
        estadoComparendoRepository.save(estadoComparendo);

        int databaseSizeBeforeDelete = estadoComparendoRepository.findAll().size();

        // Delete the estadoComparendo
        restEstadoComparendoMockMvc.perform(delete("/api/estado-comparendos/{id}", estadoComparendo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadoComparendo> estadoComparendoList = estadoComparendoRepository.findAll();
        assertThat(estadoComparendoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
