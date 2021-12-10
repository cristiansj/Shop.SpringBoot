package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;

import java.util.List;

public interface SubastaServicio {

    List<Subasta> listarSubastas();

    void ofertar(SubastaUsuario subasta, Producto producto);
}
