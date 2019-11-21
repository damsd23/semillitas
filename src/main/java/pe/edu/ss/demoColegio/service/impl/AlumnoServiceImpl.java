package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.model.repository.AlumnoRepository;
import pe.edu.ss.demoColegio.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Alumno> findAll() throws Exception {
		
		return alumnoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Alumno> findById(String id) throws Exception {
		
		return alumnoRepository.findById(id);
	}

	@Transactional
	@Override
	public Alumno save(Alumno entity) throws Exception {
		
		return alumnoRepository.save(entity);
	}

	@Transactional
	@Override
	public Alumno update(Alumno entity) throws Exception {
		
		return alumnoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		
		alumnoRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		
		alumnoRepository.deleteAll();
	
	}


}
