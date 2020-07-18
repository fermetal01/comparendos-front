package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Rango.
 */
@Document(collection = "rango")
public class Rango implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("acronimo")
    private String acronimo;

    @DBRef
    @Field("agente")
    private Set<Agente> agentes = new HashSet<>();

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

    public Rango nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public Rango acronimo(String acronimo) {
        this.acronimo = acronimo;
        return this;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public Set<Agente> getAgentes() {
        return agentes;
    }

    public Rango agentes(Set<Agente> agentes) {
        this.agentes = agentes;
        return this;
    }

    public Rango addAgente(Agente agente) {
        this.agentes.add(agente);
        agente.setRango(this);
        return this;
    }

    public Rango removeAgente(Agente agente) {
        this.agentes.remove(agente);
        agente.setRango(null);
        return this;
    }

    public void setAgentes(Set<Agente> agentes) {
        this.agentes = agentes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rango)) {
            return false;
        }
        return id != null && id.equals(((Rango) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Rango{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", acronimo='" + getAcronimo() + "'" +
            "}";
    }
}
