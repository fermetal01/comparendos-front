package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Comparendo;
import co.edu.ucentral.app.domain.Infraccion;
import co.edu.ucentral.app.repository.ComparendoRepository;
import co.edu.ucentral.app.service.ComparendoService;
import co.edu.ucentral.app.service.dto.ComparendoDTO;
import co.edu.ucentral.app.service.mapper.ComparendoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static co.edu.ucentral.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ComparendoResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ComparendoResourceIT {

    private static final ZonedDateTime DEFAULT_FECHA_HORA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FECHA_HORA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_INMOVILIZACION = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_INMOVILIZACION = "BBBBBBBBBB";

    @Autowired
    private ComparendoRepository comparendoRepository;

    @Mock
    private ComparendoRepository comparendoRepositoryMock;

    @Autowired
    private ComparendoMapper comparendoMapper;

    @Mock
    private ComparendoService comparendoServiceMock;

    @Autowired
    private ComparendoService comparendoService;

    @Autowired
    private MockMvc restComparendoMockMvc;

    private Comparendo comparendo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comparendo createEntity() {
        Comparendo comparendo = new Comparendo()
            .fechaHora(DEFAULT_FECHA_HORA)
            .direccion(DEFAULT_DIRECCION)
            .observaciones(DEFAULT_OBSERVACIONES)
            .codigoInmovilizacion(DEFAULT_CODIGO_INMOVILIZACION);
        // Add required entity
        Infraccion infraccion;
        infraccion = InfraccionResourceIT.createEntity();
        infraccion.setId("fixed-id-for-tests");
        comparendo.getInfracciones().add(infraccion);
        return comparendo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comparendo createUpdatedEntity() {
        Comparendo comparendo = new Comparendo()
            .fechaHora(UPDATED_FECHA_HORA)
            .direccion(UPDATED_DIRECCION)
            .observaciones(UPDATED_OBSERVACIONES)
            .codigoInmovilizacion(UPDATED_CODIGO_INMOVILIZACION);
        // Add required entity
        Infraccion infraccion;
        infraccion = InfraccionResourceIT.createUpdatedEntity();
        infraccion.setId("fixed-id-for-tests");
        comparendo.getInfracciones().add(infraccion);
        return comparendo;
    }

    @BeforeEach
    public void initTest() {
        comparendoRepository.deleteAll();
        comparendo = createEntity();
    }

    @Test
    public void createComparendo() throws Exception {
        int databaseSizeBeforeCreate = comparendoRepository.findAll().size();
        // Create the Comparendo
        ComparendoDTO comparendoDTO = comparendoMapper.toDto(comparendo);
        restComparendoMockMvc.perform(post("/api/comparendos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comparendoDTO)))
            .andExpect(status().isCreated());

        // Validate the Comparendo in the database
        List<Comparendo> comparendoList = comparendoRepository.findAll();
        assertThat(comparendoList).hasSize(databaseSizeBeforeCreate + 1);
        Comparendo testComparendo = comparendoList.get(comparendoList.size() - 1);
        assertThat(testComparendo.getFechaHora()).isEqualTo(DEFAULT_FECHA_HORA);
        assertThat(testComparendo.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testComparendo.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testComparendo.getCodigoInmovilizacion()).isEqualTo(DEFAULT_CODIGO_INMOVILIZACION);
    }

    @Test
    public void createComparendoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comparendoRepository.findAll().size();

        // Create the Comparendo with an existing ID
        comparendo.setId("existing_id");
        ComparendoDTO comparendoDTO = comparendoMapper.toDto(comparendo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComparendoMockMvc.perform(post("/api/comparendos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comparendoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comparendo in the database
        List<Comparendo> comparendoList = comparendoRepository.findAll();
        assertThat(comparendoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllComparendos() throws Exception {
        // Initialize the database
        comparendoRepository.save(comparendo);

        // Get all the comparendoList
        restComparendoMockMvc.perform(get("/api/comparendos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comparendo.getId())))
            .andExpect(jsonPath("$.[*].fechaHora").value(hasItem(sameInstant(DEFAULT_FECHA_HORA))))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].codigoInmovilizacion").value(hasItem(DEFAULT_CODIGO_INMOVILIZACION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllComparendosWithEagerRelationshipsIsEnabled() throws Exception {
        when(comparendoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restComparendoMockMvc.perform(get("/api/comparendos?eagerload=true"))
            .andExpect(status().isOk());

        verify(comparendoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllComparendosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(comparendoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restComparendoMockMvc.perform(get("/api/comparendos?eagerload=true"))
            .andExpect(status().isOk());

        verify(comparendoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getComparendo() throws Exception {
        // Initialize the database
        comparendoRepository.save(comparendo);

        // Get the comparendo
        restComparendoMockMvc.perform(get("/api/comparendos/{id}", comparendo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(comparendo.getId()))
            .andExpect(jsonPath("$.fechaHora").value(sameInstant(DEFAULT_FECHA_HORA)))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES))
            .andExpect(jsonPath("$.codigoInmovilizacion").value(DEFAULT_CODIGO_INMOVILIZACION));
    }
    @Test
    public void getNonExistingComparendo() throws Exception {
        // Get the comparendo
        restComparendoMockMvc.perform(get("/api/comparendos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateComparendo() throws Exception {
        // Initialize the database
        comparendoRepository.save(comparendo);

        int databaseSizeBeforeUpdate = comparendoRepository.findAll().size();

        // Update the comparendo
        Comparendo updatedComparendo = comparendoRepository.findById(comparendo.getId()).get();
        updatedComparendo
            .fechaHora(UPDATED_FECHA_HORA)
            .direccion(UPDATED_DIRECCION)
            .observaciones(UPDATED_OBSERVACIONES)
            .codigoInmovilizacion(UPDATED_CODIGO_INMOVILIZACION);
        ComparendoDTO comparendoDTO = comparendoMapper.toDto(updatedComparendo);

        restComparendoMockMvc.perform(put("/api/comparendos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comparendoDTO)))
            .andExpect(status().isOk());

        // Validate the Comparendo in the database
        List<Comparendo> comparendoList = comparendoRepository.findAll();
        assertThat(comparendoList).hasSize(databaseSizeBeforeUpdate);
        Comparendo testComparendo = comparendoList.get(comparendoList.size() - 1);
        assertThat(testComparendo.getFechaHora()).isEqualTo(UPDATED_FECHA_HORA);
        assertThat(testComparendo.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testComparendo.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testComparendo.getCodigoInmovilizacion()).isEqualTo(UPDATED_CODIGO_INMOVILIZACION);
    }

    @Test
    public void updateNonExistingComparendo() throws Exception {
        int databaseSizeBeforeUpdate = comparendoRepository.findAll().size();

        // Create the Comparendo
        ComparendoDTO comparendoDTO = comparendoMapper.toDto(comparendo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComparendoMockMvc.perform(put("/api/comparendos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comparendoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comparendo in the database
        List<Comparendo> comparendoList = comparendoRepository.findAll();
        assertThat(comparendoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteComparendo() throws Exception {
        // Initialize the database
        comparendoRepository.save(comparendo);

        int databaseSizeBeforeDelete = comparendoRepository.findAll().size();

        // Delete the comparendo
        restComparendoMockMvc.perform(delete("/api/comparendos/{id}", comparendo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Comparendo> comparendoList = comparendoRepository.findAll();
        assertThat(comparendoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
