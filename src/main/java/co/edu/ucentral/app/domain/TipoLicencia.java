package co.edu.ucentral.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoLicencia.
 */
@Document(collection = "tipo_licencia")
public class TipoLicencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tipo")
    private String tipo;

    @DBRef
    @Field("licencia")
    private Set<Licencia> licencias = new HashSet<>();

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

    public TipoLicencia tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Licencia> getLicencias() {
        return licencias;
    }

    public TipoLicencia licencias(Set<Licencia> licencias) {
        this.licencias = licencias;
        return this;
    }

    public TipoLicencia addLicencia(Licencia licencia) {
        this.licencias.add(licencia);
        licencia.setTipoLicencia(this);
        return this;
    }

    public TipoLicencia removeLicencia(Licencia licencia) {
        this.licencias.remove(licencia);
        licencia.setTipoLicencia(null);
        return this;
    }

    public void setLicencias(Set<Licencia> licencias) {
        this.licencias = licencias;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoLicencia)) {
            return false;
        }
        return id != null && id.equals(((TipoLicencia) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoLicencia{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
