package co.edu.ucentral.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Entidad} entity.
 */
public class EntidadDTO implements Serializable {
    
    private String id;

    private String nombre;

    private String direccion;

    private String telefono;

    private String celular;

    private String email;


    private String ciudadId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntidadDTO)) {
            return false;
        }

        return id != null && id.equals(((EntidadDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntidadDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", celular='" + getCelular() + "'" +
            ", email='" + getEmail() + "'" +
            ", ciudadId='" + getCiudadId() + "'" +
            "}";
    }
}
