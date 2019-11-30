/*
 * Raul Padilla
 * C482 Software 1
 * Product Class
 */
package inventorymanagementsystems;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    /**
     * Fields
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    //Constructor
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Rest of the methods are getters and setters.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * These next methods will add, delete, and return parts to the product.
     */
    
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    public void deleteAssociatedPart(Part part){
        associatedParts.remove(part);
    }
    
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
