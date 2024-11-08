package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DetalleCompraAdmin {
    private String producto;
    private String comprador;
    private int unidades;
    private LocalDateTime fecha;
}
