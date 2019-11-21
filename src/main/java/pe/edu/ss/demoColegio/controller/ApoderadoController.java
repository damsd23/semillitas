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

import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.model.entity.Apoderado;
import pe.edu.ss.demoColegio.service.AlumnoService;
import pe.edu.ss.demoColegio.service.ApoderadoService;

@Controller
@RequestMapping("/apoderado")
@SessionAttributes({ "apoderado", "alumno" })

public class ApoderadoController {

	@Autowired
	private ApoderadoService apoderadoService;

	@Autowired
	private AlumnoService alumnoService;

	@GetMapping
	public String inicio(Model model) {
		try {
			List<Apoderado> apoderados = apoderadoService.findAll();
			model.addAttribute("apoderados", apoderados);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "apoderado/inicio";
	}


	@GetMapping("edit/{id}")
	public String editar(@PathVariable("id") String id, Model model) {
		try {

			Optional<Apoderado> optional = apoderadoService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("apoderado", optional.get());
			} else {
				return "redirect:/apoderado";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "apoderado/edit";
	}

	@GetMapping("del/{id}")
	public String eliminar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Apoderado> apoderados = apoderadoService.findById(id);
			if (apoderados.isPresent()) {
				apoderadoService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violacion contra el principio de Integridad Referencial");
			try {
				List<Apoderado> apoderados = apoderadoService.findAll();
				model.addAttribute("apoderados", apoderados);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "apoderado/nuevo";
		}
		return "redirect:/apoderado";
	}

	@GetMapping("info/{id}")
	public String info(@PathVariable("id") String id, Model model) {
		try {
			Optional<Apoderado> apoderado = apoderadoService.findById(id);

			if (apoderado.isPresent()) {
				model.addAttribute("apoderado", apoderado.get());
			} else {
				return "redirect:/apoderado";
			}
		} catch (Exception e) {

		}
		return "apoderado/info";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("apoderado") Apoderado apoderado, Model model, SessionStatus status,
			@Valid Apoderado apoderad, BindingResult result) {
		if (result.hasErrors()) {
			return "apoderado/nuevo";
		}

		try {
			apoderadoService.save(apoderado);
			status.setComplete();

		} catch (Exception e) {
			// TODO: handle exception
			// return "/alumno/nuevo";
		}
		return "redirect:/apoderado";
	}

	@GetMapping("infoalumno/{id}")
	public String infoAlumno(@PathVariable("id") String id, Model model) {
		try {
			Optional<Alumno> alumno = alumnoService.findById(id);

			if (alumno.isPresent()) {
				model.addAttribute("alumno", alumno.get());
			} else {
				return "redirect:/apoderado";
			}
		} catch (Exception e) {

		}
		return "apoderado/infoalumno";
	}

	@GetMapping("delalumno/{id}")
	public String eliminarAlumno(@PathVariable("id") String id, Model model) {
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
			return "apoderado/nuevoalumno";
		}

		return "redirect:/apoderado";
	}

	@GetMapping("editseccion/{id}")
	public String editarSeccion(@PathVariable("id") String id, Model model) {
		try {
			Optional<Alumno> optional = alumnoService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("alumno", optional.get());
			} else {
				return "redirect:/apoderado";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "apoderado/editalumno";
	}
}
