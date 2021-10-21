package co.edu.uniquindio.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}
