package co.edu.ucentral.app.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Licencia} entity.
 */
public class LicenciaDTO implements Serializable {
    
    private String id;

    private LocalDate fechaExpedicion;

    private LocalDate vigencia;

    private String serial;

    private Set<RestriccionDTO> restriccions = new HashSet<>();

    private String tipoLicenciaId;

    private String categoriaId;

    private String servicioId;

    private String organismoTransitoId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }

    public void setVigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Set<RestriccionDTO> getRestriccions() {
        return restriccions;
    }

    public void setRestriccions(Set<RestriccionDTO> restriccions) {
        this.restriccions = restriccions;
    }

    public String getTipoLicenciaId() {
        return tipoLicenciaId;
    }

    public void setTipoLicenciaId(String tipoLicenciaId) {
        this.tipoLicenciaId = tipoLicenciaId;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getServicioId() {
        return servicioId;
    }

    public void setServicioId(String servicioId) {
        this.servicioId = servicioId;
    }

    public String getOrganismoTransitoId() {
        return organismoTransitoId;
    }

    public void setOrganismoTransitoId(String organismoTransitoId) {
        this.organismoTransitoId = organismoTransitoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LicenciaDTO)) {
            return false;
        }

        return id != null && id.equals(((LicenciaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LicenciaDTO{" +
            "id=" + getId() +
            ", fechaExpedicion='" + getFechaExpedicion() + "'" +
            ", vigencia='" + getVigencia() + "'" +
            ", serial='" + getSerial() + "'" +
            ", restriccions='" + getRestriccions() + "'" +
            ", tipoLicenciaId='" + getTipoLicenciaId() + "'" +
            ", categoriaId='" + getCategoriaId() + "'" +
            ", servicioId='" + getServicioId() + "'" +
            ", organismoTransitoId='" + getOrganismoTransitoId() + "'" +
            "}";
    }
}
