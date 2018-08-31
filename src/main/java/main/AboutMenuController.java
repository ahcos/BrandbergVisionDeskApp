package main;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.*;

public class AboutMenuController {

    @FXML
    private Label about_label;
    @FXML
    private Button about_exit;
    @FXML
    private TextFlow about_link;

    private HostServices hostServices;

    public AboutMenuController() {

    }

    /**
     * Populates the new window with 1. Text 2. Hyperlink, the "OK" Button is added by the .fxml
     */
    public void initialize () {


        try {
            about_label.textProperty().setValue(new String (readAllBytes(Paths.get("src/main/resources/info"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // the Hyperlink to be displayed - could be loaded dynamically from file
        Hyperlink hl = new Hyperlink("http://www.lehre.jan-wieners.de/sosem18-kuenstliche-intelligenz-und-cultural-heritage-upper-brandberg-im-louvre-der-felsmalerei/");

        // sets the hyperlink element to open the link when clicked
        hl.setOnAction(ae ->  hostServices.showDocument(hl.getText()));


        Text tx = new Text ("More information regarding the seminar and the project see:\n");

        // adds Text and Hyperlink to the about_link element, which is specified by the .fxml
        about_link.getChildren().addAll(tx, hl);


    }

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    /**
     * Closes the AboutMenu window.
     * @param ae
     */
    public void closeAboutMenu (ActionEvent ae) {
        Node source = (Node) ae.getSource();

        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();

    }


}
