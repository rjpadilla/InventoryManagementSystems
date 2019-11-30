/**
 * Raul Padilla
 * C482 Software 1
 * InHouse Class
 */
package inventorymanagementsystems;

public class InHouse extends Part {
    //Fields
    private int machineId;
    
    //Constructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id,name,price,stock,min,max);
        this.machineId = machineId;
    }
    
    //Getter
    public int getMachineId() {
        return machineId;
    }
    
    //Setter
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }  
}
