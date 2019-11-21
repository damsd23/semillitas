package pe.edu.ss.demoColegio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.ss.demoColegio.model.entity.Grado;

@Repository
public interface GradoRepository extends JpaRepository<Grado, String> {

}
