package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.ss.demoColegio.model.entity.Seccion;
import pe.edu.ss.demoColegio.model.repository.SeccionRepository;
import pe.edu.ss.demoColegio.service.SeccionService;

@Service
public class SeccionServiceimpl implements SeccionService{

	@Autowired
	private SeccionRepository seccionRepository;
	
	@Override
	public List<Seccion> findAll() throws Exception {
		return seccionRepository.findAll();
	}

	@Override
	public Optional<Seccion> findById(String id) throws Exception {
		return seccionRepository.findById(id);
	}

	@Override
	public Seccion save(Seccion entity) throws Exception {
		return seccionRepository.save(entity);
	}

	@Override
	public Seccion update(Seccion entity) throws Exception {
		return seccionRepository.save(entity);
	}

	@Override
	public void deleteById(String id) throws Exception {
		seccionRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		seccionRepository.deleteAll();
	}

}
