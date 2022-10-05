package entity;

import dao.ProductsDAOimp;
import mylib.Validation;

public class Product implements Comparable<Product>{
    private String productId;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String status;

    public Product() {

    }

    public Product(String productId, String productName, double unitPrice, int quantity, String status) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void inputProduct() {
        System.out.println("Input information of Product:");
        while (true) {
            try {
                productId =  Validation.inputString2("ID: ");
                productName = Validation.inputString("Name: ");
//                ProductsDAOimp product = new ProductsDAOimp();
//                if (product.checkDuplicate(productName)) {
//                    throw new Exception();
//                }
                unitPrice = Validation.inputNumber("Unit Price: ", 0, 10000);
                quantity = (int) Validation.inputNumber("Quantity: ", 0, 1000);
                boolean checkedStatus = Validation.inputBoolean("Status is available (true) / not available (false): ");
                if (checkedStatus) status = "Available";
                else status = "Not available";
                break;
            } catch (Exception er) {
                System.out.println("Please input again !");
            }
        }
    }

    @Override
    public String toString() {
        return "id: " + productId + ", name: " + productName + ", unit price: " + unitPrice + ", quantity: " + quantity + ", status: " + status;
    }


    @Override
    public int compareTo(Product p) {
        if (this.quantity > p.getQuantity()) return -1; //1 la dong y swap 2 object trong mang
        else if (this.quantity < p.getQuantity()) return 1;
        return 0;
    }
}
