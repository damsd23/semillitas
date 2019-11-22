package pe.edu.ss.demoColegio.model.entity;

import java.sql.Date;
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
@Table(name="trabajadores")
public class Trabajador {
	
	@Id
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="cod_trabajador" , length=5)
	private String codigo;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="dni" , length=8 , nullable=false)
	private String DNI;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="nombre_trabajador" , length=20 , nullable=false)
	private String nombre;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="apellido_Paterno" , length=20 , nullable=false)
	private String ApePat;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="apellido_Materno" , length=20 , nullable=false)
	private String ApeMat;
	

	@Column(name="fecha_Nacimiento" , nullable=false)
	private Date fechaNac;
	
	@Column(name="genero" , length=1 , nullable=false)
	private Character Genero;
	
	@Column(name="edad" , length=2 , nullable=false)
	private Integer edad;
	
	@Column(name="telefono" , length=9 , nullable=false)
	private String telefono;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="correo" , length=30 , nullable=false)
	private String correo;
	
	@NotEmpty(message="* No puede quedar vac�o")
	@Column(name="direccion" , length=100 , nullable=false)
	private String direccion;
	
	@Column(name="fecha_Ingreso" , nullable=false)
	private Date fechaIngreso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_cargo")
	private Cargo cargo;
	
	@OneToMany(mappedBy = "trabajador" , fetch = FetchType.LAZY)
	private List<Matricula> matriculas;
	
	@OneToMany(mappedBy = "trabajador" , fetch = FetchType.LAZY)
	private List<Usuario> usuarios;
	
	public Trabajador() {
		matriculas=new ArrayList<>();
		this.usuarios=new ArrayList<>();
	}
	
	public void addTrabajador(Matricula matricula) {
		matricula.setTrabajador(this);
		this.addTrabajador(matricula);
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

	

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
