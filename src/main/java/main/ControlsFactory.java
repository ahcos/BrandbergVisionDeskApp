package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlsFactory {

    @FXML
    private Button select_single_image;
    @FXML
    private Button select_multi_image;
    @FXML
    private ImageView image_view;
    @FXML
    private Slider slider;
    @FXML
    private CheckBox checkb_person;
    @FXML
    private CheckBox checkb_person_bow;
    @FXML
    private CheckBox checkb_female;
    @FXML
    private CheckBox checkb_male;
    @FXML
    private CheckBox checkb_animal;
    @FXML
    private Label label_classifier;
    @FXML
    private Label label_treshold;
    @FXML
    private ProgressBar progress_bar;
    @FXML
    private MenuBar main_menubar;
    @FXML
    private Menu main_menubar_file;
    @FXML
    private MenuItem main_menubar_file_train;
    @FXML
    private MenuItem main_menubar_file_quit;
    @FXML
    private Menu main_menubar_options;
    @FXML
    private MenuItem main_menubar_options_prefs;
    @FXML
    private Menu main_menubar_about;
    @FXML
    private MenuItem main_menubar_about_about;

    private Desktop desktop = Desktop.getDesktop();

  /*  public void createControls (Stage primaryStage) {


        FileChooser fileChooserSingle = new FileChooser();
        fileChooserSingle.setTitle("Open Single Image File");
//        fileChooserSingle.showOpenDialog(primaryStage);

        FileChooser fileChooserMulti = new FileChooser();
        fileChooserMulti.setTitle("Open Multiple Image File");


        select_single_image.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooserSingle.showOpenDialog(primaryStage);
                        if (file != null) {
                            openFile(file);
                        }
                    }
                });

        select_multi_image.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        List<File> list =
                                fileChooserMulti.showOpenMultipleDialog(primaryStage);
                        if (list != null) {
                            for (File file : list) {
                                openFile(file);
                            }
                        }
                    }
                });

    }


    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    Main.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
*/

}
