package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepository;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubastaServicioImplementation implements SubastaServicio{

    final SubastaRepository subastaRepository;

    final ProductoRepository productoRepository;

    public SubastaServicioImplementation(SubastaRepository subastaRepository, ProductoRepository productoRepository) {
        this.subastaRepository = subastaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Subasta> listarSubastas() {
        return subastaRepository.findAll();
    }

    @Override
    public void ofertar(SubastaUsuario subasta, Producto producto) {
    }
}
