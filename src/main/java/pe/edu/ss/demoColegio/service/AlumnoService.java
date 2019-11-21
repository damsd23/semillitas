package pe.edu.ss.demoColegio.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.edu.ss.demoColegio.model.entity.Alumno;

public interface AlumnoService extends CrudService<Alumno, String>{
	boolean createPdf(List<Alumno> alumnos, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);
}
