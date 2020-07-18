package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Genero.
 */
@Document(collection = "genero")
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

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

    public String getNombre() {
        return nombre;
    }

    public Genero nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public Genero personas(Set<Persona> personas) {
        this.personas = personas;
        return this;
    }

    public Genero addPersona(Persona persona) {
        this.personas.add(persona);
        persona.setGenero(this);
        return this;
    }

    public Genero removePersona(Persona persona) {
        this.personas.remove(persona);
        persona.setGenero(null);
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
        if (!(o instanceof Genero)) {
            return false;
        }
        return id != null && id.equals(((Genero) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Genero{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
