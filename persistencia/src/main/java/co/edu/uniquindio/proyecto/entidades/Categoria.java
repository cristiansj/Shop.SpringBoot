package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Categoria implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false,length = 50)
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productos;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

}
