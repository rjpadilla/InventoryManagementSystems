/*
 * Raul Padilla
 * C482 Software 1
 * Outsourced Class
 */
package inventorymanagementsystems;

public class Outsourced extends Part {

    /**
     * Fields
     */
    private String companyId;

    //Constructor
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyId) {
        super(id, name, price, stock, min, max);
        this.companyId = companyId;
    }

    //Getter
    public String getCompanyId() {
        return companyId;
    }

    //Setter
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

}
