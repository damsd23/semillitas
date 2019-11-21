package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ss.demoColegio.model.entity.ComprobantePago;
import pe.edu.ss.demoColegio.model.repository.ComprobantePagoRepository;
import pe.edu.ss.demoColegio.service.ComprobantePagoService;

@Service
public class ComprobantePagoServiceimpl implements ComprobantePagoService{

	@Autowired
	private ComprobantePagoRepository comprobantePagoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<ComprobantePago> findAll() throws Exception {
		return comprobantePagoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<ComprobantePago> findById(Integer id) throws Exception {
		return comprobantePagoRepository.findById(id);
	}

	@Transactional
	@Override
	public ComprobantePago save(ComprobantePago entity) throws Exception {
		return comprobantePagoRepository.save(entity);
	}

	@Transactional
	@Override
	public ComprobantePago update(ComprobantePago entity) throws Exception {
		return comprobantePagoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		comprobantePagoRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		comprobantePagoRepository.deleteAll();
	}

}
