package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @Positive
    private Integer disponibilidad;

    @Column(length = 250)
    private String descripcion;

    @Column(nullable = false)
    @Positive
    private Float precio;

    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @Positive
    private Float descuento;

    @ManyToOne
    private Usuario codigoVendedor;

    @ManyToOne
    private Ciudad codigoCiudad;

    @ManyToMany(mappedBy = "productos")
    @ToString.Exclude
    private List<Categoria> categorias;

    @ManyToOne
    private Colaboracion colaboracion;

    @ElementCollection
    private Map<String,String> imagenes;

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

    public Producto(Integer codigo,String nombre, Integer disponibilidad, String descripcion, Float precio, LocalDateTime fechaLimite, List<Categoria> categorias, Map<String, String> imagenes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.categorias = categorias;
        this.imagenes = imagenes;
    }
}
