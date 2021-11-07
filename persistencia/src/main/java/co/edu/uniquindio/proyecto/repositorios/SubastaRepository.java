package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubastaRepository extends JpaRepository<Subasta, Integer> {

    @Query("select s.producto.nombre, count(s.producto) from Subasta s join s.producto.categorias c group by c")
    List<Object[]> obtenerTotalProductosSubastadosPorCategoría();

    @Query("select s from Subasta s join s.producto.categorias c where s.fechaLimite > current_timestamp and c.codigo = :idCategoria")
    List<Subasta> obtenerSubastasVigentesPorCategoría(Integer idCategoria);
}
