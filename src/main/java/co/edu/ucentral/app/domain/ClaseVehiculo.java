package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ClaseVehiculo.
 */
@Document(collection = "clase_vehiculo")
public class ClaseVehiculo implements Serializable {

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

    public ClaseVehiculo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ClaseVehiculo vehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public ClaseVehiculo addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.setClase(this);
        return this;
    }

    public ClaseVehiculo removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.setClase(null);
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
        if (!(o instanceof ClaseVehiculo)) {
            return false;
        }
        return id != null && id.equals(((ClaseVehiculo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaseVehiculo{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
