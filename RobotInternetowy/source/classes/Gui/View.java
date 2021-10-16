package classes.Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class View extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Controler controler = new Controler();
        FXMLLoader loader = new FXMLLoader();
        
        loader.setController(controler);
        URL url = this.getClass().getResource("Gui.fxml");
        loader.setLocation(url);
        VBox vbox = loader.<VBox> load();

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void f()
    {
        launch();
    }

    void setListener(Scene s)
    {

    }
}