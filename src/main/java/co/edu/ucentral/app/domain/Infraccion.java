package co.edu.ucentral.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Infraccion.
 */
@Document(collection = "infraccion")
public class Infraccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("codigo")
    private String codigo;

    @Field("descripcion")
    private String descripcion;

    @DBRef
    @Field("comparendos")
    @JsonIgnore
    private Set<Comparendo> comparendos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Infraccion codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Infraccion descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Infraccion comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Infraccion addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.getInfracciones().add(this);
        return this;
    }

    public Infraccion removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.getInfracciones().remove(this);
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
        if (!(o instanceof Infraccion)) {
            return false;
        }
        return id != null && id.equals(((Infraccion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Infraccion{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
