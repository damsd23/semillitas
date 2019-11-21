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

import pe.edu.ss.demoColegio.model.entity.Grado;
import pe.edu.ss.demoColegio.model.entity.Seccion;
import pe.edu.ss.demoColegio.service.GradoService;
import pe.edu.ss.demoColegio.service.SeccionService;

@Controller
@RequestMapping("/grado")
@SessionAttributes({ "grado", "seccion" })

public class GradoController {

	@Autowired
	private GradoService gradoService;

	@Autowired
	private SeccionService seccionService;

	@GetMapping
	public String inicio(Model model) {
		try {
			List<Grado> grados = gradoService.findAll();
			model.addAttribute("grados", grados);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "grado/inicio";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
		Grado grado = new Grado();
		model.addAttribute("grado", grado);
		return "grado/nuevo";
	}

	@GetMapping("edit/{id}")
	public String editar(@PathVariable("id") String id, Model model) {
		try {

			Optional<Grado> optional = gradoService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("grado", optional.get());
			} else {
				return "redirect:/grado";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "grado/edit";
	}

	@GetMapping("del/{id}")
	public String eliminar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Grado> grados = gradoService.findById(id);
			if (grados.isPresent()) {
				gradoService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violacion contra el principio de Integridad Referencial");
			try {
				List<Grado> grados = gradoService.findAll();
				model.addAttribute("grados", grados);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "grado/inicio";
		}
		return "redirect:/grado";
	}

	@GetMapping("info/{id}")
	public String info(@PathVariable("id") String id, Model model) {
		try {
			Optional<Grado> grado = gradoService.findById(id);

			if (grado.isPresent()) {
				model.addAttribute("grado", grado.get());
			} else {
				return "redirect:/grado";
			}
		} catch (Exception e) {

		}
		return "grado/info";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("grado") Grado grado, Model model, SessionStatus status, @Valid Grado grad,
			BindingResult result) {
		if (result.hasErrors()) {
			return "alumno/nuevo";
		}

		try {
			gradoService.save(grado);
			status.setComplete();

		} catch (Exception e) {
			// TODO: handle exception
			// return "/alumno/nuevo";
		}
		return "redirect:/grado";
	}

	@GetMapping("{id}/nuevaseccion")
	public String nuevaSeccion(@PathVariable("id") String id, Model model) {
		Seccion seccion = new Seccion();
		try {
			Optional<Grado> grado = gradoService.findById(id);
			if (grado.isPresent()) {
				seccion.setGrado(grado.get());
				model.addAttribute("seccion", seccion);
			} else {
				return "redirect:/grado";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "grado/nuevaseccion";
	}

	@PostMapping("saveseccion")
	public String saveSeccion(@ModelAttribute("seccion") Seccion seccion, Model model, SessionStatus status) {
		try {
			seccionService.save(seccion);
			status.setComplete();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/grado/info/" + seccion.getGrado().getCodigo();
	}

	@GetMapping("infoseccion/{id}")
	public String infoSeccion(@PathVariable("id") String id, Model model) {
		try {
			Optional<Seccion> seccion = seccionService.findById(id);

			if (seccion.isPresent()) {
				model.addAttribute("seccion", seccion.get());
			} else {
				return "redirect:/grado";
			}
		} catch (Exception e) {

		}
		return "grado/infoseccion";
	}

	@GetMapping("delseccion/{id}")
	public String eliminarSeccion(@PathVariable("id") String id, Model model) {
		try {
			Optional<Seccion> secciones = seccionService.findById(id);
			if (secciones.isPresent()) {
				seccionService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violacion contra el principio de Integridad Referencial");
			try {
				List<Seccion> secciones = seccionService.findAll();
				model.addAttribute("secciones", secciones);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "grado/info";
		}

		return "redirect:/grado";
	}

	@GetMapping("editseccion/{id}")
	public String editarSeccion(@PathVariable("id") String id, Model model) {
		try {
			Optional<Seccion> optional = seccionService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("seccion", optional.get());
			} else {
				return "redirect:/grado";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "grado/editseccion";
	}
}
