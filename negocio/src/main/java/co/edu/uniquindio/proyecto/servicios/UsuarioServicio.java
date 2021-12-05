package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario user) throws Exception;

    Usuario actualizarUsuario(Usuario user) throws Exception;

    void eliminarUsuario(Integer codigo) throws Exception;

    List<Usuario> listarUsuarios();

    List<Producto> listarProductosFavoritos(String email) throws Exception;

    Usuario obtenerUsuario(Integer codigo)throws Exception;

    Usuario hacerLogin(String email, String password)throws Exception;

    List<Producto> listarMisProductos(Integer idUsuario);
}
