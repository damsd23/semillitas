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
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.ss.demoColegio.model.entity.Cargo;
import pe.edu.ss.demoColegio.service.CargoService;

@Controller
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	private CargoService cargoService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Cargo> cargos= cargoService.findAll();
			model.addAttribute("cargos",cargos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "cargo/inicio";
	}
	
	@GetMapping("edit/{id}")
	public String editar (@PathVariable("id") String id, Model model) {
		try {
			Optional<Cargo> optional = cargoService.findById(id);
			if(optional.isPresent()) {
				
				model.addAttribute("cargo", optional.get());
			}else {
				return "redirect:/cargo";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "cargo/edit";
	}
	
	@PostMapping("save")
	public String save(@ModelAttribute("cargo") Cargo cargo,Model model, SessionStatus status,
			@Valid Cargo car, BindingResult result) {
		if(result.hasErrors()){
			return "cargo/nuevo";
		}
		try {
			cargoService.save(cargo);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/cargo";
	}
	
	@GetMapping("nuevo")
	public String nuevo(Model model) {
		Cargo cargo= new Cargo();
		model.addAttribute("cargo",cargo);
		return "cargo/nuevo";
	}
	@GetMapping("del/{id}")
	public String eliminar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Cargo> cargo= cargoService.findById(id);
			if(cargo.isPresent()) {
				cargoService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel","ERROR");
			try {
				List<Cargo> cargos= cargoService.findAll();
				model.addAttribute("cargos",cargos);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "cargo/inicio";
		}
		return "redirect:/cargo";
	}
	@GetMapping("info/{id}")
	public String info (@PathVariable("id") String id, Model model) {
		try {
			Optional<Cargo> cargo = cargoService.findById(id);
			if(cargo.isPresent()) {
				model.addAttribute("cargo",cargo.get());
			}else {
				return "redirect:/cargo";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "cargo/info";
	}
}
