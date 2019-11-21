package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ss.demoColegio.model.entity.Matricula;
import pe.edu.ss.demoColegio.model.repository.MatriculaRepository;
import pe.edu.ss.demoColegio.service.MatriculaService;

@Service
public class MatriculaServiceimpl implements MatriculaService{

	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Matricula> findAll() throws Exception {
		return matriculaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Matricula> findById(Integer id) throws Exception {
		return matriculaRepository.findById(id);
	}

	@Transactional
	@Override
	public Matricula save(Matricula entity) throws Exception {
		return matriculaRepository.save(entity);
	}

	@Transactional
	@Override
	public Matricula update(Matricula entity) throws Exception {
		return matriculaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		matriculaRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		matriculaRepository.deleteAll();
	}

}
