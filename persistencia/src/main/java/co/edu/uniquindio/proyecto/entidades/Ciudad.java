package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 100,nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "codigoCiudad")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productos;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> usuarios;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
