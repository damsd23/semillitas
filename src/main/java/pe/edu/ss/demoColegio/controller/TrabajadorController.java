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

import pe.edu.ss.demoColegio.model.entity.Cargo;
import pe.edu.ss.demoColegio.model.entity.Trabajador;
import pe.edu.ss.demoColegio.model.entity.Usuario;
import pe.edu.ss.demoColegio.service.CargoService;
import pe.edu.ss.demoColegio.service.TrabajadorService;
import pe.edu.ss.demoColegio.service.UsuarioService;

@Controller
@RequestMapping("/trabajador")
@SessionAttributes({"trabajador","usuario"})
public class TrabajadorController {
	@Autowired
	private TrabajadorService trabajadorService;

	@Autowired
	private CargoService cargoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@InitBinder("trabajador")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		binder.registerCustomEditor(Date.class, "fechaNac", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "fechaIngreso", new CustomDateEditor(dateFormatter, true));
	}
	
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Trabajador> trabajadores= trabajadorService.findAll();
			model.addAttribute("trabajadores", trabajadores);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "trabajador/inicio";
	}

	@GetMapping("edit/{id}")
	public String editar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Trabajador> optional = trabajadorService.findById(id);
			if (optional.isPresent()) {
				List<Cargo> cargos= cargoService.findAll();
				model.addAttribute("trabajador", optional.get());
				model.addAttribute("cargos", cargos);
			} else {
				return "redirect:/trabajador";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "trabajador/edit";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("trabajador") Trabajador trabajador, Model model, SessionStatus status,
			@Valid Trabajador trab, BindingResult result) {
		if(result.hasErrors())
			return "trabajador/nuevo";
		try {
			trabajadorService.save(trabajador);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/trabajador";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
		Trabajador trabajador= new Trabajador();
		model.addAttribute("trabajador", trabajador);
		try {
			List<Cargo> cargos = cargoService.findAll();
			model.addAttribute("cargos", cargos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "trabajador/nuevo";
	}

	@GetMapping("del/{id}")
	public String eliminar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Trabajador> trabajador = trabajadorService.findById(id);
			if (trabajador.isPresent()) {
				trabajadorService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR");
			try {
				List<Trabajador> trabajadores = trabajadorService.findAll();
				model.addAttribute("trabajadores", trabajadores);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "trabajador/inicio";
		}
		return "redirect:/trabajador";
	}

	@GetMapping("info/{id}")
	public String info(@PathVariable("id") String id, Model model) {
		try {
			Optional<Trabajador> trabajador = trabajadorService.findById(id);
			if (trabajador.isPresent()) {
				model.addAttribute("trabajador", trabajador.get());
			} else {
				return "redirect:/trabajador";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "trabajador/info";
	}
	
	@GetMapping("{id}/nuevousuario")
	public String nuevoUsuario(@PathVariable("id") String id, Model model) {
		Usuario usuario= new Usuario();
		try {
			Optional<Trabajador> trabajador= trabajadorService.findById(id);
			if (trabajador.isPresent()) {
				usuario.setTrabajador(trabajador.get());
				model.addAttribute("usuario", usuario);
			} else {
				return "redirect:/trabajador";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "trabajador/nuevousuario";
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
				return "trabajador";
			} else {
				usuario.setPassword(passwordEncoder
						.encode( usuario.getPassword() ));
				usuario.addAuthority("ROLE_TRABAJADOR");
				usuarioService.save(usuario);
				status.setComplete();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/trabajador";
	}
}
