package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashMap;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Persona implements Serializable {
    public Administrador(Integer codigo, String nombre, String email, String password, HashMap<String, String> numTelefonos) {
        super(codigo, nombre, email, password, numTelefonos);
    }
}
