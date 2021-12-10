package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicioImplementacion implements CompraServicio{

    @Autowired
    private CompraRepository compraRepository;


    @Override
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    @Override
    public Integer contarComprasPorCiudad(Ciudad ciudad) {
        List<Compra> compras = compraRepository.findAll();
        int contador = 0;
        for (int i = 0; i < compras.size(); i++) {
            for (int j = 0; j < compras.get(i).getDetallesCompras().size(); j++) {
                if (compras.get(i).getDetallesCompras().get(j).getCodigoProducto().getCodigoCiudad().equals(ciudad)) {
                    contador++;
                }
            }
        }
        return contador;
    }
}
