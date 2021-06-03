package aplicacion.Calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * clase principal para arrancar la aplicación
 */
public class Main extends Application {

    /**
     * Metodo de inicio de la aplicaición
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioVista.fxml")) ;
        VBox root = null;
        try{
            root = (VBox)loader.load();
        } catch(Exception e){
            System.out.println("Error al cargar la vista inicial") ;
            return ;
        }

        primaryStage.setTitle("Calculadora de sistemas de ecuaciones");
        primaryStage.setScene(new Scene(root, 600, 400));
        InicioVistaController InicioController = loader.getController() ;
        InicioController.setEscenario(primaryStage);
        primaryStage.show();
    }

    /**
     * metodo main
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
