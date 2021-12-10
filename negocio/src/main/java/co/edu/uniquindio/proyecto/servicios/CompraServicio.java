package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Compra;

import java.util.List;

public interface CompraServicio {

    List<Compra> listarCompras();

    Integer contarComprasPorCiudad(Ciudad ciudad);
}
