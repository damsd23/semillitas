package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ss.demoColegio.model.entity.Apoderado;
import pe.edu.ss.demoColegio.model.repository.ApoderadoRepository;
import pe.edu.ss.demoColegio.service.ApoderadoService;

@Service
public class ApoderadoServiceimpl implements ApoderadoService {

	@Autowired
	private ApoderadoRepository apoderadoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Apoderado> findAll() throws Exception {
		return apoderadoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Apoderado> findById(String id) throws Exception {
		return apoderadoRepository.findById(id);
	}

	@Transactional
	@Override
	public Apoderado save(Apoderado entity) throws Exception {
		return apoderadoRepository.save(entity);
	}

	@Transactional
	@Override
	public Apoderado update(Apoderado entity) throws Exception {
		return apoderadoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		apoderadoRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		apoderadoRepository.deleteAll();
	}

}
