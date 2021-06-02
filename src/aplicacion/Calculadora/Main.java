package aplicacion.Calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioVista.fxml")) ;
        VBox root = (VBox)loader.load();
        primaryStage.setTitle("Calculadora de sistemas de ecuaciones");
        primaryStage.setScene(new Scene(root, 600, 400));
        InicioVistaController InicioController = loader.getController() ;
        InicioController.setEscenario(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
