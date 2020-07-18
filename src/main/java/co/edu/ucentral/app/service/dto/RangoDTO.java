package co.edu.ucentral.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Rango} entity.
 */
public class RangoDTO implements Serializable {
    
    private String id;

    private String nombre;

    private String acronimo;

    
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

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RangoDTO)) {
            return false;
        }

        return id != null && id.equals(((RangoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RangoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", acronimo='" + getAcronimo() + "'" +
            "}";
    }
}
