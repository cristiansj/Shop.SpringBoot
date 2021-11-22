package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p where :categoria member p.categorias")
    List<Producto> listarProductosPorCategoria(Categoria categoria);

    @Query("select  p from Producto p where p.codigoVendedor.codigo = :codigoUsuario")
    List<Producto> listarProductosDeUsuario(Integer codigoUsuario);

    @Query("select avg(c.calificacion) from Comentario c where c.codigoProducto = :producto")
    Integer sacarCalificaciónPromedio(Producto producto);
}
