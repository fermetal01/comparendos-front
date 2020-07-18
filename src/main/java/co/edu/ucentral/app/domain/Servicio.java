package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Servicio.
 */
@Document(collection = "servicio")
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tipo")
    private String tipo;

    @DBRef
    @Field("licencia")
    private Set<Licencia> licencias = new HashSet<>();

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

    public String getTipo() {
        return tipo;
    }

    public Servicio tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Licencia> getLicencias() {
        return licencias;
    }

    public Servicio licencias(Set<Licencia> licencias) {
        this.licencias = licencias;
        return this;
    }

    public Servicio addLicencia(Licencia licencia) {
        this.licencias.add(licencia);
        licencia.setServicio(this);
        return this;
    }

    public Servicio removeLicencia(Licencia licencia) {
        this.licencias.remove(licencia);
        licencia.setServicio(null);
        return this;
    }

    public void setLicencias(Set<Licencia> licencias) {
        this.licencias = licencias;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Servicio vehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public Servicio addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.setServicio(this);
        return this;
    }

    public Servicio removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.setServicio(null);
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
        if (!(o instanceof Servicio)) {
            return false;
        }
        return id != null && id.equals(((Servicio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Servicio{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
