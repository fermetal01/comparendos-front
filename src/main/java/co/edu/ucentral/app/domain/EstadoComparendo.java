package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A EstadoComparendo.
 */
@Document(collection = "estado_comparendo")
public class EstadoComparendo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tipo")
    private String tipo;

    @DBRef
    @Field("comparendo")
    private Set<Comparendo> comparendos = new HashSet<>();

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

    public EstadoComparendo tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public EstadoComparendo comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public EstadoComparendo addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setEstado(this);
        return this;
    }

    public EstadoComparendo removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setEstado(null);
        return this;
    }

    public void setComparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstadoComparendo)) {
            return false;
        }
        return id != null && id.equals(((EstadoComparendo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstadoComparendo{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
