package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Vehiculo;
import co.edu.ucentral.app.domain.Restriccion;
import co.edu.ucentral.app.repository.VehiculoRepository;
import co.edu.ucentral.app.service.VehiculoService;
import co.edu.ucentral.app.service.dto.VehiculoDTO;
import co.edu.ucentral.app.service.mapper.VehiculoMapper;

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
 * Integration tests for the {@link VehiculoResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class VehiculoResourceIT {

    private static final String DEFAULT_PLACA = "AAAAAAAAAA";
    private static final String UPDATED_PLACA = "BBBBBBBBBB";

    private static final String DEFAULT_LINEA = "AAAAAAAAAA";
    private static final String UPDATED_LINEA = "BBBBBBBBBB";

    private static final Integer DEFAULT_MODELO = 1;
    private static final Integer UPDATED_MODELO = 2;

    private static final Integer DEFAULT_CILINDRAJE = 1;
    private static final Integer UPDATED_CILINDRAJE = 2;

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAPACIDAD = 1;
    private static final Integer UPDATED_CAPACIDAD = 2;

    private static final String DEFAULT_NUMERO_MOTOR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_MOTOR = "BBBBBBBBBB";

    private static final String DEFAULT_VIN = "AAAAAAAAAA";
    private static final String UPDATED_VIN = "BBBBBBBBBB";

    private static final String DEFAULT_SERIE = "AAAAAAAAAA";
    private static final String UPDATED_SERIE = "BBBBBBBBBB";

    private static final String DEFAULT_CHASIS = "AAAAAAAAAA";
    private static final String UPDATED_CHASIS = "BBBBBBBBBB";

    private static final String DEFAULT_POTENCIA = "AAAAAAAAAA";
    private static final String UPDATED_POTENCIA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_MATRICULA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_MATRICULA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Mock
    private VehiculoRepository vehiculoRepositoryMock;

    @Autowired
    private VehiculoMapper vehiculoMapper;

    @Mock
    private VehiculoService vehiculoServiceMock;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private MockMvc restVehiculoMockMvc;

    private Vehiculo vehiculo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehiculo createEntity() {
        Vehiculo vehiculo = new Vehiculo()
            .placa(DEFAULT_PLACA)
            .linea(DEFAULT_LINEA)
            .modelo(DEFAULT_MODELO)
            .cilindraje(DEFAULT_CILINDRAJE)
            .color(DEFAULT_COLOR)
            .capacidad(DEFAULT_CAPACIDAD)
            .numeroMotor(DEFAULT_NUMERO_MOTOR)
            .vin(DEFAULT_VIN)
            .serie(DEFAULT_SERIE)
            .chasis(DEFAULT_CHASIS)
            .potencia(DEFAULT_POTENCIA)
            .fechaMatricula(DEFAULT_FECHA_MATRICULA);
        // Add required entity
        Restriccion restriccion;
        restriccion = RestriccionResourceIT.createEntity();
        restriccion.setId("fixed-id-for-tests");
        vehiculo.getRestriccions().add(restriccion);
        return vehiculo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehiculo createUpdatedEntity() {
        Vehiculo vehiculo = new Vehiculo()
            .placa(UPDATED_PLACA)
            .linea(UPDATED_LINEA)
            .modelo(UPDATED_MODELO)
            .cilindraje(UPDATED_CILINDRAJE)
            .color(UPDATED_COLOR)
            .capacidad(UPDATED_CAPACIDAD)
            .numeroMotor(UPDATED_NUMERO_MOTOR)
            .vin(UPDATED_VIN)
            .serie(UPDATED_SERIE)
            .chasis(UPDATED_CHASIS)
            .potencia(UPDATED_POTENCIA)
            .fechaMatricula(UPDATED_FECHA_MATRICULA);
        // Add required entity
        Restriccion restriccion;
        restriccion = RestriccionResourceIT.createUpdatedEntity();
        restriccion.setId("fixed-id-for-tests");
        vehiculo.getRestriccions().add(restriccion);
        return vehiculo;
    }

    @BeforeEach
    public void initTest() {
        vehiculoRepository.deleteAll();
        vehiculo = createEntity();
    }

    @Test
    public void createVehiculo() throws Exception {
        int databaseSizeBeforeCreate = vehiculoRepository.findAll().size();
        // Create the Vehiculo
        VehiculoDTO vehiculoDTO = vehiculoMapper.toDto(vehiculo);
        restVehiculoMockMvc.perform(post("/api/vehiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehiculoDTO)))
            .andExpect(status().isCreated());

        // Validate the Vehiculo in the database
        List<Vehiculo> vehiculoList = vehiculoRepository.findAll();
        assertThat(vehiculoList).hasSize(databaseSizeBeforeCreate + 1);
        Vehiculo testVehiculo = vehiculoList.get(vehiculoList.size() - 1);
        assertThat(testVehiculo.getPlaca()).isEqualTo(DEFAULT_PLACA);
        assertThat(testVehiculo.getLinea()).isEqualTo(DEFAULT_LINEA);
        assertThat(testVehiculo.getModelo()).isEqualTo(DEFAULT_MODELO);
        assertThat(testVehiculo.getCilindraje()).isEqualTo(DEFAULT_CILINDRAJE);
        assertThat(testVehiculo.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testVehiculo.getCapacidad()).isEqualTo(DEFAULT_CAPACIDAD);
        assertThat(testVehiculo.getNumeroMotor()).isEqualTo(DEFAULT_NUMERO_MOTOR);
        assertThat(testVehiculo.getVin()).isEqualTo(DEFAULT_VIN);
        assertThat(testVehiculo.getSerie()).isEqualTo(DEFAULT_SERIE);
        assertThat(testVehiculo.getChasis()).isEqualTo(DEFAULT_CHASIS);
        assertThat(testVehiculo.getPotencia()).isEqualTo(DEFAULT_POTENCIA);
        assertThat(testVehiculo.getFechaMatricula()).isEqualTo(DEFAULT_FECHA_MATRICULA);
    }

    @Test
    public void createVehiculoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehiculoRepository.findAll().size();

        // Create the Vehiculo with an existing ID
        vehiculo.setId("existing_id");
        VehiculoDTO vehiculoDTO = vehiculoMapper.toDto(vehiculo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehiculoMockMvc.perform(post("/api/vehiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehiculoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vehiculo in the database
        List<Vehiculo> vehiculoList = vehiculoRepository.findAll();
        assertThat(vehiculoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllVehiculos() throws Exception {
        // Initialize the database
        vehiculoRepository.save(vehiculo);

        // Get all the vehiculoList
        restVehiculoMockMvc.perform(get("/api/vehiculos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehiculo.getId())))
            .andExpect(jsonPath("$.[*].placa").value(hasItem(DEFAULT_PLACA)))
            .andExpect(jsonPath("$.[*].linea").value(hasItem(DEFAULT_LINEA)))
            .andExpect(jsonPath("$.[*].modelo").value(hasItem(DEFAULT_MODELO)))
            .andExpect(jsonPath("$.[*].cilindraje").value(hasItem(DEFAULT_CILINDRAJE)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].capacidad").value(hasItem(DEFAULT_CAPACIDAD)))
            .andExpect(jsonPath("$.[*].numeroMotor").value(hasItem(DEFAULT_NUMERO_MOTOR)))
            .andExpect(jsonPath("$.[*].vin").value(hasItem(DEFAULT_VIN)))
            .andExpect(jsonPath("$.[*].serie").value(hasItem(DEFAULT_SERIE)))
            .andExpect(jsonPath("$.[*].chasis").value(hasItem(DEFAULT_CHASIS)))
            .andExpect(jsonPath("$.[*].potencia").value(hasItem(DEFAULT_POTENCIA)))
            .andExpect(jsonPath("$.[*].fechaMatricula").value(hasItem(DEFAULT_FECHA_MATRICULA.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllVehiculosWithEagerRelationshipsIsEnabled() throws Exception {
        when(vehiculoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restVehiculoMockMvc.perform(get("/api/vehiculos?eagerload=true"))
            .andExpect(status().isOk());

        verify(vehiculoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllVehiculosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(vehiculoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restVehiculoMockMvc.perform(get("/api/vehiculos?eagerload=true"))
            .andExpect(status().isOk());

        verify(vehiculoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getVehiculo() throws Exception {
        // Initialize the database
        vehiculoRepository.save(vehiculo);

        // Get the vehiculo
        restVehiculoMockMvc.perform(get("/api/vehiculos/{id}", vehiculo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vehiculo.getId()))
            .andExpect(jsonPath("$.placa").value(DEFAULT_PLACA))
            .andExpect(jsonPath("$.linea").value(DEFAULT_LINEA))
            .andExpect(jsonPath("$.modelo").value(DEFAULT_MODELO))
            .andExpect(jsonPath("$.cilindraje").value(DEFAULT_CILINDRAJE))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.capacidad").value(DEFAULT_CAPACIDAD))
            .andExpect(jsonPath("$.numeroMotor").value(DEFAULT_NUMERO_MOTOR))
            .andExpect(jsonPath("$.vin").value(DEFAULT_VIN))
            .andExpect(jsonPath("$.serie").value(DEFAULT_SERIE))
            .andExpect(jsonPath("$.chasis").value(DEFAULT_CHASIS))
            .andExpect(jsonPath("$.potencia").value(DEFAULT_POTENCIA))
            .andExpect(jsonPath("$.fechaMatricula").value(DEFAULT_FECHA_MATRICULA.toString()));
    }
    @Test
    public void getNonExistingVehiculo() throws Exception {
        // Get the vehiculo
        restVehiculoMockMvc.perform(get("/api/vehiculos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateVehiculo() throws Exception {
        // Initialize the database
        vehiculoRepository.save(vehiculo);

        int databaseSizeBeforeUpdate = vehiculoRepository.findAll().size();

        // Update the vehiculo
        Vehiculo updatedVehiculo = vehiculoRepository.findById(vehiculo.getId()).get();
        updatedVehiculo
            .placa(UPDATED_PLACA)
            .linea(UPDATED_LINEA)
            .modelo(UPDATED_MODELO)
            .cilindraje(UPDATED_CILINDRAJE)
            .color(UPDATED_COLOR)
            .capacidad(UPDATED_CAPACIDAD)
            .numeroMotor(UPDATED_NUMERO_MOTOR)
            .vin(UPDATED_VIN)
            .serie(UPDATED_SERIE)
            .chasis(UPDATED_CHASIS)
            .potencia(UPDATED_POTENCIA)
            .fechaMatricula(UPDATED_FECHA_MATRICULA);
        VehiculoDTO vehiculoDTO = vehiculoMapper.toDto(updatedVehiculo);

        restVehiculoMockMvc.perform(put("/api/vehiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehiculoDTO)))
            .andExpect(status().isOk());

        // Validate the Vehiculo in the database
        List<Vehiculo> vehiculoList = vehiculoRepository.findAll();
        assertThat(vehiculoList).hasSize(databaseSizeBeforeUpdate);
        Vehiculo testVehiculo = vehiculoList.get(vehiculoList.size() - 1);
        assertThat(testVehiculo.getPlaca()).isEqualTo(UPDATED_PLACA);
        assertThat(testVehiculo.getLinea()).isEqualTo(UPDATED_LINEA);
        assertThat(testVehiculo.getModelo()).isEqualTo(UPDATED_MODELO);
        assertThat(testVehiculo.getCilindraje()).isEqualTo(UPDATED_CILINDRAJE);
        assertThat(testVehiculo.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testVehiculo.getCapacidad()).isEqualTo(UPDATED_CAPACIDAD);
        assertThat(testVehiculo.getNumeroMotor()).isEqualTo(UPDATED_NUMERO_MOTOR);
        assertThat(testVehiculo.getVin()).isEqualTo(UPDATED_VIN);
        assertThat(testVehiculo.getSerie()).isEqualTo(UPDATED_SERIE);
        assertThat(testVehiculo.getChasis()).isEqualTo(UPDATED_CHASIS);
        assertThat(testVehiculo.getPotencia()).isEqualTo(UPDATED_POTENCIA);
        assertThat(testVehiculo.getFechaMatricula()).isEqualTo(UPDATED_FECHA_MATRICULA);
    }

    @Test
    public void updateNonExistingVehiculo() throws Exception {
        int databaseSizeBeforeUpdate = vehiculoRepository.findAll().size();

        // Create the Vehiculo
        VehiculoDTO vehiculoDTO = vehiculoMapper.toDto(vehiculo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehiculoMockMvc.perform(put("/api/vehiculos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehiculoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vehiculo in the database
        List<Vehiculo> vehiculoList = vehiculoRepository.findAll();
        assertThat(vehiculoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteVehiculo() throws Exception {
        // Initialize the database
        vehiculoRepository.save(vehiculo);

        int databaseSizeBeforeDelete = vehiculoRepository.findAll().size();

        // Delete the vehiculo
        restVehiculoMockMvc.perform(delete("/api/vehiculos/{id}", vehiculo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vehiculo> vehiculoList = vehiculoRepository.findAll();
        assertThat(vehiculoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
