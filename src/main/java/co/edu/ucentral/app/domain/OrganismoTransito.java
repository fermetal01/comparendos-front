package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrganismoTransito.
 */
@Document(collection = "organismo_transito")
public class OrganismoTransito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

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

    public String getNombre() {
        return nombre;
    }

    public OrganismoTransito nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Licencia> getLicencias() {
        return licencias;
    }

    public OrganismoTransito licencias(Set<Licencia> licencias) {
        this.licencias = licencias;
        return this;
    }

    public OrganismoTransito addLicencia(Licencia licencia) {
        this.licencias.add(licencia);
        licencia.setOrganismoTransito(this);
        return this;
    }

    public OrganismoTransito removeLicencia(Licencia licencia) {
        this.licencias.remove(licencia);
        licencia.setOrganismoTransito(null);
        return this;
    }

    public void setLicencias(Set<Licencia> licencias) {
        this.licencias = licencias;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public OrganismoTransito vehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public OrganismoTransito addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.setOrganismoTransito(this);
        return this;
    }

    public OrganismoTransito removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.setOrganismoTransito(null);
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
        if (!(o instanceof OrganismoTransito)) {
            return false;
        }
        return id != null && id.equals(((OrganismoTransito) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganismoTransito{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
