package co.edu.uniquindio.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubastaUsuarioRepository extends JpaRepository<SubastaUsuario, Integer> {
}
