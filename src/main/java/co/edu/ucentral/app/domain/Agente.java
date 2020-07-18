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
 * A Agente.
 */
@Document(collection = "agente")
public class Agente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("placa")
    private String placa;

    @DBRef
    @Field("comparendo")
    private Set<Comparendo> comparendos = new HashSet<>();

    @DBRef
    @Field("persona")
    @JsonIgnoreProperties(value = "agentes", allowSetters = true)
    private Persona persona;

    @DBRef
    @Field("rango")
    @JsonIgnoreProperties(value = "agentes", allowSetters = true)
    private Rango rango;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public Agente placa(String placa) {
        this.placa = placa;
        return this;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Agente comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Agente addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setAgente(this);
        return this;
    }

    public Agente removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setAgente(null);
        return this;
    }

    public void setComparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }

    public Persona getPersona() {
        return persona;
    }

    public Agente persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rango getRango() {
        return rango;
    }

    public Agente rango(Rango rango) {
        this.rango = rango;
        return this;
    }

    public void setRango(Rango rango) {
        this.rango = rango;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agente)) {
            return false;
        }
        return id != null && id.equals(((Agente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Agente{" +
            "id=" + getId() +
            ", placa='" + getPlaca() + "'" +
            "}";
    }
}
