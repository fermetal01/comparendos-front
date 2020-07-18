package co.edu.ucentral.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.TipoIdentificacion} entity.
 */
public class TipoIdentificacionDTO implements Serializable {
    
    private String id;

    private String nombre;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoIdentificacionDTO)) {
            return false;
        }

        return id != null && id.equals(((TipoIdentificacionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoIdentificacionDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
