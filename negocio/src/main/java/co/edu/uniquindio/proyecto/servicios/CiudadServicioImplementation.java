package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServicioImplementation implements CiudadServicio{

    @Autowired
    CiudadRepository ciudadRepository;

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
}
