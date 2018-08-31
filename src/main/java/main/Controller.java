package main;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/** Main Controller class. Most of the apps functionality is controlled here, including opening files, menus and making
 * settings.
 *
 */
public class Controller {

    /*
    Following is a list of all elements in the AppGui.fxml, that have a fx:id, so they might be accessed if some
    changes are being made to the app. At the current state, most of them are redundant.
     */

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
    private CheckBox checkb_multi_persons;
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
    @FXML
    private TextField threshold_field;

    private HostServices hostServices;

    // boolean values refering to the checkboxes
    private boolean personChecked = true;
    private boolean multiplePersonsChecked = false;
    private boolean animalChecked = false;
    private boolean personWithBowChecked = false;
    private float sliderValue;

    // default constructor as demanded by javaFX
    public Controller() {

    }

    /**
     * initialize()  is called by the FXMLLoader once the .fxml file is loaded.
     * Use it to load in dynamic data that is supposed to populate the window.
     */
    @FXML
    public void initialize() {

        threshold_field.setText(Math.round(slider.getValue()) + "%");

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue == null) {
                    threshold_field.setText("");
                    return;
                }
                threshold_field.setText(Math.round(newValue.intValue()) + "%");
            }
        });

    }


    // two functions to pass hostServices down to the class that uses it
    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }


    /* The following 4 methods simply set the field that relates to the Checkboxes to true or false on Action.
     * This is the most basic implementation.
     */

    /** When the corresponding CheckBox in the GUI is clicked, set the according field to false or true.
     *
     */
    public void setPersonChecked() {
        if (personChecked) personChecked = false;
        else personChecked = true;
    }

    /** When the corresponding CheckBox in the GUI is clicked, set the according field to false or true.
     *
     */
    public void setMultiplePersonsChecked() {
        if (multiplePersonsChecked) multiplePersonsChecked = false;
        else multiplePersonsChecked = true;
    }

    /** When the corresponding CheckBox in the GUI is clicked, set the according field to false or true.
     *
     */
    public void setPersonWithBowChecked () {
        if (personWithBowChecked) personWithBowChecked = false;
        else personWithBowChecked = true;
    }

    /** When the corresponding CheckBox in the GUI is clicked, set the according field to false or true.
     *
     */
    public void setAnimalChecked () {
        if (animalChecked) animalChecked = false;
        else animalChecked = true;
    }


/*    *//** Called when the slider itself is moved by the mouse, on releasing the Drag. Gives the Treshold TextField a new
     * value, according to the value chosen by the slider.
     *
     *//*

    @FXML
    public void setSlider () {




    }

    *//** Called when the Treshold TextField receives a new value, entered by the keyboard. Sets the Slider according to
     * the new input.
     *
     *//*

    @FXML
    public void setSliderValue () {
//        slider.setValue((double) threshold_field.getCharacters());

    }*/


    /**
     * Method for opening the Menu entry "About/About". Loads a new .fxml, AboutMenu.fxml, which has it's own controller.
     * Content is loaded from file "info" in the resources folder.
     * @see AboutMenuController
     */
    @FXML
    public void openAboutWindow () {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AboutMenu.fxml"));

            root = loader.load();

            AboutMenuController ctl = loader.getController();
            ctl.setHostServices(hostServices);
            Stage stage = new Stage();
            stage.setTitle("About this App");
            stage.setScene(new Scene(root, 1000, 500));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void openResults () {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Results.fxml"));

            root = loader.load();

            ResultController ctl = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Results");
            stage.setScene(new Scene(root, 1000, 500));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for opening the Menu entry "File/Train New Classifier". Load a new fxml, NewClassifierMenu.fxml, which has it's own controller
     * Currently disabled because of the restriction to maximum number of events for the project.
     * @see NewClassifierController
     */
/*
    @FXML
    public void openNewClassifier() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("NewClassifierMenu.fxml"));

            root = loader.load();

            AboutMenuController ctl = loader.getController();
            ctl.setHostServices(hostServices);
            Stage stage = new Stage();
            stage.setTitle("Train a new Classifier");
            stage.setScene(new Scene(root, 1000, 500));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
*/
    /**
     * Quits the app.
     */
    @FXML
    public void quitApp () {

        Platform.exit();
    }

    /**
     * Method to open a single image to be classified. Sets the selected image as the image for the ImageView-Element.
     * @see
     * @param actionEvent
     * @throws MalformedURLException
     */
    @FXML
    public void openSingleImage (ActionEvent actionEvent) throws MalformedURLException {

        String imageFile;

        // get the node where the ActionEvent happened, so the FileChooser can get opened
        Node source = (Node) actionEvent.getSource();
        FileChooser fileChooser = new FileChooser();

        //set filters, so only some file types can be opened
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        File selectedFile = fileChooser.showOpenDialog(source.getScene().getWindow());

        if (selectedFile != null) {

            //if File is valid, set the file as Image for image_view

            imageFile = selectedFile.toURI().toURL().toString();

            Image image = new Image(imageFile);
            image_view.setImage(image);

            //Finally, get the result of the classification out to the user
            sliderValue = (float) slider.getValue() / 100;

            ClassifierCore cc = new ClassifierCore(personChecked, multiplePersonsChecked,
                                                    animalChecked, personWithBowChecked, sliderValue);
            String results = cc.classify(selectedFile);

            try {
                Files.write(Paths.get("src/main/resources/results"), results.getBytes(), StandardOpenOption.WRITE);
            }catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            openResults();

//            System.out.println(results);

        } else {
            System.out.println("error opening file");
        }
    }

    /**
     * Opens multiple image files as a list, each of them to be classified. Sets one image as the image for the ImageView element.
     * Currently disabled because of the restriction to the maximum number of events we could use for our project.
     * @param ae
     */
    /* @FXML
    private void openMultiImage (ActionEvent ae) {
        Node source = (Node) ae.getSource();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Multiple Image Files");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("IMAGE files (*.jpg, *.png, *.bmp)", "*.png", "*.jpg", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);

        List<File> list =
                fileChooser.showOpenMultipleDialog(source.getScene().getWindow());
        if (list != null) {

            short a = 1;
            for (File file : list) {

                    Image image = new Image(file.toURI().toString());

                    if (a == 1) {
                        a = 0;
                        image_view.setImage(image);

                    }
            }
        }

    }
    */



}
