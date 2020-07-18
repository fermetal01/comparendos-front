package co.edu.ucentral.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Restriccion} entity.
 */
public class RestriccionDTO implements Serializable {
    
    private String id;

    private String tipo;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestriccionDTO)) {
            return false;
        }

        return id != null && id.equals(((RestriccionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestriccionDTO{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
