package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CiudadConverter implements Converter<Ciudad>, Serializable {

    @Autowired
    private CiudadServicio ciudadServicio;

    @Override
    public Ciudad getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Ciudad ciudad = null;
        try {
            ciudad = ciudadServicio.obtenerPorID(Integer.parseInt(s));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ciudad;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Ciudad ciudad) {
        if (ciudad != null) {
            return ciudad.getCodigo().toString();
        }
        return "";
    }
}
