package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    @Query("select avg(com.calificacion), cat.nombre from Comentario com join com.codigoProducto.categorias cat group by cat order by avg(com.calificacion) desc")
    List<Object[]> listarCalificacionPromedioPorCategoría();

}
