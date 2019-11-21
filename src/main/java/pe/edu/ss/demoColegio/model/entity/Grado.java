package pe.edu.ss.demoColegio.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="grados")
public class Grado {
	
	@Id
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="cod_grado" , length=4)
	private String codigo;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="descripcion" , length=50 , nullable=false)
	private String descripcion;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="estado" , length=12 , nullable=false)
	private String estado;
	
	@OneToMany(mappedBy = "grado" , fetch= FetchType.LAZY)
	private List<Seccion> secciones;

	public Grado() {
		secciones = new ArrayList<>();
	}
	
	public void addSeccion (Seccion seccion){
		seccion.setGrado(this);
		this.secciones.add(seccion);
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Seccion> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<Seccion> secciones) {
		this.secciones = secciones;
	}
}
