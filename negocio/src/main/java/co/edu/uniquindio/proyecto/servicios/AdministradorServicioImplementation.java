package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServicioImplementation implements AdministradorServicio{

    @Autowired
    AdministradorRepository administradorRepository;

    @Override
    public List<Administrador> listarAdmins() {
        return administradorRepository.findAll();
    }

    @Override
    public Administrador hacerLogin(String email, String password) {
        return administradorRepository.findByEmailAndPassword(email, password);
    }
}
