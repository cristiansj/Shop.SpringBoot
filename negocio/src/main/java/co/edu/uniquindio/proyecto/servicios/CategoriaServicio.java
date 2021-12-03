package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CategoriaServicio {

    Categoria obtenerPorID(Integer codigo) throws Exception;

    List<Categoria> listarCategoria();
}
