package co.edu.ucentral.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Comparendo.
 */
@Document(collection = "comparendo")
public class Comparendo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("fecha_hora")
    private ZonedDateTime fechaHora;

    @Field("direccion")
    private String direccion;

    @Field("observaciones")
    private String observaciones;

    @Field("codigo_inmovilizacion")
    private String codigoInmovilizacion;

    @DBRef
    @Field("infracciones")
    private Set<Infraccion> infracciones = new HashSet<>();

    @DBRef
    @Field("estado")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private EstadoComparendo estado;

    @DBRef
    @Field("vehiculo")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Vehiculo vehiculo;

    @DBRef
    @Field("licenciaTransito")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Licencia licenciaTransito;

    @DBRef
    @Field("licenciaConduccion")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Licencia licenciaConduccion;

    @DBRef
    @Field("agente")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Agente agente;

    @DBRef
    @Field("ciudad")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Ciudad ciudad;

    @DBRef
    @Field("infractor")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Persona infractor;

    @DBRef
    @Field("testigo")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Persona testigo;

    @DBRef
    @Field("patio")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Patio patio;

    @DBRef
    @Field("grua")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Grua grua;

    @DBRef
    @Field("entidad")
    @JsonIgnoreProperties(value = "comparendos", allowSetters = true)
    private Entidad entidad;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getFechaHora() {
        return fechaHora;
    }

    public Comparendo fechaHora(ZonedDateTime fechaHora) {
        this.fechaHora = fechaHora;
        return this;
    }

    public void setFechaHora(ZonedDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDireccion() {
        return direccion;
    }

    public Comparendo direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Comparendo observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodigoInmovilizacion() {
        return codigoInmovilizacion;
    }

    public Comparendo codigoInmovilizacion(String codigoInmovilizacion) {
        this.codigoInmovilizacion = codigoInmovilizacion;
        return this;
    }

    public void setCodigoInmovilizacion(String codigoInmovilizacion) {
        this.codigoInmovilizacion = codigoInmovilizacion;
    }

    public Set<Infraccion> getInfracciones() {
        return infracciones;
    }

    public Comparendo infracciones(Set<Infraccion> infraccions) {
        this.infracciones = infraccions;
        return this;
    }

    public Comparendo addInfracciones(Infraccion infraccion) {
        this.infracciones.add(infraccion);
        infraccion.getComparendos().add(this);
        return this;
    }

    public Comparendo removeInfracciones(Infraccion infraccion) {
        this.infracciones.remove(infraccion);
        infraccion.getComparendos().remove(this);
        return this;
    }

    public void setInfracciones(Set<Infraccion> infraccions) {
        this.infracciones = infraccions;
    }

    public EstadoComparendo getEstado() {
        return estado;
    }

    public Comparendo estado(EstadoComparendo estadoComparendo) {
        this.estado = estadoComparendo;
        return this;
    }

    public void setEstado(EstadoComparendo estadoComparendo) {
        this.estado = estadoComparendo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Comparendo vehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        return this;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Licencia getLicenciaTransito() {
        return licenciaTransito;
    }

    public Comparendo licenciaTransito(Licencia licencia) {
        this.licenciaTransito = licencia;
        return this;
    }

    public void setLicenciaTransito(Licencia licencia) {
        this.licenciaTransito = licencia;
    }

    public Licencia getLicenciaConduccion() {
        return licenciaConduccion;
    }

    public Comparendo licenciaConduccion(Licencia licencia) {
        this.licenciaConduccion = licencia;
        return this;
    }

    public void setLicenciaConduccion(Licencia licencia) {
        this.licenciaConduccion = licencia;
    }

    public Agente getAgente() {
        return agente;
    }

    public Comparendo agente(Agente agente) {
        this.agente = agente;
        return this;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Comparendo ciudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Persona getInfractor() {
        return infractor;
    }

    public Comparendo infractor(Persona persona) {
        this.infractor = persona;
        return this;
    }

    public void setInfractor(Persona persona) {
        this.infractor = persona;
    }

    public Persona getTestigo() {
        return testigo;
    }

    public Comparendo testigo(Persona persona) {
        this.testigo = persona;
        return this;
    }

    public void setTestigo(Persona persona) {
        this.testigo = persona;
    }

    public Patio getPatio() {
        return patio;
    }

    public Comparendo patio(Patio patio) {
        this.patio = patio;
        return this;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public Grua getGrua() {
        return grua;
    }

    public Comparendo grua(Grua grua) {
        this.grua = grua;
        return this;
    }

    public void setGrua(Grua grua) {
        this.grua = grua;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public Comparendo entidad(Entidad entidad) {
        this.entidad = entidad;
        return this;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comparendo)) {
            return false;
        }
        return id != null && id.equals(((Comparendo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comparendo{" +
            "id=" + getId() +
            ", fechaHora='" + getFechaHora() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", codigoInmovilizacion='" + getCodigoInmovilizacion() + "'" +
            "}";
    }
}
