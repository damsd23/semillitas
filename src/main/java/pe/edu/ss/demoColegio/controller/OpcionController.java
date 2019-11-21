package pe.edu.ss.demoColegio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("opcion")
public class OpcionController {
	
	@GetMapping("mision")
	public String Mision() {
		return "opcion/mision";
	}
	
	@GetMapping("vision")
	public String Vision() {
		return "opcion/vision";
	}
	
	@GetMapping("eventos")
	public String Eventos() {
		return "opcion/eventos";
	}
	
	@GetMapping("sedes")
	public String Sedes() {
		return "opcion/sedes";
	}
	
	@GetMapping("calendario")
	public String Calendario() {
		return "opcion/calendario";
	}
	
	@GetMapping("contacto")
	public String Contacto() {
		return "opcion/contacto";
	}
}
