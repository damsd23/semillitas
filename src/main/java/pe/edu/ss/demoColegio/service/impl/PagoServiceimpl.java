package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ss.demoColegio.model.entity.Pago;
import pe.edu.ss.demoColegio.model.repository.PagoRepository;
import pe.edu.ss.demoColegio.service.PagoService;

@Service
public class PagoServiceimpl implements PagoService{

	@Autowired
	private PagoRepository pagoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Pago> findAll() throws Exception {
		return pagoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Pago> findById(String id) throws Exception {
		return pagoRepository.findById(id);
	}

	@Transactional
	@Override
	public Pago save(Pago entity) throws Exception {
		return pagoRepository.save(entity);
	}

	@Transactional
	@Override
	public Pago update(Pago entity) throws Exception {
		return pagoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		pagoRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		pagoRepository.deleteAll();
	}

}
