package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.IntegerSyntax;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImplementation implements UsuarioServicio{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServicioImplementation(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario registrarUsuario(Usuario user) throws Exception {

        Optional<Usuario> buscado = buscarPorCodigo(user.getCodigo());
        if (buscado.isPresent()){
            throw new Exception("La cédula ya se encuentra registrada");
        }

        buscado = buscarPorEmail(user.getEmail());
        if (buscado.isPresent()){
            throw new Exception("El email ingresado ya se encuentra registrado");
        }

        buscado = usuarioRepository.findByUsername(user.getUsername());
        if (buscado.isPresent()){
            throw new Exception("El nombre de usuario ingresado ya se encuentra en uso");
        }
        return usuarioRepository.save(user);
    }

    @Override
    public Usuario actualizarUsuario(Usuario user) throws Exception {

        Optional<Usuario> buscado = buscarPorCodigo(user.getCodigo());
        if (buscado.isEmpty()){
            throw new Exception("El usuario no existe");
        }

        buscado = buscarPorEmail(user.getEmail());
        if (buscado.isPresent() && !buscado.get().getCodigo().equals(user.getCodigo())){
            throw new Exception("El email ingresado ya se encuentra registrado");
        }

        buscado = usuarioRepository.findByUsername(user.getUsername());
        if (buscado.isPresent()  && !buscado.get().getCodigo().equals(user.getCodigo())){
            throw new Exception("El nombre de usuario ingresado ya se encuentra en uso");
        }

        return usuarioRepository.save(user);
    }

    @Override
    public void eliminarUsuario(Integer codigo) throws Exception {

        Optional<Usuario> buscado = buscarPorCodigo(codigo);
        if (buscado.isEmpty()){
            throw new Exception("La cédula ingresada no se encuentra en la base de datos");
        }

        usuarioRepository.deleteById(codigo);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Producto> listarProductosFavoritos(String email) throws Exception {

        Optional<Usuario> buscado = buscarPorEmail(email);
        if (buscado.isEmpty()){
            throw new Exception("El email ingresado no se encuentra en la base de datos");
        }

        return usuarioRepository.obtenerProductosFavoritos(email);
    }

    @Override
    public Usuario obtenerUsuario(Integer codigo) throws Exception {

        Optional<Usuario> buscado = buscarPorCodigo(codigo);
        if(buscado.isEmpty()){
            throw new Exception("No existe ningún usuario registrado con ese código");
        }

        return buscado.get();
    }

    @Override
    public Usuario hacerLogin(String email, String password) throws Exception {

        Optional<Usuario> buscado = buscarPorEmail(email);
        if (buscado.isEmpty()){
            throw new Exception("El email ingresado no está asociado a niguna cuenta");
        }
        buscado = usuarioRepository.findByEmailAndPassword(email, password);
        if(buscado.isEmpty()){
            throw new Exception("La contraseña es incorrecta");
        }

        return buscado.get();
    }

    @Override
    public List<Producto> listarMisProductos(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        if (usuario.getProductos() != null && !usuario.getProductos().isEmpty()) {
            return usuario.getProductos();
        }
        return null;
    }

    Optional<Usuario> buscarPorCodigo(Integer codigo){
        return usuarioRepository.findById(codigo);
    }

    Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }


}
