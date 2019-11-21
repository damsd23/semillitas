package pe.edu.ss.demoColegio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="matriculas")
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_matricula" , length=5)
	private Integer codigo;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="fecha_creacion" , nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@Column(name="estado_registro" , nullable = false)
	private String EstadoRegistro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_alumno")
	private Alumno alumno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_seccion")
	private Seccion seccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_trabajador")
	private Trabajador trabajador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_pago")
	private Pago pago;
	
	@OneToOne(mappedBy = "matricula")
	private ComprobantePago comprobantePago;
	
	
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public ComprobantePago getComprobantePago() {
		return comprobantePago;
	}

	public void setComprobantePago(ComprobantePago comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstadoRegistro() {
		return EstadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		EstadoRegistro = estadoRegistro;
	}
	
	
}
