package aplicacion.Calculadora ;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * controlador de la Vista de inicio
 */
public class InicioVistaController implements Initializable {

    /*
    Campo que recibirá el número de ecuaciones del sistema
     */
    @FXML
    private TextField textFieldNoEcuaciones ;
    /*
    Botón para lanzar la vista de la calculadora
     */
    @FXML
    private Button botonContinuar ;
    /*
    Escenario actual
     */
    private Stage escenario ;


    /**
     * Por ahora no hace nada
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Establece el escenario actual
     * @param escenario - el escenario actual, antes de lanzar la vista
     */
    public void setEscenario(Stage escenario){
        this.escenario =  escenario ;
    }

    /*
    Lanza la vista de la calculadora
     */
    private void mostrarVistaCalculadora(int noEcuaciones) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculadoraVista.fxml")) ;
        VBox root = (VBox) loader.load() ;
        CalculadoraVistaController controladorCalculadora = loader.getController();
        controladorCalculadora.modificarVista(noEcuaciones) ;
        Scene escena = new Scene(root) ;
        Stage escenario2 = new Stage() ;
        escenario2.setScene(escena);
        //escenario2.initOwner(escenario);
        escenario2.initModality(Modality.WINDOW_MODAL);
        escenario2.setTitle("Calculadora de sistemas de ecuaciones");
        escenario2.show() ;
        controladorCalculadora.setEscenario(escenario2);
        escenario.close() ;
    }

    /*
    verifica que la entrada para el tamaño del sistema de ecuaciones sea válida y lanza la vista de la calculadora
     */
    @FXML private void continuar(ActionEvent e) throws IOException {
        String noEcuaciones = textFieldNoEcuaciones.getText() ;
        int noEcuaciones2 = 0 ;
        try {
            noEcuaciones2 = Integer.parseInt(noEcuaciones);
        }catch(Exception exception){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ingrese un número, por favor") ;
            alert.show();
            return ;
        }
        if(!noEcuaciones.isEmpty() && (noEcuaciones2 <= 7 && noEcuaciones2 >=2) ) {
            if(noEcuaciones2 >= 2 )
                mostrarVistaCalculadora(noEcuaciones2);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ingrese un número válido, por favor") ;
            alert.show();
            return ;
        }
    }

}


