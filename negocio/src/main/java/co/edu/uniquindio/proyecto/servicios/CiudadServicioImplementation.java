package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepository;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServicioImplementation implements CiudadServicio{

    @Autowired
    CiudadRepository ciudadRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Ciudad registrarCiudad(Ciudad city) throws Exception {
        int i;
        return ciudadRepository.save(city);
    }

    @Override
    public Ciudad obtenerPorID(Integer codigo) throws Exception {
        return ciudadRepository.findById(codigo).orElseThrow( () -> new Exception("El id no pertenece a ninguna ciudad"));
    }

    @Override
    public List<Ciudad> listarCiudad() {
        return ciudadRepository.findAll();
    }

    @Override
    public Integer numeroDeUsuariosPorCiudad(Ciudad ciudad) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        int contador = 0;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCiudad() != null && usuarios.get(i).getCiudad().equals(ciudad)) {
                contador++;
            }
        }
        return contador;
    }
}
