package pe.edu.ss.demoColegio.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="secciones")
public class Seccion {
	
	@Id
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="cod_seccion" , length=4)
	private String codigo;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="descripcion" , length=50 , nullable=false)
	private String descripcion;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="estado" , length=12 , nullable=false)
	private String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_grado")
	private Grado grado;
	
	@OneToMany(mappedBy = "seccion" , fetch = FetchType.LAZY)
	private List<Matricula> matriculas;

	public Seccion() {
		matriculas = new ArrayList<>();
	}
	
	public void addMatricula(Matricula matricula) {
		matricula.setSeccion(this);
		this.matriculas.add(matricula);
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

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

}
