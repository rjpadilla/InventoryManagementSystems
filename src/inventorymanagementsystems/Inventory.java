/**
 * Raul Padilla
 * C482 Software 1
 * Inventory Class
 */
package inventorymanagementsystems;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    /**
     * Fields
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    
   
    //This will add a part to the inventory.
    public static void addPart(Part part) {
        allParts.add(part);
    }

    //This will a product to the inventory.
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    //This will look up the "int id" of a part that's inside the inventory.
    public static Part lookupPart(int partId) {
        for (Part part : Inventory.allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    //This will look up the "int id" of a product that's inside the inventory.
    public static Product lookupProduct(int productId) {
        for (Product product : Inventory.allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    //This will look up the "string name" of a part that's inside the inventory.
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundName = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().contains(partName)) {
                foundName.add(allParts.get(i));
            }
        }
        return foundName;
    }

    //This will look up the "string name" of a product that's inside the inventory.
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundName = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().contains(productName)) {
                foundName.add(allProducts.get(i));
            }
        }
        return foundName;
    }

    //This will update a part that's inside the inventory.
    public static void updatePart(int index, Part part) {
        if (part instanceof InHouse) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).equals(part)) {
                    allParts.remove(i);
                }
            }
        }
        if (part instanceof Outsourced) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).equals(part)) {
                    allParts.remove(i);
                }
            }
        }
    }

    //This will update a product that's inside the inventory.
    public static void updateProduct(int index, Product product) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).equals(product)) {
                allProducts.remove(i);
            }
        }
    }

    //This will delete a part inside the inventory.
    public static void deletePart(Part part) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).equals(part)) {
                allParts.remove(i);
            }
        }
    }

    //This will delete a product inside the inventory.
    public static void deleteProduct(Product product) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).equals(product)) {
                allProducts.remove(i);
            }
        }
    }

    //This will get the parts inside the tableview.
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    //This will get all the products inside the tableview.
    public static ObservableList<Product> getAllProduct() {
        return allProducts;
    }

}
