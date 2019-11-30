/**
 * Raul Padilla
 * C482 Software 1
 * AddPartController Class
 */
package inventorymanagementsystems;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddPartController implements Initializable {

    /**
     * Fields
     */
    double x, y; //Variable coordinates to move the application

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private RadioButton outSourceRadio;

    private ToggleGroup radioToggleGroup;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField companyTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField inventoryTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private Label choiceLabel;

    private InHouse inPart;
    private Outsourced outPart;

    int counter;

    /**
     * Toggles between each radio button.
     */
    public void radioButtonPushed(ActionEvent event) throws IOException {
        if (this.radioToggleGroup.getSelectedToggle().equals(this.inHouseRadio)) {
            companyTextField.disableProperty().setValue(Boolean.FALSE);
            this.choiceLabel.setText("Machine ID#");
        }
        if (this.radioToggleGroup.getSelectedToggle().equals(this.outSourceRadio)) {
            companyTextField.disableProperty().setValue(Boolean.FALSE);
            this.choiceLabel.setText("Company Name");
        }

    }

    /**
     * Exits the program
     */
    public void changeExitButtonPushed(ActionEvent event) throws IOException {
        Platform.exit();
    }

    /**
     * Minimizes the program.
     */
    public void changeMinimizeButtonPushed(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setIconified(true);
    }

    //***Next two methods work simultaneously to move the window around.***
    /**
     * Method will find the current coordinates of title bar press
     */
    @FXML
    public void pressCurrentCordinate(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /**
     * Moves the program window around
     */
    @FXML
    public void draggedWindowBar(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setX(event.getScreenX() - x);
        window.setY(event.getScreenY() - y);
    }

    /**
     * Screen will change to the MainPage.
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainPage.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This will save the part into the management's inventory.
     */
    public void saveButtonPushed(ActionEvent event) throws IOException {

        try {
            int idCounter;
            idCounter = Inventory.getAllParts().size() + 1;

            if (this.radioToggleGroup.getSelectedToggle().equals(this.inHouseRadio)) {
                inPart = new InHouse(idCounter,
                        nameTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        Integer.parseInt(inventoryTextField.getText()),
                        Integer.parseInt(minTextField.getText()),
                        Integer.parseInt(maxTextField.getText()),
                        Integer.parseInt(companyTextField.getText()));

                if (Integer.parseInt(inventoryTextField.getText()) <= Integer.parseInt(maxTextField.getText()) && Integer.parseInt(inventoryTextField.getText()) >= Integer.parseInt(minTextField.getText())) {
                    Inventory.getAllParts().add(inPart);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("MainPage.fxml"));
                    loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
                    stage.setScene(new Scene(scene));
                    stage.show();
                } else {
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setTitle("Warning Message");
                    warning.setContentText("Inventory can't have more than max or less than min.");
                    warning.showAndWait();
                }
            }
            if (this.radioToggleGroup.getSelectedToggle().equals(this.outSourceRadio)) {
                outPart = new Outsourced(idCounter,
                        nameTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        Integer.parseInt(inventoryTextField.getText()),
                        Integer.parseInt(minTextField.getText()),
                        Integer.parseInt(maxTextField.getText()),
                        companyTextField.getText());

                if (Integer.parseInt(inventoryTextField.getText()) <= Integer.parseInt(maxTextField.getText()) && Integer.parseInt(inventoryTextField.getText()) >= Integer.parseInt(minTextField.getText())) {
                    Inventory.getAllParts().add(outPart);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("MainPage.fxml"));
                    loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
                    stage.setScene(new Scene(scene));
                    stage.show();
                } else {
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setTitle("Warning Message");
                    warning.setContentText("Inventory can't have more than max or less than min.");
                    warning.showAndWait();
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Please enter valid input values into the text field.");
            alert.setContentText("Error: " + e);
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        radioToggleGroup = new ToggleGroup();
        this.inHouseRadio.setToggleGroup(radioToggleGroup);
        this.outSourceRadio.setToggleGroup(radioToggleGroup);
    }

}
