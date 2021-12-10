package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

import java.util.List;

public interface AdministradorServicio {

    List<Administrador> listarAdmins();

    Administrador hacerLogin(String email, String password) throws Exception;
}
