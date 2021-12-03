package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioImplementation implements CategoriaServicio{

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria obtenerPorID(Integer codigo) throws Exception {
        return categoriaRepository.findById(codigo).orElseThrow( () -> new Exception("No se ha encontrado la categoria"));
    }

    @Override
    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }
}
