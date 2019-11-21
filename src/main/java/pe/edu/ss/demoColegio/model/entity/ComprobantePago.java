package pe.edu.ss.demoColegio.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="comprobante")
public class ComprobantePago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_comprobante")
	private Integer codigo;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="descripcion" , length=20)
	private String descripcion;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "matricula_cod", referencedColumnName = "codigo_matricula")
	private Matricula matricula;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "apoderado_com", referencedColumnName = "cod_apoderado")
	private Apoderado apoderado;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	
}
