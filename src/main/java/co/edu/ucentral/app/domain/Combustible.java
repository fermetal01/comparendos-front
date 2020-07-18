package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Combustible.
 */
@Document(collection = "combustible")
public class Combustible implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @DBRef
    @Field("vehiculo")
    private Set<Vehiculo> vehiculos = new HashSet<>();

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

    public Combustible nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Combustible vehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public Combustible addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.setCombustible(this);
        return this;
    }

    public Combustible removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.setCombustible(null);
        return this;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Combustible)) {
            return false;
        }
        return id != null && id.equals(((Combustible) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Combustible{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
