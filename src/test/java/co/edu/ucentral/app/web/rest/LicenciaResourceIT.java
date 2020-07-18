package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Licencia;
import co.edu.ucentral.app.domain.Restriccion;
import co.edu.ucentral.app.repository.LicenciaRepository;
import co.edu.ucentral.app.service.LicenciaService;
import co.edu.ucentral.app.service.dto.LicenciaDTO;
import co.edu.ucentral.app.service.mapper.LicenciaMapper;

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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LicenciaResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class LicenciaResourceIT {

    private static final LocalDate DEFAULT_FECHA_EXPEDICION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_EXPEDICION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VIGENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VIGENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    @Autowired
    private LicenciaRepository licenciaRepository;

    @Mock
    private LicenciaRepository licenciaRepositoryMock;

    @Autowired
    private LicenciaMapper licenciaMapper;

    @Mock
    private LicenciaService licenciaServiceMock;

    @Autowired
    private LicenciaService licenciaService;

    @Autowired
    private MockMvc restLicenciaMockMvc;

    private Licencia licencia;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Licencia createEntity() {
        Licencia licencia = new Licencia()
            .fechaExpedicion(DEFAULT_FECHA_EXPEDICION)
            .vigencia(DEFAULT_VIGENCIA)
            .serial(DEFAULT_SERIAL);
        // Add required entity
        Restriccion restriccion;
        restriccion = RestriccionResourceIT.createEntity();
        restriccion.setId("fixed-id-for-tests");
        licencia.getRestriccions().add(restriccion);
        return licencia;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Licencia createUpdatedEntity() {
        Licencia licencia = new Licencia()
            .fechaExpedicion(UPDATED_FECHA_EXPEDICION)
            .vigencia(UPDATED_VIGENCIA)
            .serial(UPDATED_SERIAL);
        // Add required entity
        Restriccion restriccion;
        restriccion = RestriccionResourceIT.createUpdatedEntity();
        restriccion.setId("fixed-id-for-tests");
        licencia.getRestriccions().add(restriccion);
        return licencia;
    }

    @BeforeEach
    public void initTest() {
        licenciaRepository.deleteAll();
        licencia = createEntity();
    }

    @Test
    public void createLicencia() throws Exception {
        int databaseSizeBeforeCreate = licenciaRepository.findAll().size();
        // Create the Licencia
        LicenciaDTO licenciaDTO = licenciaMapper.toDto(licencia);
        restLicenciaMockMvc.perform(post("/api/licencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(licenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Licencia in the database
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Licencia testLicencia = licenciaList.get(licenciaList.size() - 1);
        assertThat(testLicencia.getFechaExpedicion()).isEqualTo(DEFAULT_FECHA_EXPEDICION);
        assertThat(testLicencia.getVigencia()).isEqualTo(DEFAULT_VIGENCIA);
        assertThat(testLicencia.getSerial()).isEqualTo(DEFAULT_SERIAL);
    }

    @Test
    public void createLicenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = licenciaRepository.findAll().size();

        // Create the Licencia with an existing ID
        licencia.setId("existing_id");
        LicenciaDTO licenciaDTO = licenciaMapper.toDto(licencia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLicenciaMockMvc.perform(post("/api/licencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(licenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Licencia in the database
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllLicencias() throws Exception {
        // Initialize the database
        licenciaRepository.save(licencia);

        // Get all the licenciaList
        restLicenciaMockMvc.perform(get("/api/licencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(licencia.getId())))
            .andExpect(jsonPath("$.[*].fechaExpedicion").value(hasItem(DEFAULT_FECHA_EXPEDICION.toString())))
            .andExpect(jsonPath("$.[*].vigencia").value(hasItem(DEFAULT_VIGENCIA.toString())))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllLicenciasWithEagerRelationshipsIsEnabled() throws Exception {
        when(licenciaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restLicenciaMockMvc.perform(get("/api/licencias?eagerload=true"))
            .andExpect(status().isOk());

        verify(licenciaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllLicenciasWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(licenciaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restLicenciaMockMvc.perform(get("/api/licencias?eagerload=true"))
            .andExpect(status().isOk());

        verify(licenciaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getLicencia() throws Exception {
        // Initialize the database
        licenciaRepository.save(licencia);

        // Get the licencia
        restLicenciaMockMvc.perform(get("/api/licencias/{id}", licencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(licencia.getId()))
            .andExpect(jsonPath("$.fechaExpedicion").value(DEFAULT_FECHA_EXPEDICION.toString()))
            .andExpect(jsonPath("$.vigencia").value(DEFAULT_VIGENCIA.toString()))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL));
    }
    @Test
    public void getNonExistingLicencia() throws Exception {
        // Get the licencia
        restLicenciaMockMvc.perform(get("/api/licencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLicencia() throws Exception {
        // Initialize the database
        licenciaRepository.save(licencia);

        int databaseSizeBeforeUpdate = licenciaRepository.findAll().size();

        // Update the licencia
        Licencia updatedLicencia = licenciaRepository.findById(licencia.getId()).get();
        updatedLicencia
            .fechaExpedicion(UPDATED_FECHA_EXPEDICION)
            .vigencia(UPDATED_VIGENCIA)
            .serial(UPDATED_SERIAL);
        LicenciaDTO licenciaDTO = licenciaMapper.toDto(updatedLicencia);

        restLicenciaMockMvc.perform(put("/api/licencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(licenciaDTO)))
            .andExpect(status().isOk());

        // Validate the Licencia in the database
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeUpdate);
        Licencia testLicencia = licenciaList.get(licenciaList.size() - 1);
        assertThat(testLicencia.getFechaExpedicion()).isEqualTo(UPDATED_FECHA_EXPEDICION);
        assertThat(testLicencia.getVigencia()).isEqualTo(UPDATED_VIGENCIA);
        assertThat(testLicencia.getSerial()).isEqualTo(UPDATED_SERIAL);
    }

    @Test
    public void updateNonExistingLicencia() throws Exception {
        int databaseSizeBeforeUpdate = licenciaRepository.findAll().size();

        // Create the Licencia
        LicenciaDTO licenciaDTO = licenciaMapper.toDto(licencia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLicenciaMockMvc.perform(put("/api/licencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(licenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Licencia in the database
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLicencia() throws Exception {
        // Initialize the database
        licenciaRepository.save(licencia);

        int databaseSizeBeforeDelete = licenciaRepository.findAll().size();

        // Delete the licencia
        restLicenciaMockMvc.perform(delete("/api/licencias/{id}", licencia.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
