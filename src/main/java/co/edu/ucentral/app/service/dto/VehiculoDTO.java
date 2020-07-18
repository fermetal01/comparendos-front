package co.edu.ucentral.app.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link co.edu.ucentral.app.domain.Vehiculo} entity.
 */
public class VehiculoDTO implements Serializable {
    
    private String id;

    private String placa;

    private String linea;

    private Integer modelo;

    private Integer cilindraje;

    private String color;

    private Integer capacidad;

    private String numeroMotor;

    private String vin;

    private String serie;

    private String chasis;

    private String potencia;

    private LocalDate fechaMatricula;

    private Set<RestriccionDTO> restriccions = new HashSet<>();
    private Set<PersonaDTO> personas = new HashSet<>();
    private Set<EntidadDTO> entidads = new HashSet<>();

    private String marcaId;

    private String servicioId;

    private String claseId;

    private String combustibleId;

    private String organismoTransitoId;

    private String licenciaTransitoId;

    private String personaId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Set<RestriccionDTO> getRestriccions() {
        return restriccions;
    }

    public void setRestriccions(Set<RestriccionDTO> restriccions) {
        this.restriccions = restriccions;
    }

    public Set<PersonaDTO> getPersonas() {
        return personas;
    }

    public void setPersonas(Set<PersonaDTO> personas) {
        this.personas = personas;
    }

    public Set<EntidadDTO> getEntidads() {
        return entidads;
    }

    public void setEntidads(Set<EntidadDTO> entidads) {
        this.entidads = entidads;
    }

    public String getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(String marcaId) {
        this.marcaId = marcaId;
    }

    public String getServicioId() {
        return servicioId;
    }

    public void setServicioId(String servicioId) {
        this.servicioId = servicioId;
    }

    public String getClaseId() {
        return claseId;
    }

    public void setClaseId(String claseVehiculoId) {
        this.claseId = claseVehiculoId;
    }

    public String getCombustibleId() {
        return combustibleId;
    }

    public void setCombustibleId(String combustibleId) {
        this.combustibleId = combustibleId;
    }

    public String getOrganismoTransitoId() {
        return organismoTransitoId;
    }

    public void setOrganismoTransitoId(String organismoTransitoId) {
        this.organismoTransitoId = organismoTransitoId;
    }

    public String getLicenciaTransitoId() {
        return licenciaTransitoId;
    }

    public void setLicenciaTransitoId(String licenciaId) {
        this.licenciaTransitoId = licenciaId;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VehiculoDTO)) {
            return false;
        }

        return id != null && id.equals(((VehiculoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VehiculoDTO{" +
            "id=" + getId() +
            ", placa='" + getPlaca() + "'" +
            ", linea='" + getLinea() + "'" +
            ", modelo=" + getModelo() +
            ", cilindraje=" + getCilindraje() +
            ", color='" + getColor() + "'" +
            ", capacidad=" + getCapacidad() +
            ", numeroMotor='" + getNumeroMotor() + "'" +
            ", vin='" + getVin() + "'" +
            ", serie='" + getSerie() + "'" +
            ", chasis='" + getChasis() + "'" +
            ", potencia='" + getPotencia() + "'" +
            ", fechaMatricula='" + getFechaMatricula() + "'" +
            ", restriccions='" + getRestriccions() + "'" +
            ", personas='" + getPersonas() + "'" +
            ", entidads='" + getEntidads() + "'" +
            ", marcaId='" + getMarcaId() + "'" +
            ", servicioId='" + getServicioId() + "'" +
            ", claseId='" + getClaseId() + "'" +
            ", combustibleId='" + getCombustibleId() + "'" +
            ", organismoTransitoId='" + getOrganismoTransitoId() + "'" +
            ", licenciaTransitoId='" + getLicenciaTransitoId() + "'" +
            ", personaId='" + getPersonaId() + "'" +
            "}";
    }
}
