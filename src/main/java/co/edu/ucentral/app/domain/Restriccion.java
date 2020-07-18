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
 * A Restriccion.
 */
@Document(collection = "restriccion")
public class Restriccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tipo")
    private String tipo;

    @DBRef
    @Field("licencias")
    @JsonIgnore
    private Set<Licencia> licencias = new HashSet<>();

    @DBRef
    @Field("vehiculos")
    @JsonIgnore
    private Set<Vehiculo> vehiculos = new HashSet<>();

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

    public Restriccion tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Licencia> getLicencias() {
        return licencias;
    }

    public Restriccion licencias(Set<Licencia> licencias) {
        this.licencias = licencias;
        return this;
    }

    public Restriccion addLicencia(Licencia licencia) {
        this.licencias.add(licencia);
        licencia.getRestriccions().add(this);
        return this;
    }

    public Restriccion removeLicencia(Licencia licencia) {
        this.licencias.remove(licencia);
        licencia.getRestriccions().remove(this);
        return this;
    }

    public void setLicencias(Set<Licencia> licencias) {
        this.licencias = licencias;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Restriccion vehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public Restriccion addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.getRestriccions().add(this);
        return this;
    }

    public Restriccion removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.getRestriccions().remove(this);
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
        if (!(o instanceof Restriccion)) {
            return false;
        }
        return id != null && id.equals(((Restriccion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Restriccion{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
