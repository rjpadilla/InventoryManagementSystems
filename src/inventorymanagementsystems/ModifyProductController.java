/**
 * Raul Padilla
 * C482 Software 1
 * ModifyProductController Class
 **** Exception Control Set 1(entering an inventory value that exceeds the minimum or maximum value for that part or product) on line 180-183 ****
 **** Exception Control Set 2(ensuring that a product must have a name, price, category, and inventory level) on line 186-192 ****
 */
package inventorymanagementsystems;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyProductController implements Initializable {

    /**
     * Fields
     */
    double x, y; //Variable coordinates to move the application

    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, String> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, String> partInventoryColumn;

    @FXML
    private TableColumn<Part, String> partPriceColumn;

    @FXML
    private TableView<Part> addedPartTable;

    @FXML
    private TableColumn<Part, String> addedIdColumn;

    @FXML
    private TableColumn<Part, String> addedNameColumn;

    @FXML
    private TableColumn<Part, String> addedInventoryColumn;

    @FXML
    private TableColumn<Part, String> addedPriceColumn;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField inventoryTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField searchProductField;

    private static Product modifiedProduct;
    public static ObservableList<Part> associatedPartsList;
    public static Part associatedPart;
    public static Product product;

    /**
     * Exits the program
     */
    public void changeExitButtonPushed(ActionEvent event) throws IOException {
        Platform.exit();
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

        Inventory.getAllProduct().add(modifiedProduct);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainPage.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Minimizes the program.
     */
    public void changeMinimizeButtonPushed(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setIconified(true);
    }

    public void saveButtonPushed(ActionEvent event) throws IOException {

        try {
            product = new Product(Integer.parseInt(idTextField.getText()),
                    nameTextField.getText(),
                    Double.parseDouble(priceTextField.getText()),
                    Integer.parseInt(inventoryTextField.getText()),
                    Integer.parseInt(minTextField.getText()),
                    Integer.parseInt(maxTextField.getText())
            );

            product.addAssociatedPart(associatedPart);
            if (deleteButton.isPressed()) {
                product.deleteAssociatedPart(associatedPart);
            }

            if (Integer.parseInt(inventoryTextField.getText()) <= Integer.parseInt(maxTextField.getText()) && Integer.parseInt(inventoryTextField.getText()) >= Integer.parseInt(minTextField.getText())) {
                Inventory.getAllProduct().add(product);
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

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Please enter valid input values into the text field.");
            alert.setContentText("Error: " + e);
            alert.showAndWait();

        }

    }

    public void setProduct(Product product) {
        modifiedProduct = product;

        associatedPartsList = modifiedProduct.getAllAssociatedParts();

        idTextField.setText(String.valueOf(product.getId()));
        nameTextField.setText(product.getName());
        priceTextField.setText(String.valueOf(product.getPrice()));
        inventoryTextField.setText(String.valueOf(product.getStock()));
        minTextField.setText(String.valueOf(product.getMin()));
        maxTextField.setText(String.valueOf(product.getMax()));

        addedIdColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        addedNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addedInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        addedPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));

        addedPartTable.setItems(associatedPartsList);

        Inventory.updateProduct(product.getId(), product);

    }

    public void addPartPushed(ActionEvent event) throws IOException {

        ObservableList<Part> partList = FXCollections.observableArrayList();
        associatedPart = partTable.getSelectionModel().getSelectedItem();
        partList.add(partTable.getSelectionModel().getSelectedItem());
        addedPartTable.setItems(partList);

        addedIdColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        addedNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addedInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        addedPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));

    }

    @FXML
    void searchPartTableButtonPushed(ActionEvent event) {
        try {
            ObservableList<Part> partList = FXCollections.observableArrayList();
            Part intSearch = Inventory.lookupPart(Integer.parseInt(searchProductField.getText()));

            partList.add(intSearch);
            partTable.setItems(partList);
        } catch (NumberFormatException e) {
            partTable.setItems(Inventory.lookupPart(searchProductField.getText()));
        }
    }

    @FXML
    void deleteAssociatedPart(ActionEvent event) {
        ObservableList<Part> partList = FXCollections.observableArrayList();
        addedPartTable.setItems(partList);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        partTable.setItems(Inventory.getAllParts());

    }

}
