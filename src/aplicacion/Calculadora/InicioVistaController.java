package aplicacion.Calculadora ;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioVistaController implements Initializable {

    @FXML
    private TextField textFieldNoEcuaciones ;
    @FXML
    private Button botonContinuar ;

    private Stage escenario ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEscenario(Stage escenario){
        this.escenario =  escenario ;
    }

    public void mostrarVistaCalculadora(int noEcuaciones) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculadoraVista.fxml")) ;
        VBox root = (VBox) loader.load() ;
        CalculadoraVistaController controladorCalculadora = loader.getController();
        controladorCalculadora.modificarVista(noEcuaciones) ;
        Scene escena = new Scene(root, 600, 400) ;
        Stage escenario2 = new Stage() ;
        escenario2.setScene(escena);
        //escenario2.initOwner(escenario);
        escenario2.initModality(Modality.WINDOW_MODAL);
        escenario2.show() ;
        controladorCalculadora.setEscenario(escenario2);
        escenario.close() ;
    }

    @FXML private void continuar(ActionEvent e) throws IOException {
        String noEcuaciones = textFieldNoEcuaciones.getText() ;
        if(!noEcuaciones.isEmpty()) {
            int noEcuaciones2 = Integer.parseInt(noEcuaciones) ;
            if(noEcuaciones2 >= 2 )
                mostrarVistaCalculadora(noEcuaciones2);
        }
    }


}


