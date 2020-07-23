package co.edu.ucentral.app.service.dto;

import java.io.Serializable;

import co.edu.ucentral.app.domain.Departamento;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Ciudad} entity.
 */
public class CiudadDTO implements Serializable {
    
    private String id;

    private String nombre;

    private Departamento departamento;
    
    private String departamentoId;
        
   
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CiudadDTO)) {
            return false;
        }

        return id != null && id.equals(((CiudadDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

	@Override
	public String toString() {
		return "CiudadDTO [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", departamentoId="
				+ departamentoId + "]";
	}

	


    
    
}
