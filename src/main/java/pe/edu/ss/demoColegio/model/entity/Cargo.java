package pe.edu.ss.demoColegio.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cargos")
public class Cargo {
	
	@Id
	@Column(name="cod_cargo" , length=3)
	private String codigo;
	
	@Column(name="descripcion" , length=30 , nullable = false)
	private String descripcion;
	
	@Column(name="estado" , length=10 , nullable = false)
	private String Estado;
	
	@OneToMany(mappedBy = "cargo" , fetch = FetchType.LAZY)
	private List<Trabajador> trabajadores;

	public Cargo() {
		trabajadores = new ArrayList<>();
	}
	
	public void addCargo(Trabajador trabajador) {
		trabajador.setCargo(this);
		this.trabajadores.add(trabajador);
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
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
	
	
	

}
