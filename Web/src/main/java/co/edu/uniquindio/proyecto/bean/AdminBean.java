package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.CiudadAdmin;
import co.edu.uniquindio.proyecto.dto.CiudadUsuarioAdmin;
import co.edu.uniquindio.proyecto.dto.DetalleCompraAdmin;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.donutchart.DonutChart;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AdminBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Getter @Setter
    private PieChartModel pieModel;

    @Getter @Setter
    private BarChartModel barChartModel;

    @Getter @Setter
    private boolean mostrar;

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<CiudadAdmin> ventasCiudades;

    @Getter @Setter
    private List<DetalleCompraAdmin> detalleCompras;

    @Getter @Setter
    private List<CiudadUsuarioAdmin> ciudadUsuarios;

    @Getter @Setter
    private List<Usuario> usuarios;

    @Getter @Setter
    private List<Compra> compras;

    @PostConstruct
    public void inicializar() {
        this.mostrar = false;
        this.productos = productoServicio.listarProductos();
        this.usuarios = usuarioServicio.listarUsuarios();
        this.compras = compraServicio.listarCompras();
        this.detalleCompras = llenarDetalleLista();
        this.ventasCiudades = llenarVentasCiudades();
        this.ciudadUsuarios = llenarUsuarioPorCiudad();
    }

    public List<DetalleCompraAdmin> llenarDetalleLista(){
        List<DetalleCompraAdmin> detalles = new ArrayList<>();
        for (int i = 0; i < compras.size(); i++) {
            for (int j = 0; j < compras.get(i).getDetallesCompras().size(); j++) {
                DetalleCompra detalleCompra = compras.get(i).getDetallesCompras().get(j);
                DetalleCompraAdmin detalleCompraAdmin = new DetalleCompraAdmin(detalleCompra.getCodigoProducto().getNombre(),
                                                                                compras.get(i).getCodigoUsuario().getNombre(),
                                                                                detalleCompra.getUnidades(),
                                                                                compras.get(i).getFechaCompra());
                detalles.add(detalleCompraAdmin);
            }
        }
        return detalles;
    }

    public List<CiudadAdmin> llenarVentasCiudades(){
        List<CiudadAdmin> ventas = new ArrayList<>();
        List<Ciudad> ciudades = ciudadServicio.listarCiudad();
        for (int i = 0; i < ciudades.size(); i++) {
            CiudadAdmin ciudadAdmin = new CiudadAdmin(ciudades.get(i).getNombre(),
                                                        compraServicio.contarComprasPorCiudad(ciudades.get(i)));
            ventas.add(ciudadAdmin);
        }
        return ventas;
    }

    public List<CiudadUsuarioAdmin> llenarUsuarioPorCiudad(){
        List<CiudadUsuarioAdmin> aux = new ArrayList<>();
        List<Ciudad> ciudades = ciudadServicio.listarCiudad();
        for (int i = 0; i < ciudades.size(); i++) {
            CiudadUsuarioAdmin ciudadUsuarioAdmin = new CiudadUsuarioAdmin(ciudades.get(i).getNombre(),
                                                                            ciudadServicio.numeroDeUsuariosPorCiudad(ciudades.get(i)));
            aux.add(ciudadUsuarioAdmin);
        }
        return aux;
    }

    public void listarProductos(){
        this.mostrar = true;
        graficarProductos(productos);
    }

    public void listarVentasCiudad(){
        this.mostrar = true;
        graficarVentasCiudad(ventasCiudades);
    }

    public void listarProductosFavoritos(){
        this.mostrar = true;
        graficarProductosFavoritos(productos);
    }

    public void listarUsuarioPorCiudad(){
        this.mostrar = true;
        graficarNumUsuarioPorCiudad(ciudadUsuarios);
    }

    private void graficarProductos(List<Producto> productos){
        pieModel = new PieChartModel();

        for (Producto p : productos){
            pieModel.set(p.getNombre(), p.getDisponibilidad());
        }

        pieModel.setTitle("Disponibilidad de productos");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(365);
    }

    private void graficarProductosFavoritos(List<Producto> productos){
        pieModel = new PieChartModel();

        for (Producto p : productos){
            pieModel.set(p.getNombre(), productoServicio.contarFavorito(p.getCodigo()));
        }

        pieModel.setTitle("Productos en favoritos");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(365);
    }

    private void graficarVentasCiudad(List<CiudadAdmin> ventas){
        barChartModel = new BarChartModel();

        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Ventas por ciudad");
        List<Number> values = new ArrayList<>();

        for (int i = 0; i < ventas.size(); i++) {
            values.add(ventas.get(i).getVentas());
        }
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for (int i = 0; i < ventas.size(); i++) {
            labels.add(ventas.get(i).getNombre());
        }
        data.setLabels(labels);
        barChartModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barChartModel.setOptions(options);
    }

    private void graficarNumUsuarioPorCiudad(List<CiudadUsuarioAdmin> ciudades){
        pieModel = new PieChartModel();

        for (CiudadUsuarioAdmin c : ciudades){
            pieModel.set(c.getCiudad(), c.getNumUsuarios());
        }

        pieModel.setTitle("Numero de usuarios por ciudad");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(365);
    }
}
