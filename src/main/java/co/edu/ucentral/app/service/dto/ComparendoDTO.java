package co.edu.ucentral.app.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Comparendo} entity.
 */
public class ComparendoDTO implements Serializable {
    
    private String id;

    private ZonedDateTime fechaHora;

    private String direccion;

    private String observaciones;

    private String codigoInmovilizacion;

    private Set<InfraccionDTO> infracciones = new HashSet<>();

    private String estadoId;

    private String vehiculoId;

    private String licenciaTransitoId;

    private String licenciaConduccionId;

    private String agenteId;

    private String ciudadId;

    private String infractorId;

    private String testigoId;

    private String patioId;

    private String gruaId;

    private String entidadId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(ZonedDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodigoInmovilizacion() {
        return codigoInmovilizacion;
    }

    public void setCodigoInmovilizacion(String codigoInmovilizacion) {
        this.codigoInmovilizacion = codigoInmovilizacion;
    }

    public Set<InfraccionDTO> getInfracciones() {
        return infracciones;
    }

    public void setInfracciones(Set<InfraccionDTO> infraccions) {
        this.infracciones = infraccions;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoComparendoId) {
        this.estadoId = estadoComparendoId;
    }

    public String getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(String vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getLicenciaTransitoId() {
        return licenciaTransitoId;
    }

    public void setLicenciaTransitoId(String licenciaId) {
        this.licenciaTransitoId = licenciaId;
    }

    public String getLicenciaConduccionId() {
        return licenciaConduccionId;
    }

    public void setLicenciaConduccionId(String licenciaId) {
        this.licenciaConduccionId = licenciaId;
    }

    public String getAgenteId() {
        return agenteId;
    }

    public void setAgenteId(String agenteId) {
        this.agenteId = agenteId;
    }

    public String getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(String ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getInfractorId() {
        return infractorId;
    }

    public void setInfractorId(String personaId) {
        this.infractorId = personaId;
    }

    public String getTestigoId() {
        return testigoId;
    }

    public void setTestigoId(String personaId) {
        this.testigoId = personaId;
    }

    public String getPatioId() {
        return patioId;
    }

    public void setPatioId(String patioId) {
        this.patioId = patioId;
    }

    public String getGruaId() {
        return gruaId;
    }

    public void setGruaId(String gruaId) {
        this.gruaId = gruaId;
    }

    public String getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(String entidadId) {
        this.entidadId = entidadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComparendoDTO)) {
            return false;
        }

        return id != null && id.equals(((ComparendoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComparendoDTO{" +
            "id=" + getId() +
            ", fechaHora='" + getFechaHora() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", codigoInmovilizacion='" + getCodigoInmovilizacion() + "'" +
            ", infracciones='" + getInfracciones() + "'" +
            ", estadoId='" + getEstadoId() + "'" +
            ", vehiculoId='" + getVehiculoId() + "'" +
            ", licenciaTransitoId='" + getLicenciaTransitoId() + "'" +
            ", licenciaConduccionId='" + getLicenciaConduccionId() + "'" +
            ", agenteId='" + getAgenteId() + "'" +
            ", ciudadId='" + getCiudadId() + "'" +
            ", infractorId='" + getInfractorId() + "'" +
            ", testigoId='" + getTestigoId() + "'" +
            ", patioId='" + getPatioId() + "'" +
            ", gruaId='" + getGruaId() + "'" +
            ", entidadId='" + getEntidadId() + "'" +
            "}";
    }
}
