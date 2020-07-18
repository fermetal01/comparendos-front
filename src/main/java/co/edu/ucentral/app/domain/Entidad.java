package co.edu.ucentral.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Entidad.
 */
@Document(collection = "entidad")
public class Entidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("direccion")
    private String direccion;

    @Field("telefono")
    private String telefono;

    @Field("celular")
    private String celular;

    @Field("email")
    private String email;

    @DBRef
    @Field("comparendo")
    private Set<Comparendo> comparendos = new HashSet<>();

    @DBRef
    @Field("ciudad")
    @JsonIgnoreProperties(value = "entidads", allowSetters = true)
    private Ciudad ciudad;

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

    public String getNombre() {
        return nombre;
    }

    public Entidad nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Entidad direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Entidad telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public Entidad celular(String celular) {
        this.celular = celular;
        return this;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public Entidad email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Comparendo> getComparendos() {
        return comparendos;
    }

    public Entidad comparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
        return this;
    }

    public Entidad addComparendo(Comparendo comparendo) {
        this.comparendos.add(comparendo);
        comparendo.setEntidad(this);
        return this;
    }

    public Entidad removeComparendo(Comparendo comparendo) {
        this.comparendos.remove(comparendo);
        comparendo.setEntidad(null);
        return this;
    }

    public void setComparendos(Set<Comparendo> comparendos) {
        this.comparendos = comparendos;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Entidad ciudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Entidad vehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public Entidad addVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
        vehiculo.getEntidads().add(this);
        return this;
    }

    public Entidad removeVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
        vehiculo.getEntidads().remove(this);
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
        if (!(o instanceof Entidad)) {
            return false;
        }
        return id != null && id.equals(((Entidad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Entidad{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", celular='" + getCelular() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
