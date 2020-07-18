package co.edu.ucentral.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Persona.
 */
@Document(collection = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombres")
    private String nombres;

    @Field("apellidos")
    private String apellidos;

    @Field("fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Field("numero_id")
    private String numeroId;

    @Field("direccion")
    private String direccion;

    @Field("telefono")
    private String telefono;

    @Field("celular")
    private String celular;

    @Field("email")
    private String email;

    @DBRef
    @Field("usuario")
    private Set<User> usuarios = new HashSet<>();

    @DBRef
    @Field("agente")
    private Set<Agente> agentes = new HashSet<>();

    @DBRef
    @Field("vehiculo")
    private Set<Vehiculo> vehiculos = new HashSet<>();

    @DBRef
    @Field("comparendo")
    private Set<Comparendo> comparendos = new HashSet<>();


    @DBRef
    @Field("ciudad")
    @JsonIgnoreProperties(value = "personas", allowSetters = true)
    private Ciudad ciudad;

    @DBRef
    @Field("genero")
    @JsonIgnoreProperties(value = "personas", allowSetters = true)
    private Genero genero;

    @DBRef
    @Field("tipoSanguineo")
    @JsonIgnoreProperties(value = "personas", allowSetters = true)
    private TipoSanguineo tipoSanguineo;

    @DBRef
    @Field("tipoId")
    @JsonIgnoreProperties(value = "personas", allowSetters = true)
    private TipoIdentificacion tipoId;

    @DBRef
    @Field("licencia")
    @JsonIgnoreProperties(value = "personas", allowSetters = true)
    private Licencia licencia;


    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public Persona nombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Persona apellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Persona fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public Persona numeroId(String numeroId) {
        this.numeroId = numeroId;
        return this;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
    }

    public String getDireccion() {
        return direccion;
    }

    public Persona direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Persona telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public Persona celular(String celular) {
        this.celular = celular;
        return this;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public Persona email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<User> getUsuarios() {
        return usuarios;
    }

    public Persona usuarios(Set<User> users) {
        this.usuarios = users;
        return this;
    }

    public Persona addUsuario(User user) {
        this.usuarios.add(user);
        return this;
    }

    public Persona removeUsuario(User user) {
        this.usuarios.remove(user);
        return this;
    }

    public void setUsuarios(Set<User> users) {
        this.usuarios = users;
    }

    public Set<Agente> getAgentes() {
        return agentes;
    }

    public Persona agentes(Set<Agente> agentes) {
        this.agentes = agentes;
        return this;
    }

    public Persona addAgente(Agente agente) {
        this.agentes.add(agente);
        agente.setPersona(this);
        return this;
    }

    public Persona removeAgente(Agente agente) {
        this.agentes.remove(agente);
        agente.setPersona(null);
        return this;
    }

    public void setAgentes(Set<Agente> agentes) {
        this.agentes = agentes;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Persona comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Persona addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setTestigo(this);
        return this;
    }

    public Persona removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setTestigo(null);
        return this;
    }

    public void setComparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Persona ciudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Genero getGenero() {
        return genero;
    }

    public Persona genero(Genero genero) {
        this.genero = genero;
        return this;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public Persona tipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
        return this;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public TipoIdentificacion getTipoId() {
        return tipoId;
    }

    public Persona tipoId(TipoIdentificacion tipoIdentificacion) {
        this.tipoId = tipoIdentificacion;
        return this;
    }

    public void setTipoId(TipoIdentificacion tipoIdentificacion) {
        this.tipoId = tipoIdentificacion;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public Persona licencia(Licencia licencia) {
        this.licencia = licencia;
        return this;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Persona vehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public Persona addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.getPersonas().add(this);
        return this;
    }

    public Persona removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.getPersonas().remove(this);
        return this;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Persona)) {
            return false;
        }
        return id != null && id.equals(((Persona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Persona{" +
            "id=" + getId() +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", numeroId='" + getNumeroId() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", celular='" + getCelular() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
