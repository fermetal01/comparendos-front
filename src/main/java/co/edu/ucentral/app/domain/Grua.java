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
 * A Grua.
 */
@Document(collection = "grua")
public class Grua implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("codigo")
    private String codigo;

    @DBRef
    @Field("comparendo")
    private Set<Comparendo> comparendos = new HashSet<>();

    @DBRef
    @Field("vehiculo")
    @JsonIgnoreProperties(value = "gruas", allowSetters = true)
    private Vehiculo vehiculo;

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

    public Grua codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Grua comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Grua addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setGrua(this);
        return this;
    }

    public Grua removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setGrua(null);
        return this;
    }

    public void setComparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Grua vehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        return this;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Grua)) {
            return false;
        }
        return id != null && id.equals(((Grua) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Grua{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            "}";
    }
}
