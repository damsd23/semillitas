package pe.edu.ss.demoColegio.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pagos")
public class Pago {
	
	@Id
	@Column(name="cod_pago" , length=3)
	private String codigo;
	
	@Column(name="descripcion" , length=20)
	private String descripcion;
	
	@Column(name="estado" , length=20 , nullable = false)
	private String Estado;

	@Column(name="fecha_pago" , nullable=false)
	private Date fechaPago;
	
	@Column(name="metodo_pago" , length=20 , nullable=false)
	private String metodoPago;

	@OneToMany(mappedBy = "pago" , fetch = FetchType.LAZY)
	private List<Matricula> matriculas;
	
	public Pago() {
		this.matriculas=new ArrayList<>();
	}
	
	public void addPago(Matricula matricula) {
		matricula.setPago(this);
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
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	
	

	
}
