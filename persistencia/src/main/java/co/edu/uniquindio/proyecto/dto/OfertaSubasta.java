package co.edu.uniquindio.proyecto.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class OfertaSubasta {
    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre, imagen;
    private Double ofertaActual;
    private LocalDateTime tiempo;
}
