package co.edu.ucentral.app.domain;

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
 * A Vehiculo.
 */
@Document(collection = "vehiculo")
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("placa")
    private String placa;

    @Field("linea")
    private String linea;

    @Field("modelo")
    private Integer modelo;

    @Field("cilindraje")
    private Integer cilindraje;

    @Field("color")
    private String color;

    @Field("capacidad")
    private Integer capacidad;

    @Field("numero_motor")
    private String numeroMotor;

    @Field("vin")
    private String vin;

    @Field("serie")
    private String serie;

    @Field("chasis")
    private String chasis;

    @Field("potencia")
    private String potencia;

    @Field("fecha_matricula")
    private LocalDate fechaMatricula;

    @DBRef
    @Field("comparendo")
    private Set<Comparendo> comparendos = new HashSet<>();

    @DBRef
    @Field("grua")
    private Set<Grua> gruas = new HashSet<>();

    @DBRef
    @Field("restriccions")
    private Set<Restriccion> restriccions = new HashSet<>();

    @DBRef
    @Field("personas")
    private Set<Persona> personas = new HashSet<>();

    @DBRef
    @Field("entidads")
    private Set<Entidad> entidads = new HashSet<>();

    @DBRef
    @Field("marca")
    @JsonIgnoreProperties(value = "vehiculos", allowSetters = true)
    private Marca marca;

    @DBRef
    @Field("servicio")
    @JsonIgnoreProperties(value = "vehiculos", allowSetters = true)
    private Servicio servicio;

    @DBRef
    @Field("clase")
    @JsonIgnoreProperties(value = "vehiculos", allowSetters = true)
    private ClaseVehiculo clase;

    @DBRef
    @Field("combustible")
    @JsonIgnoreProperties(value = "vehiculos", allowSetters = true)
    private Combustible combustible;

    @DBRef
    @Field("organismoTransito")
    @JsonIgnoreProperties(value = "vehiculos", allowSetters = true)
    private OrganismoTransito organismoTransito;

    @DBRef
    @Field("licenciaTransito")
    @JsonIgnoreProperties(value = "vehiculos", allowSetters = true)
    private Licencia licenciaTransito;

    @DBRef
    @Field("persona")
    @JsonIgnoreProperties(value = "vehiculos", allowSetters = true)
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public Vehiculo placa(String placa) {
        this.placa = placa;
        return this;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getLinea() {
        return linea;
    }

    public Vehiculo linea(String linea) {
        this.linea = linea;
        return this;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public Integer getModelo() {
        return modelo;
    }

    public Vehiculo modelo(Integer modelo) {
        this.modelo = modelo;
        return this;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }

    public Vehiculo cilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getColor() {
        return color;
    }

    public Vehiculo color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Vehiculo capacidad(Integer capacidad) {
        this.capacidad = capacidad;
        return this;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public Vehiculo numeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
        return this;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getVin() {
        return vin;
    }

    public Vehiculo vin(String vin) {
        this.vin = vin;
        return this;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSerie() {
        return serie;
    }

    public Vehiculo serie(String serie) {
        this.serie = serie;
        return this;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getChasis() {
        return chasis;
    }

    public Vehiculo chasis(String chasis) {
        this.chasis = chasis;
        return this;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getPotencia() {
        return potencia;
    }

    public Vehiculo potencia(String potencia) {
        this.potencia = potencia;
        return this;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public Vehiculo fechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
        return this;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Vehiculo comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Vehiculo addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setVehiculo(this);
        return this;
    }

    public Vehiculo removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setVehiculo(null);
        return this;
    }

    public void setComparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }

    public Set<Grua> getGruas() {
        return gruas;
    }

    public Vehiculo gruas(Set<Grua> gruas) {
        this.gruas = gruas;
        return this;
    }

    public Vehiculo addGrua(Grua grua) {
        this.gruas.add(grua);
        grua.setVehiculo(this);
        return this;
    }

    public Vehiculo removeGrua(Grua grua) {
        this.gruas.remove(grua);
        grua.setVehiculo(null);
        return this;
    }

    public void setGruas(Set<Grua> gruas) {
        this.gruas = gruas;
    }

    public Set<Restriccion> getRestriccions() {
        return restriccions;
    }

    public Vehiculo restriccions(Set<Restriccion> restriccions) {
        this.restriccions = restriccions;
        return this;
    }

    public Vehiculo addRestriccion(Restriccion restriccion) {
        this.restriccions.add(restriccion);
        restriccion.getVehiculos().add(this);
        return this;
    }

    public Vehiculo removeRestriccion(Restriccion restriccion) {
        this.restriccions.remove(restriccion);
        restriccion.getVehiculos().remove(this);
        return this;
    }

    public void setRestriccions(Set<Restriccion> restriccions) {
        this.restriccions = restriccions;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public Vehiculo personas(Set<Persona> personas) {
        this.personas = personas;
        return this;
    }

    public Vehiculo addPersona(Persona persona) {
        this.personas.add(persona);
        persona.getVehiculos().add(this);
        return this;
    }

    public Vehiculo removePersona(Persona persona) {
        this.personas.remove(persona);
        persona.getVehiculos().remove(this);
        return this;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }

    public Set<Entidad> getEntidads() {
        return entidads;
    }

    public Vehiculo entidads(Set<Entidad> entidads) {
        this.entidads = entidads;
        return this;
    }

    public Vehiculo addEntidad(Entidad entidad) {
        this.entidads.add(entidad);
        entidad.getVehiculos().add(this);
        return this;
    }

    public Vehiculo removeEntidad(Entidad entidad) {
        this.entidads.remove(entidad);
        entidad.getVehiculos().remove(this);
        return this;
    }

    public void setEntidads(Set<Entidad> entidads) {
        this.entidads = entidads;
    }

    public Marca getMarca() {
        return marca;
    }

    public Vehiculo marca(Marca marca) {
        this.marca = marca;
        return this;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Vehiculo servicio(Servicio servicio) {
        this.servicio = servicio;
        return this;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public ClaseVehiculo getClase() {
        return clase;
    }

    public Vehiculo clase(ClaseVehiculo claseVehiculo) {
        this.clase = claseVehiculo;
        return this;
    }

    public void setClase(ClaseVehiculo claseVehiculo) {
        this.clase = claseVehiculo;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public Vehiculo combustible(Combustible combustible) {
        this.combustible = combustible;
        return this;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public Vehiculo organismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
        return this;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Licencia getLicenciaTransito() {
        return licenciaTransito;
    }

    public Vehiculo licenciaTransito(Licencia licencia) {
        this.licenciaTransito = licencia;
        return this;
    }

    public void setLicenciaTransito(Licencia licencia) {
        this.licenciaTransito = licencia;
    }

    public Persona getPersona() {
        return persona;
    }

    public Vehiculo persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehiculo)) {
            return false;
        }
        return id != null && id.equals(((Vehiculo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehiculo{" +
            "id=" + getId() +
            ", placa='" + getPlaca() + "'" +
            ", linea='" + getLinea() + "'" +
            ", modelo=" + getModelo() +
            ", cilindraje=" + getCilindraje() +
            ", color='" + getColor() + "'" +
            ", capacidad=" + getCapacidad() +
            ", numeroMotor='" + getNumeroMotor() + "'" +
            ", vin='" + getVin() + "'" +
            ", serie='" + getSerie() + "'" +
            ", chasis='" + getChasis() + "'" +
            ", potencia='" + getPotencia() + "'" +
            ", fechaMatricula='" + getFechaMatricula() + "'" +
            "}";
    }
}
