package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonIgnore
    private List<Categoria> categorias = new ArrayList<Categoria>();

    @ManyToOne
    private Colaboracion colaboracion;

    @ElementCollection
    @ToString.Exclude
    private List<String> imagenes = new ArrayList<String>();

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chats;

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    @JsonIgnore
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "codigoProducto")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> detalleCompras;

    public String getImagenPrincipal() {
        if (imagenes != null && !imagenes.isEmpty()) {
            return imagenes.get(0);
        }
        return "producto.png";
    }

    public Producto(String nombre, Integer disponibilidad, String descripcion, List<String> imagenes, Double precio, LocalDateTime fechaLimite, Usuario usuario, Ciudad ciudad, ArrayList<Categoria> categorias) {
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

    public String getCategoriasEstilo(){
        String concatenador = "";
        for (int i = 0; i < categorias.size() && !categorias.isEmpty() && categorias != null; i++) {
            concatenador = concatenador + categorias.get(i).getNombre();
            if (i != categorias.size()-1) {
                concatenador = concatenador + ", ";
            }
        }
        return concatenador;
    }
}
