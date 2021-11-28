package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Getter
    @Setter
    private String atributo1, atributo2;
    public void cambiar() {
        String aux = atributo1;
        atributo1 = atributo2;
        atributo2 = aux;
    }
}
