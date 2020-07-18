package co.edu.ucentral.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Patio} entity.
 */
public class PatioDTO implements Serializable {
    
    private String id;

    private String nombre;

    private String numero;

    private String direccion;

    
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatioDTO)) {
            return false;
        }

        return id != null && id.equals(((PatioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatioDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", numero='" + getNumero() + "'" +
            ", direccion='" + getDireccion() + "'" +
            "}";
    }
}
