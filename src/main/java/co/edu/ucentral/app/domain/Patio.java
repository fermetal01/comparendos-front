package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Patio.
 */
@Document(collection = "patio")
public class Patio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("numero")
    private String numero;

    @Field("direccion")
    private String direccion;

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

    public String getNombre() {
        return nombre;
    }

    public Patio nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public Patio numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public Patio direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Patio comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Patio addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setPatio(this);
        return this;
    }

    public Patio removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setPatio(null);
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
        if (!(o instanceof Patio)) {
            return false;
        }
        return id != null && id.equals(((Patio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patio{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", numero='" + getNumero() + "'" +
            ", direccion='" + getDireccion() + "'" +
            "}";
    }
}
