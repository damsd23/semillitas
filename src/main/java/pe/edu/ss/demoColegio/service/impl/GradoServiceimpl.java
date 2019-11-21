package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ss.demoColegio.model.entity.Grado;
import pe.edu.ss.demoColegio.model.repository.GradoRepository;
import pe.edu.ss.demoColegio.service.GradoService;

@Service
public class GradoServiceimpl implements GradoService{

	@Autowired
	private GradoRepository gradoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Grado> findAll() throws Exception {
		return gradoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Grado> findById(String id) throws Exception {
		return gradoRepository.findById(id);
	}

	@Transactional
	@Override
	public Grado save(Grado entity) throws Exception {
		return gradoRepository.save(entity);
	}

	@Transactional
	@Override
	public Grado update(Grado entity) throws Exception {
		return gradoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		gradoRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		gradoRepository.deleteAll();
	}

}
