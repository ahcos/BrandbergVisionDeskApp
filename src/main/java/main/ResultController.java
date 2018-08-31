package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class ResultController {

    @FXML
    private Label results_label;
    @FXML
    private Button close_results;


    public ResultController () {


    }

    /**
     * give the Results to the Label field before the Window is loaded
     */

    public void initialize () {
        try {
            results_label.textProperty().setValue(new String (readAllBytes(Paths.get("src/main/resources/results"))));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void closeResults (ActionEvent ae) {
        Node source = (Node) ae.getSource();

        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();

    }
}
