package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoSanguineo.
 */
@Document(collection = "tipo_sanguineo")
public class TipoSanguineo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tipo")
    private String tipo;

    @DBRef
    @Field("persona")
    private Set<Persona> personas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public TipoSanguineo tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public TipoSanguineo personas(Set<Persona> personas) {
        this.personas = personas;
        return this;
    }

    public TipoSanguineo addPersona(Persona persona) {
        this.personas.add(persona);
        persona.setTipoSanguineo(this);
        return this;
    }

    public TipoSanguineo removePersona(Persona persona) {
        this.personas.remove(persona);
        persona.setTipoSanguineo(null);
        return this;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoSanguineo)) {
            return false;
        }
        return id != null && id.equals(((TipoSanguineo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoSanguineo{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
