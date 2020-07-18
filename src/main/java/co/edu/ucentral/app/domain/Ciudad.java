package co.edu.ucentral.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Ciudad.
 */
@Document(collection = "ciudad")
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @DBRef
    @Field("persona")
    private Set<Persona> personas = new HashSet<>();

    @DBRef
    @Field("comparendo")
    private Set<Comparendo> comparendos = new HashSet<>();

    @DBRef
    @Field("entidad")
    private Set<Entidad> entidads = new HashSet<>();

    @DBRef
    @Field("departamento")
    @JsonIgnoreProperties(value = "ciudads", allowSetters = true)
    private Departamento departamento;

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

    public Ciudad nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public Ciudad personas(Set<Persona> personas) {
        this.personas = personas;
        return this;
    }

    public Ciudad addPersona(Persona persona) {
        this.personas.add(persona);
        persona.setCiudad(this);
        return this;
    }

    public Ciudad removePersona(Persona persona) {
        this.personas.remove(persona);
        persona.setCiudad(null);
        return this;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Ciudad comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Ciudad addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setCiudad(this);
        return this;
    }

    public Ciudad removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setCiudad(null);
        return this;
    }

    public void setComparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }

    public Set<Entidad> getEntidads() {
        return entidads;
    }

    public Ciudad entidads(Set<Entidad> entidads) {
        this.entidads = entidads;
        return this;
    }

    public Ciudad addEntidad(Entidad entidad) {
        this.entidads.add(entidad);
        entidad.setCiudad(this);
        return this;
    }

    public Ciudad removeEntidad(Entidad entidad) {
        this.entidads.remove(entidad);
        entidad.setCiudad(null);
        return this;
    }

    public void setEntidads(Set<Entidad> entidads) {
        this.entidads = entidads;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Ciudad departamento(Departamento departamento) {
        this.departamento = departamento;
        return this;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ciudad)) {
            return false;
        }
        return id != null && id.equals(((Ciudad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ciudad{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
