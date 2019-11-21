package pe.edu.ss.demoColegio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ss.demoColegio.model.entity.Cargo;
import pe.edu.ss.demoColegio.model.repository.CargoRepository;
import pe.edu.ss.demoColegio.service.CargoService;

@Service
public class CargoServiceimpl implements CargoService{

	@Autowired
	private CargoRepository cargoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Cargo> findAll() throws Exception {
		return cargoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Cargo> findById(String id) throws Exception {
		return cargoRepository.findById(id);
	}

	@Transactional
	@Override
	public Cargo save(Cargo entity) throws Exception {
		return cargoRepository.save(entity);
	}

	@Transactional
	@Override
	public Cargo update(Cargo entity) throws Exception {
		return cargoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		cargoRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		cargoRepository.deleteAll();
	}

}
