package pe.edu.ss.demoColegio.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.ss.demoColegio.sendMail;
import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.model.entity.Grado;
import pe.edu.ss.demoColegio.model.entity.Matricula;
import pe.edu.ss.demoColegio.model.entity.Seccion;
import pe.edu.ss.demoColegio.service.AlumnoService;
import pe.edu.ss.demoColegio.service.ApoderadoService;
import pe.edu.ss.demoColegio.service.GradoService;
import pe.edu.ss.demoColegio.service.MatriculaService;
import pe.edu.ss.demoColegio.service.SeccionService;


@Controller
@RequestMapping("/matricula")
@SessionAttributes({ "matricula","alumno","apoderado","grado", "seccion" ,"pago"})
public class MatriculaController {
	
	
	@Autowired
	private sendMail smtpMailSender;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private SeccionService seccionService;
	
	@Autowired
	private GradoService gradoService;

	@Autowired
	private ApoderadoService apoderadoService;
	
	/*@InitBinder("matricula")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		//binder.registerCustomEditor(Date.class, "fechaNac", new CustomDateEditor(dateFormatter, true));
	}*/
	
	
	//Online
	
	
	//Matricula en linea
	@GetMapping("inicio")
	public String matriculaOnline() {
		return "matricula/inicio";
	}
	
	@GetMapping("documentacion")
	public String documentos() {
		return "matricula/documentacion";
	}
	
	@GetMapping("login")
	public String login() {
		return "matricula/login";
	}
	
	@GetMapping("servicio")
	public String servicios() {
		return "matricula/servicio";
	}

	
	@GetMapping("{id}/matricula") 
	public String nuevo(@PathVariable("id")String id, Model model) {
		Matricula matricula=new Matricula();
		model.addAttribute("matricula", matricula);
		try {
			Optional<Alumno> alumno = alumnoService.findById(id);
			List<Seccion> secciones=seccionService.findAll();
			model.addAttribute("secciones", secciones);
			
			if (alumno.isPresent()) {
				matricula.setAlumno(alumno.get());
				model.addAttribute("matricula", matricula);
			} else {
				return "redirect:/matricula";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "matricula/matricula";
	}
	
	
	@GetMapping("info/{id}")
	public String info(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Matricula> matricula=matriculaService.findById(id);
			
			if (matricula.isPresent()) {
				model.addAttribute("matricula",matricula.get());
			} else {
				return "redirect:/matricula";
			}
		} catch (Exception e) {
		}
		return "matricula/dato";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("matricula") Matricula matricula,
			Model model, SessionStatus status) {
		
		try {
			
			smtpMailSender.send(matricula.getAlumno().getDireccion(), "Matricula Registrada", "Estudiante, te matriculaste en el colegio Semillitas del saber, tu codigo de estudiante es:"+ matricula.getAlumno().getCodigo());
			
			matriculaService.save(matricula);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
			// return "/alumno/nuevo";
		}
		return "redirect:/menu/menuestudiante/"+matricula.getAlumno().getCodigo();
	}
	
	
}
