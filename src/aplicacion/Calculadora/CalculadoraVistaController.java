package aplicacion.Calculadora;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculadoraVistaController implements Initializable {

    @FXML private Label labelResultados ;
    @FXML private GridPane gridPane1 ;
    @FXML private Button botonAtras ;

    private Stage escenario ;
    private int noEcuaciones ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void calcular(ActionEvent e){
        ObservableList<Node> hijos = gridPane1.getChildren() ;
        TextField textField ;
        double[][] arreglo = new double[noEcuaciones][noEcuaciones+1] ;
        for(Node hijo:hijos){
            textField = (TextField)hijo ;
            String texto =  textField.getText() ;
            Double numero = 0.0 ;
            if(!texto.isEmpty()) {
                try {
                   numero = Double.parseDouble(texto);
                } catch(Exception exception) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Ingrese sólo números, por favor") ;
                    alert.show() ;
                    return ;
                }
                arreglo[GridPane.getRowIndex(textField)][GridPane.getColumnIndex(textField)] = numero ;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Usted no ha completado los espacios") ;
                alert.show();
                return;
            }
        }

        Matriz m = new Matriz(arreglo) ;
        String result = m.gaussJordan() ;
        labelResultados.setText(result);
    }


    @FXML
    private void borrar(ActionEvent e){
      ObservableList<Node> hijos = gridPane1.getChildren() ;
      TextField textField ;
      for(Node hijo : hijos) {
          textField = (TextField)hijo ;
          textField.setText("");
      }
      labelResultados.setText(null);
    }


    public void setEscenario(Stage escenario){
        this.escenario =  escenario ;
    }


    public void modificarVista(int noEcuaciones){
        this.noEcuaciones = noEcuaciones ;
        TextField textF = null ;
        for(int i=0 ; i < noEcuaciones+1 ; i++){
            for(int j = 0 ; j < noEcuaciones ; j++) {
                textF = new TextField();
                textF.setId("boton"+i+j);
                gridPane1.add(textF, i, j);
            }
        }
    }

    @FXML private void cargarVistaInicio(ActionEvent event){
        Stage primaryStage = new Stage() ;
        VBox root = null ;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioVista.fxml")) ;
       try{
           root = (VBox)loader.load();
       } catch (IOException exception){
           Alert alert = new Alert(Alert.AlertType.ERROR, "Error al cargar la vista de inicio") ;
           alert.show();
       }
        primaryStage.setTitle("Calculadora de sistemas de ecuaciones");
        primaryStage.setScene(new Scene(root, 600, 400));
        InicioVistaController InicioController = loader.getController() ;
        InicioController.setEscenario(primaryStage);
        primaryStage.show();
        escenario.close() ;
    }

}
