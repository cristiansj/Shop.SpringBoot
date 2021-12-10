package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    List<Usuario> findAllByNombreContains(String nombre);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmailAndPassword(String email, String password);

    Page<Usuario> findAll(Pageable paginador);

    @Query("select p from Usuario u, IN (u.productosFavoritos) p where u.email = :email")
    List<Producto> obtenerProductosFavoritos(String email);

    @Query("select c from Usuario u, IN (u.compras) c where u.codigo = :codigo")
    List<Compra> obtenerProductosComprados(Integer codigo);
}
