package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Colaboracion implements Serializable {

    @Id
    private Integer codigo;

    @OneToMany(mappedBy = "colaboracion")
    @ToString.Exclude
    private List<Producto> producto;

}
