package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false,unique = true,length = 120)
    private String email;

    @ElementCollection
    private Map<String,String> numTelefonos;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    private List<Prestamo> prestamos;

    public Usuario(String codigo, String nombre, LocalDate fechaNacimiento, EnumGenero genero, String email, Map<String, String> numTelefonos, Ciudad ciudad) {
        super(codigo, nombre, fechaNacimiento, genero);
        this.email = email;
        this.numTelefonos = numTelefonos;
        this.ciudad = ciudad;
    }
}

