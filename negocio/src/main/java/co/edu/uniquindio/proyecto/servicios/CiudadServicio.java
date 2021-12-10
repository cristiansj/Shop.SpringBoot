package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface CiudadServicio {

    Ciudad registrarCiudad(Ciudad city) throws Exception;

    Ciudad obtenerPorID(Integer codigo) throws Exception;

    List<Ciudad> listarCiudad();

    Integer numeroDeUsuariosPorCiudad(Ciudad ciudad);
}
