package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Departamento.
 */
@Document(collection = "departamento")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @DBRef
    @Field("ciudad")
    private Set<Ciudad> ciudads = new HashSet<>();

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

    public Departamento nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Ciudad> getCiudads() {
        return ciudads;
    }

    public Departamento ciudads(Set<Ciudad> ciudads) {
        this.ciudads = ciudads;
        return this;
    }

    public Departamento addCiudad(Ciudad ciudad) {
        this.ciudads.add(ciudad);
        ciudad.setDepartamento(this);
        return this;
    }

    public Departamento removeCiudad(Ciudad ciudad) {
        this.ciudads.remove(ciudad);
        ciudad.setDepartamento(null);
        return this;
    }

    public void setCiudads(Set<Ciudad> ciudads) {
        this.ciudads = ciudads;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Departamento)) {
            return false;
        }
        return id != null && id.equals(((Departamento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Departamento{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
