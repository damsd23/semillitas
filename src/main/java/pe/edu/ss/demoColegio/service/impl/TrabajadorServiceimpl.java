package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ss.demoColegio.model.entity.Trabajador;
import pe.edu.ss.demoColegio.model.repository.TrabajadorRepository;
import pe.edu.ss.demoColegio.service.TrabajadorService;

@Service
public class TrabajadorServiceimpl implements TrabajadorService{

	@Autowired
	private TrabajadorRepository trabajadorRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Trabajador> findAll() throws Exception {
		return trabajadorRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Trabajador> findById(String id) throws Exception {
		return trabajadorRepository.findById(id);
	}

	@Transactional
	@Override
	public Trabajador save(Trabajador entity) throws Exception {
		return trabajadorRepository.save(entity);
	}

	@Transactional
	@Override
	public Trabajador update(Trabajador entity) throws Exception {
		return trabajadorRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		trabajadorRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		trabajadorRepository.deleteAll(); 
	}

	
}
