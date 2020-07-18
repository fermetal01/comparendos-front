package co.edu.ucentral.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Grua} entity.
 */
public class GruaDTO implements Serializable {
    
    private String id;

    private String codigo;


    private String vehiculoId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(String vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GruaDTO)) {
            return false;
        }

        return id != null && id.equals(((GruaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GruaDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", vehiculoId='" + getVehiculoId() + "'" +
            "}";
    }
}
