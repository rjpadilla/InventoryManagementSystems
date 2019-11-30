/**
 * Raul Padilla
 * C482 Software 1
 * InventoryManagementSystems Class
 */
package inventorymanagementsystems;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InventoryManagementSystems extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        Scene scene = new Scene(root);

        /******************* Java Default Window Bar is Disabled/Custom Window Bar is Applied *******************/
        
        //This removes the window title bar
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
