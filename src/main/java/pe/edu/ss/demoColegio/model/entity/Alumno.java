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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="alumnos")
public class Alumno {
	
	@Id
	@NotEmpty(message="* No puede quedar vac�o")
	@Pattern(regexp="[ES0-9]{5}+" ,  message="* Obligatorio: El c�digo debe comenzar con 'ES'. Ejemplo :ES001")
	@Column(name="cod_alumno" , length=5 )
	private String codigo;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Pattern(regexp="[0-9]{8}+", message="* Es obligatorio :  S�lo n�meros de 8 d�gitos")
	@Column(name="dni" , length=8 , nullable=false)
	private String DNI;
	
	@NotEmpty(message="*  No puede quedar vac�o")
	//@Pattern(regexp="[A-Z]+" , message="* Solo numeros")
	//@Pattern(regexp="[a-zA-Z]+")
	@Column(name="nombre_alumno" , length=20 , nullable=false)
	private String nombre;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Pattern(regexp="[a-zA-Z]+")
	@Column(name="apellido_Paterno" , length=20 , nullable=false)
	private String ApePat;
	
	//@NotNull
	@NotEmpty(message="* No puede quedar vac�o")
	@Pattern(regexp="[a-zA-Z]+")
	@Column(name="apellido_Materno" , length=20 , nullable=false)
	private String ApeMat;
	
	//@NotEmpty(message="* No puede quedar vac�o")
	//@Pattern(regexp="[0-9]+", message="* Es obligatorio :  S�lo n�meros")
	@Column(name="edad" , length=2 , nullable=false)
	private Integer edad;
	
	//@NotEmpty(message="* No puede quedar vac�o")
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name="fecha_Nacimiento" , nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaNac;
	
	//@Pattern(regexp="[M|F]+", message="* Es obligatorio :  S�lo M � F")
	@Column(name="genero" , length=1 ,nullable = false)
	private Character Genero;
	
	@Column(name="direccion" , length=30 , nullable=false)
	private String direccion;
	
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name="fecha_Ingreso" , nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@OneToMany(mappedBy = "alumno" , fetch = FetchType.LAZY)
	private List<Apoderado> apoderados;

	@OneToMany(mappedBy = "alumno" , fetch = FetchType.LAZY)
	private List<Matricula> matriculas;
	
	@OneToMany(mappedBy = "alumno" , fetch = FetchType.LAZY)
	private List<Usuario> usuarios;
	
	public Alumno() {
		this.apoderados=new ArrayList<>();
		this.matriculas=new ArrayList<>();
		this.usuarios=new ArrayList<>();
	}
	
	public void addApoderado(Apoderado apoderado) {
		apoderado.setAlumno(this);
		this.apoderados.add(apoderado);
	}
	
	public void addMatricula(Matricula matricula) {
		matricula.setAlumno(this);
		this.matriculas.add(matricula);
	}
	
	
	
	public List<Apoderado> getApoderados() {
		return apoderados;
	}

	public void setApoderados(List<Apoderado> apoderados) {
		this.apoderados = apoderados;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePat() {
		return ApePat;
	}

	public void setApePat(String apePat) {
		ApePat = apePat;
	}

	public String getApeMat() {
		return ApeMat;
	}

	public void setApeMat(String apeMat) {
		ApeMat = apeMat;
	}

	

	

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}


	public Character getGenero() {
		return Genero;
	}

	public void setGenero(Character genero) {
		Genero = genero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
	
}
