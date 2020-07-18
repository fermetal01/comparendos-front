package co.edu.ucentral.app.web.rest;

import co.edu.ucentral.app.ComparendosApp;
import co.edu.ucentral.app.domain.Persona;
import co.edu.ucentral.app.domain.Vehiculo;
import co.edu.ucentral.app.repository.PersonaRepository;
import co.edu.ucentral.app.service.PersonaService;
import co.edu.ucentral.app.service.dto.PersonaDTO;
import co.edu.ucentral.app.service.mapper.PersonaMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersonaResource} REST controller.
 */
@SpringBootTest(classes = ComparendosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonaResourceIT {

    private static final String DEFAULT_NOMBRES = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRES = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDOS = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDOS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_NACIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_NACIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NUMERO_ID = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_CELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private MockMvc restPersonaMockMvc;

    private Persona persona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persona createEntity() {
        Persona persona = new Persona()
            .nombres(DEFAULT_NOMBRES)
            .apellidos(DEFAULT_APELLIDOS)
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .numeroId(DEFAULT_NUMERO_ID)
            .direccion(DEFAULT_DIRECCION)
            .telefono(DEFAULT_TELEFONO)
            .celular(DEFAULT_CELULAR)
            .email(DEFAULT_EMAIL);
        // Add required entity
        Vehiculo vehiculo;
        vehiculo = VehiculoResourceIT.createEntity();
        vehiculo.setId("fixed-id-for-tests");
        persona.getVehiculos().add(vehiculo);
        return persona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persona createUpdatedEntity() {
        Persona persona = new Persona()
            .nombres(UPDATED_NOMBRES)
            .apellidos(UPDATED_APELLIDOS)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .numeroId(UPDATED_NUMERO_ID)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO)
            .celular(UPDATED_CELULAR)
            .email(UPDATED_EMAIL);
        // Add required entity
        Vehiculo vehiculo;
        vehiculo = VehiculoResourceIT.createUpdatedEntity();
        vehiculo.setId("fixed-id-for-tests");
        persona.getVehiculos().add(vehiculo);
        return persona;
    }

    @BeforeEach
    public void initTest() {
        personaRepository.deleteAll();
        persona = createEntity();
    }

    @Test
    public void createPersona() throws Exception {
        int databaseSizeBeforeCreate = personaRepository.findAll().size();
        // Create the Persona
        PersonaDTO personaDTO = personaMapper.toDto(persona);
        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isCreated());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeCreate + 1);
        Persona testPersona = personaList.get(personaList.size() - 1);
        assertThat(testPersona.getNombres()).isEqualTo(DEFAULT_NOMBRES);
        assertThat(testPersona.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testPersona.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testPersona.getNumeroId()).isEqualTo(DEFAULT_NUMERO_ID);
        assertThat(testPersona.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testPersona.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testPersona.getCelular()).isEqualTo(DEFAULT_CELULAR);
        assertThat(testPersona.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    public void createPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personaRepository.findAll().size();

        // Create the Persona with an existing ID
        persona.setId("existing_id");
        PersonaDTO personaDTO = personaMapper.toDto(persona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllPersonas() throws Exception {
        // Initialize the database
        personaRepository.save(persona);

        // Get all the personaList
        restPersonaMockMvc.perform(get("/api/personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persona.getId())))
            .andExpect(jsonPath("$.[*].nombres").value(hasItem(DEFAULT_NOMBRES)))
            .andExpect(jsonPath("$.[*].apellidos").value(hasItem(DEFAULT_APELLIDOS)))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].numeroId").value(hasItem(DEFAULT_NUMERO_ID)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    public void getPersona() throws Exception {
        // Initialize the database
        personaRepository.save(persona);

        // Get the persona
        restPersonaMockMvc.perform(get("/api/personas/{id}", persona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(persona.getId()))
            .andExpect(jsonPath("$.nombres").value(DEFAULT_NOMBRES))
            .andExpect(jsonPath("$.apellidos").value(DEFAULT_APELLIDOS))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO.toString()))
            .andExpect(jsonPath("$.numeroId").value(DEFAULT_NUMERO_ID))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.celular").value(DEFAULT_CELULAR))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    public void getNonExistingPersona() throws Exception {
        // Get the persona
        restPersonaMockMvc.perform(get("/api/personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updatePersona() throws Exception {
        // Initialize the database
        personaRepository.save(persona);

        int databaseSizeBeforeUpdate = personaRepository.findAll().size();

        // Update the persona
        Persona updatedPersona = personaRepository.findById(persona.getId()).get();
        updatedPersona
            .nombres(UPDATED_NOMBRES)
            .apellidos(UPDATED_APELLIDOS)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .numeroId(UPDATED_NUMERO_ID)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO)
            .celular(UPDATED_CELULAR)
            .email(UPDATED_EMAIL);
        PersonaDTO personaDTO = personaMapper.toDto(updatedPersona);

        restPersonaMockMvc.perform(put("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isOk());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeUpdate);
        Persona testPersona = personaList.get(personaList.size() - 1);
        assertThat(testPersona.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testPersona.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testPersona.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testPersona.getNumeroId()).isEqualTo(UPDATED_NUMERO_ID);
        assertThat(testPersona.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testPersona.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testPersona.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testPersona.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    public void updateNonExistingPersona() throws Exception {
        int databaseSizeBeforeUpdate = personaRepository.findAll().size();

        // Create the Persona
        PersonaDTO personaDTO = personaMapper.toDto(persona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonaMockMvc.perform(put("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deletePersona() throws Exception {
        // Initialize the database
        personaRepository.save(persona);

        int databaseSizeBeforeDelete = personaRepository.findAll().size();

        // Delete the persona
        restPersonaMockMvc.perform(delete("/api/personas/{id}", persona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
