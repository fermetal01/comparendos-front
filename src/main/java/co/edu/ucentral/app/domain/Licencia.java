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
 * A Licencia.
 */
@Document(collection = "licencia")
public class Licencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("fecha_expedicion")
    private LocalDate fechaExpedicion;

    @Field("vigencia")
    private LocalDate vigencia;

    @Field("serial")
    private String serial;

    @DBRef
    @Field("persona")
    private Set<Persona> personas = new HashSet<>();

    @DBRef
    @Field("vehiculo")
    private Set<Vehiculo> vehiculos = new HashSet<>();

    @DBRef
    @Field("comparendo")
    private Set<Comparendo> comparendos = new HashSet<>();

    @DBRef
    @Field("restriccions")
    private Set<Restriccion> restriccions = new HashSet<>();

    @DBRef
    @Field("tipoLicencia")
    @JsonIgnoreProperties(value = "licencias", allowSetters = true)
    private TipoLicencia tipoLicencia;

    @DBRef
    @Field("categoria")
    @JsonIgnoreProperties(value = "licencias", allowSetters = true)
    private Categoria categoria;

    @DBRef
    @Field("servicio")
    @JsonIgnoreProperties(value = "licencias", allowSetters = true)
    private Servicio servicio;

    @DBRef
    @Field("organismoTransito")
    @JsonIgnoreProperties(value = "licencias", allowSetters = true)
    private OrganismoTransito organismoTransito;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public Licencia fechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
        return this;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }

    public Licencia vigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
        return this;
    }

    public void setVigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
    }

    public String getSerial() {
        return serial;
    }

    public Licencia serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public Licencia personas(Set<Persona> personas) {
        this.personas = personas;
        return this;
    }

    public Licencia addPersona(Persona persona) {
        this.personas.add(persona);
        persona.setLicencia(this);
        return this;
    }

    public Licencia removePersona(Persona persona) {
        this.personas.remove(persona);
        persona.setLicencia(null);
        return this;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Licencia vehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public Licencia addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.setLicenciaTransito(this);
        return this;
    }

    public Licencia removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.setLicenciaTransito(null);
        return this;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Licencia comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Licencia addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setLicenciaConduccion(this);
        return this;
    }

    public Licencia removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setLicenciaConduccion(null);
        return this;
    }

    public void setComparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }

    public Set<Restriccion> getRestriccions() {
        return restriccions;
    }

    public Licencia restriccions(Set<Restriccion> restriccions) {
        this.restriccions = restriccions;
        return this;
    }

    public Licencia addRestriccion(Restriccion restriccion) {
        this.restriccions.add(restriccion);
        restriccion.getLicencias().add(this);
        return this;
    }

    public Licencia removeRestriccion(Restriccion restriccion) {
        this.restriccions.remove(restriccion);
        restriccion.getLicencias().remove(this);
        return this;
    }

    public void setRestriccions(Set<Restriccion> restriccions) {
        this.restriccions = restriccions;
    }

    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    public Licencia tipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
        return this;
    }

    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Licencia categoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Licencia servicio(Servicio servicio) {
        this.servicio = servicio;
        return this;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public Licencia organismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
        return this;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Licencia)) {
            return false;
        }
        return id != null && id.equals(((Licencia) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Licencia{" +
            "id=" + getId() +
            ", fechaExpedicion='" + getFechaExpedicion() + "'" +
            ", vigencia='" + getVigencia() + "'" +
            ", serial='" + getSerial() + "'" +
            "}";
    }
}
