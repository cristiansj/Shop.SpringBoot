package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Filtro {

    private Categoria categoría;
    private Double precioMin;
    private Double precioMax;
    private Ciudad ciudad;
}
