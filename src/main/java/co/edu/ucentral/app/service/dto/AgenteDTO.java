package co.edu.ucentral.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Agente} entity.
 */
public class AgenteDTO implements Serializable {
    
    private String id;

    private String placa;


    private String personaId;

    private String rangoId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getRangoId() {
        return rangoId;
    }

    public void setRangoId(String rangoId) {
        this.rangoId = rangoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgenteDTO)) {
            return false;
        }

        return id != null && id.equals(((AgenteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgenteDTO{" +
            "id=" + getId() +
            ", placa='" + getPlaca() + "'" +
            ", personaId='" + getPersonaId() + "'" +
            ", rangoId='" + getRangoId() + "'" +
            "}";
    }
}
