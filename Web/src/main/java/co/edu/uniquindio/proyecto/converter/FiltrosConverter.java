package co.edu.uniquindio.proyecto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

public class FiltrosConverter implements Converter<String>, Serializable {
    @Override
    public String getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return s;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, String s) {
        return s;
    }
}
