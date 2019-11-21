package pe.edu.ss.demoColegio.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.ss.demoColegio.sendMail;
import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.model.entity.Apoderado;
import pe.edu.ss.demoColegio.model.entity.Grado;
import pe.edu.ss.demoColegio.model.entity.Matricula;
import pe.edu.ss.demoColegio.model.entity.Seccion;
import pe.edu.ss.demoColegio.model.entity.Usuario;
import pe.edu.ss.demoColegio.service.AlumnoService;
import pe.edu.ss.demoColegio.service.ApoderadoService;
import pe.edu.ss.demoColegio.service.GradoService;
import pe.edu.ss.demoColegio.service.MatriculaService;
import pe.edu.ss.demoColegio.service.SeccionService;
import pe.edu.ss.demoColegio.service.UsuarioService;

@Controller
@RequestMapping("/alumno")
@SessionAttributes({ "alumno", "matricula","apoderado" ,"usuario"})


public class AlumnoController {

	@Autowired
	private sendMail smtpMailSender;
	
	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private MatriculaService matriculaService;

	@Autowired
	private SeccionService seccionSevice;
	

	@Autowired
	private ApoderadoService apoderadoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@InitBinder("alumno")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		binder.registerCustomEditor(Date.class, "fechaNac", new CustomDateEditor(dateFormatter, true));
	}
	/*
	 * @GetMapping("/opciones") public String opciones() { return
	 * "/alumno/opciones"; }
	 */

	@GetMapping
	public String inicio(Model model) {
		try {
			List<Alumno> alumnos = alumnoService.findAll();
			model.addAttribute("alumnos", alumnos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "alumno/inicio";
	}

	@GetMapping("nuevo") // nuevo medico->html nuevo
	public String nuevo(Model model) {
		Alumno alumno = new Alumno();
		model.addAttribute("alumno", alumno);

		return "alumno/nuevo";
	}

	@GetMapping("edit/{id}")
	public String editar(@PathVariable("id") String id, Model model) {
		try {

			Optional<Alumno> optional = alumnoService.findById(id);

			if (optional.isPresent()) {

				model.addAttribute("alumno", optional.get());

			} else {
				return "redirect:/alumno";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "alumno/edit";
	}

	@GetMapping("del/{id}")
	public String eliminar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Alumno> alumnos = alumnoService.findById(id);
			if (alumnos.isPresent()) {
				alumnoService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violacion contra el principio de Integridad Referencial");
			try {
				List<Alumno> alumnos = alumnoService.findAll();
				model.addAttribute("alumnos", alumnos);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "alumno/nuevo";
		}
		return "redirect:/alumno";
	}
	
	
	

	@GetMapping("info/{id}")
	public String info(@PathVariable("id") String id, Model model) {
		try {
			Optional<Alumno> alumnos = alumnoService.findById(id);

			Optional<Seccion> secciones =seccionSevice.findById(id);
			if (alumnos.isPresent()) {
				model.addAttribute("alumnos", alumnos.get());
	
				model.addAttribute("alumnos", secciones.get());
			} else {
				return "redirect://alumno";
			}
		} catch (Exception e) {
		}
		return "alumno/info";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("alumno") Alumno alumno, Model model, SessionStatus status, 
			@Valid Alumno alum,
			BindingResult result) {
		if (result.hasErrors()) {
			return "alumno/nuevo";
		}
		try {
			alumnoService.save(alumno);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
			// return "/alumno/nuevo";
		}
		return "redirect:/alumno";
	}

	@GetMapping("{id}/nuevamatricula")
	public String nuevoMatricula(@PathVariable("id") String id, Model model) {
		Matricula matricula = new Matricula();
		try {
			Optional<Alumno> alumno = alumnoService.findById(id);
			List<Seccion> secciones = seccionSevice.findAll();
			model.addAttribute("secciones", secciones);
			if (alumno.isPresent()) {
				matricula.setAlumno(alumno.get());
				model.addAttribute("matricula", matricula);
			} else {
				return "redirect:/alumno";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "alumno/nuevamatricula";
	}

	@PostMapping("savematricula")
	public String saveMatricula(@ModelAttribute("matricula") Matricula matricula, Model model, SessionStatus status) {
		try {
			
			smtpMailSender.send(matricula.getAlumno().getDireccion(), "Matricula Registrada", "Estudiante, te matriculaste en el colegio Semillitas del saber, tu codigo de estudiante es:"+ matricula.getAlumno().getCodigo());
			
			
			matriculaService.save(matricula);
			status.setComplete();
		} catch (Exception e) {
			System.out.println("Ocurrio un error");
		}
		return "redirect:/alumno/info/" + matricula.getAlumno().getCodigo();
	}
	
	@GetMapping("{id}/nuevoapoderado")
	public String nuevoApoderado(@PathVariable("id") String id, Model model) {
		Apoderado apoderado= new Apoderado();
		try {
			Optional<Alumno> alumno = alumnoService.findById(id);
			if (alumno.isPresent()) {
				apoderado.setAlumno(alumno.get());
				model.addAttribute("apoderado", apoderado);
			} else {
				return "redirect:/alumno";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "alumno/nuevoapoderado";
	}
	
	@PostMapping("saveapoderado")
	public String saveApoderado(@ModelAttribute("apoderado") Apoderado apoderado, Model model, SessionStatus status, @Valid Apoderado apod,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "alumno/nuevoapoderado";
			}
		
		try {
			apoderadoService.save(apoderado);
			status.setComplete();
		} catch (Exception e) {
			System.out.println("Ocurrio un error");
		}
		return "redirect:/alumno/info/" + apoderado.getAlumno().getCodigo();
	}
	
	@GetMapping("{id}/nuevousuario")
	public String nuevoUsuario(@PathVariable("id") String id, Model model) {
		Usuario usuario= new Usuario();
		try {
			Optional<Alumno> alumno = alumnoService.findById(id);
			if (alumno.isPresent()) {
				usuario.setAlumno(alumno.get());
				model.addAttribute("usuario", usuario);
			} else {
				return "redirect:/alumno";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "alumno/nuevousuario";
	}
	
	@PostMapping("saveusuario")
	public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, 
			Model model, SessionStatus status) {
		
		try {
			// Verificar que el username ya exista.
			Optional<Usuario> optional 
				= usuarioService.findByUsername(usuario.getUsername());
			if(optional.isPresent()) {
				model.addAttribute("dangerRegister"
						, "ERROR - El username " 
							+ usuario.getUsername() 
							+ " ya existe ");
				return "alumno";
			} else {
				usuario.setPassword(passwordEncoder
						.encode( usuario.getPassword() ));
				usuario.addAuthority("ROLE_ALUMNO");
				usuarioService.save(usuario);
				status.setComplete();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/alumno";
	}
}
