package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String idUsuario) {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(Integer.parseInt(idUsuario));
            return ResponseEntity.status(200).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) throws Exception{
        try {
            Usuario usuario1 = usuarioServicio.registrarUsuario(usuario);
            return ResponseEntity.status(201).body(new Mensaje("Se ha creado el usuario satisfactoriamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Usuario usuario) throws Exception{
        try {
            Usuario usuario1 =  usuarioServicio.actualizarUsuario(usuario);
            return ResponseEntity.status(200).body(new Mensaje("Se ha actualizado el usuario de forma exitosa"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") String id) throws Exception{
        try {
            usuarioServicio.eliminarUsuario(Integer.parseInt(id));
            return ResponseEntity.status(200).body(new Mensaje("Se ha eliminado el usuario sin inconvenientes"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
}
