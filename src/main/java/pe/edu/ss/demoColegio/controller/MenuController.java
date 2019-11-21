package pe.edu.ss.demoColegio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.model.entity.Grado;
import pe.edu.ss.demoColegio.model.entity.Seccion;
import pe.edu.ss.demoColegio.service.AlumnoService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping
	public String inicio(Model model) {
		return "menu";
	}
	
	@GetMapping("alumnos")
	public String alumnos() {
		return "alumno/inicio";
	}
	
	@GetMapping("menuestudiante/{id}")
	public String menuEstudiante(@PathVariable("id") String id, Model model) {
		
		try {
			Optional<Alumno> alumnos = alumnoService.findById(id);

			if (alumnos.isPresent()) {
				model.addAttribute("alumnos", alumnos.get());

			} else {
				return "redirect:/matricula";
			}
		} catch (Exception e) {
		}
		
		return "menuestudiante";
	}
}