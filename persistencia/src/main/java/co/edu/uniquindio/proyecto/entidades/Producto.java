package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer disponibilidad;

    @Column(length = 250)
    private String descripcion;

    @Column(nullable = false)
    @Positive
    private Double precio;

    @Future
    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @PositiveOrZero
    private Float descuento;

    @ManyToOne
    private Usuario codigoVendedor;

    @ManyToOne
    private Ciudad codigoCiudad;

    @ManyToMany
    @ToString.Exclude
    private List<Categoria> categorias = new ArrayList<Categoria>();

    @ManyToOne
    private Colaboracion colaboracion;

    @ElementCollection
    @ToString.Exclude
    private List<String> imagenes = new ArrayList<String>();

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    private List<Chat> chats;

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    public String getImagenPrincipal() {
        if (imagenes != null && !imagenes.isEmpty()) {
            return imagenes.get(0);
        }
        return "producto.png";
    }

    public Producto(Integer codigo, String nombre, Integer disponibilidad, String descripcion, List<String> imagenes, Double precio, LocalDateTime fechaLimite, Usuario usuario, Ciudad ciudad, ArrayList<Categoria> categorias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.codigoCiudad = ciudad;
        this.codigoVendedor = usuario;
        this.categorias = categorias;
        this.imagenes = imagenes;
    }
}
