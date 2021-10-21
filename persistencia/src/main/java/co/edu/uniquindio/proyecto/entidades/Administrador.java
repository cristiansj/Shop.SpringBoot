package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Administrador extends Persona implements Serializable {
    public Administrador(String nombre, String email, String password) {
        super(nombre, email, password);
    }
}
