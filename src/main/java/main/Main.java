package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    // Entry point for the program

    @Override
    public void start(Stage primaryStage) throws Exception{

        // load GUi as specified by the .fxml file with the FXMLLoader

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppGui.fxml"));

        Parent root = loader.load();

        // set HostService, so URL can be opened by clicking on it
        Controller ctl = loader.getController();
        ctl.setHostServices(getHostServices());


        primaryStage.setTitle("BrandbergVisionDeskApp");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }


}
