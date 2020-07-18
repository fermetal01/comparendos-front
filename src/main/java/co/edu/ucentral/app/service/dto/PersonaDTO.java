package co.edu.ucentral.app.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Persona} entity.
 */
public class PersonaDTO implements Serializable {
    
    private String id;

    private String nombres;

    private String apellidos;

    private LocalDate fechaNacimiento;

    private String numeroId;

    private String direccion;

    private String telefono;

    private String celular;

    private String email;


    private String ciudadId;

    private String generoId;

    private String tipoSanguineoId;

    private String tipoIdId;

    private String licenciaId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(String ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getGeneroId() {
        return generoId;
    }

    public void setGeneroId(String generoId) {
        this.generoId = generoId;
    }

    public String getTipoSanguineoId() {
        return tipoSanguineoId;
    }

    public void setTipoSanguineoId(String tipoSanguineoId) {
        this.tipoSanguineoId = tipoSanguineoId;
    }

    public String getTipoIdId() {
        return tipoIdId;
    }

    public void setTipoIdId(String tipoIdentificacionId) {
        this.tipoIdId = tipoIdentificacionId;
    }

    public String getLicenciaId() {
        return licenciaId;
    }

    public void setLicenciaId(String licenciaId) {
        this.licenciaId = licenciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((PersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonaDTO{" +
            "id=" + getId() +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", numeroId='" + getNumeroId() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", celular='" + getCelular() + "'" +
            ", email='" + getEmail() + "'" +
            ", ciudadId='" + getCiudadId() + "'" +
            ", generoId='" + getGeneroId() + "'" +
            ", tipoSanguineoId='" + getTipoSanguineoId() + "'" +
            ", tipoIdId='" + getTipoIdId() + "'" +
            ", licenciaId='" + getLicenciaId() + "'" +
            "}";
    }
}
