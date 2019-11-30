/**
 * Raul Padilla
 * C482 Software 1
 * MainPageController Class
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainPageController implements Initializable {

    /**
     * Fields
     */
    double x, y;

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
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, String> productInventoryColumn;

    @FXML
    private TableColumn<Product, String> productPriceColumn;

    @FXML
    private TextField searchPartField;

    @FXML
    private TextField searchProductField;

    static boolean entered;

    /**
     * This will change the scene to the Add Parts screen.
     */
    public void changeAddPartsButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * This will change the scene to the Modify Parts screen.
     */
    public void changeModifyPartsButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPart.fxml"));
        loader.load();

        ModifyPartController modifyController = loader.getController();
        modifyController.setPart(partTable.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This will change the scene to the Add Products screen.
     */
    public void changeAddProductsButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddProduct.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This will change the scene to the Modify Product screen.
     */
    public void changeModifyProductsButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
        loader.load();

        ModifyProductController modifyProductController = loader.getController();
        modifyProductController.setProduct(productTable.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This will exit the program.
     */
    public void changeExitButtonPushed(ActionEvent event) throws IOException {
        Platform.exit();
    }

    /**
     * This will minimize the program.
     */
    public void changeMinimizeButtonPushed(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setIconified(true);
    }

    //Next two methods work simultaneously to move the window around.
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
     * This will search the part table.
     */
    @FXML
    void searchAddTableButtonPushed(ActionEvent event) {
        try {
            ObservableList<Part> partList = FXCollections.observableArrayList();
            Part intSearch = Inventory.lookupPart(Integer.parseInt(searchPartField.getText()));

            partList.add(intSearch);
            partTable.setItems(partList);
        } catch (NumberFormatException e) {
            partTable.setItems(Inventory.lookupPart(searchPartField.getText()));
        }

    }

    /**
     * This will search the product table.
     */
    @FXML
    void searchProductTableButtonPushed(ActionEvent event) {
        try {
            ObservableList<Product> productList = FXCollections.observableArrayList();
            Product intSearch = Inventory.lookupProduct(Integer.parseInt(searchProductField.getText()));

            productList.add(intSearch);
            productTable.setItems(productList);
        } catch (NumberFormatException e) {
            productTable.setItems(Inventory.lookupProduct(searchProductField.getText()));
        }

    }

    /**
     * This will delete a part in the part's table.
     */
    @FXML
    void deletePartPushed(ActionEvent event) {
        Inventory.deletePart((partTable.getSelectionModel().getSelectedItem()));
    }

    /**
     * This will delete a product in the product's table.
     */
    @FXML
    void deleteProductPushed(ActionEvent event) {
        Inventory.deleteProduct((productTable.getSelectionModel().getSelectedItem()));
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //set up the columns in the table
        if (!entered) {
            Inventory.getAllParts().add(new InHouse(1, "Screw", 2.00, 3, 3, 3, 4));
            Inventory.getAllProduct().add(new Product(1, "Door", 1.00, 2, 2, 3));
            entered = true;
        }
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        partTable.setItems(Inventory.getAllParts());

        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        productTable.setItems(Inventory.getAllProduct());

    }

}
