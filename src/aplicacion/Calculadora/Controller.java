package aplicacion.Calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TextArea textArea1 ;
    @FXML private Label labelResultados ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void calcular(ActionEvent e){
        String texto =  textArea1.getText();
        if(!texto.isEmpty()){
            String[] cadenas  = texto.split("\n") ;

            double [][] arreglo = new double[cadenas.length][];

            for (int i = 0 ; i < cadenas.length ; i++){
                String [] fila = cadenas[i].split(" ");
                double [] filaD = new double[fila.length];
                for (int j = 0 ; j < fila.length ; j++){
                    filaD[j] = Double.parseDouble(fila[j]);
                }
                arreglo [i] = filaD ;

            }
            Matriz m = new Matriz(arreglo) ;
            String result = m.gaussJordan() ;
            labelResultados.setText(result);

        }
    }
    @FXML
    private void borrar(ActionEvent e){
        textArea1.setText(null);
        labelResultados.setText(null);
    }

}
