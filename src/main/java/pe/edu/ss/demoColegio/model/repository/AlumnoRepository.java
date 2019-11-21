package pe.edu.ss.demoColegio.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.ss.demoColegio.model.entity.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String>{
	@Query
	(value="select * from alumnos where fecha_Ingreso between '20190101' and '20190330'", nativeQuery=true)
	List<Alumno> listaalumno();
}
