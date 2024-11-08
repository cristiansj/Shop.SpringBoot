package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Persona implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false,length = 50)
    @Length(max = 50)
    private String nombre;

    @Column(unique = true,length = 150)
    @Length(max = 150)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String,String> numTelefonos = new HashMap<>();

    public Persona(String nombre, String email, String password, HashMap<String,String> numTelefonos) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTelefonos = numTelefonos;
    }
}
