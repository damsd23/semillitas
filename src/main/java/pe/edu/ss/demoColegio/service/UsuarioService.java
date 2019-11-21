package pe.edu.ss.demoColegio.service;

import java.util.Optional;

import pe.edu.ss.demoColegio.model.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long> {
	Optional<Usuario> findByUsername(String username) throws Exception;
}
