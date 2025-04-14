import java.util.Objects;
public class Product {

    private String ID = "";
    private String name = "";
    private String description = "";
    private double cost = 0;

    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public String toCSV(){
        return ID + ", " + name + ", " + description + ", " + cost;
    }
    public String toJSON(){
        String retString ="";
        char DQ = '\u0022';
        retString = "{" +DQ+ "ID" + DQ+ ":" +DQ+ this.ID + DQ+ ",";
        retString += " " +DQ+ "name" +DQ+ ":" +DQ+ this.name +DQ+ ",";
        retString += " " +DQ+ "description" +DQ+ ":" +DQ+ this.description +DQ+ ",";
        retString += " " +DQ+ "cost" +DQ+ ":" + this.cost +"}";
        return retString;
    }
    public String toFormattedString() {
        return String.format("Product name: %s%nProduct ID: %s%nDescription: %s%nCost: $%.2f",
                name, ID, description, cost);
    }

    public String toXML(){
        String retString ="";

        retString = "<Product>";
        retString += "<ID>" + this.ID + "</ID>";
        retString += "<name>" + this.name + "</name>";
        retString += "<description>" + this.description + "</description>";
        retString += "<cost>" + this.cost + "</cost>";
        retString += "</Product>";
        return retString;
    }
    public static Product toProduct(String formatted) {
        if (formatted == null || formatted.length() < 125) {
            throw new IllegalArgumentException("Input string is not in the expected format.");
        }
        String name = formatted.substring(0, 35).trim();
        String description = formatted.substring(35, 110).trim();
        String id = formatted.substring(110, 116).trim();
        double cost = Double.parseDouble(formatted.substring(116, 125).trim());
        return new Product(id, name, description, cost);
    }


    @Override
    public int hashCode() {
        return Objects.hash(ID, name, description, cost);
    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "ID='" + ID + '\'' +
//                ", Name='" + name + '\'' +
//                ", Description='" + description + '\'' +
//                ", Cost='" + cost +
//                "'}";
//    }
    @Override
    public String toString() {
        return String.format("%-35s%-75s%-6s%9.2f", name, description, ID, cost);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return cost == product.cost && Objects.equals(ID, product.ID) && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }
}
