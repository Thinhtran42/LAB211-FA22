package dao;

import entity.Product;
import mylib.Validation;

import java.lang.reflect.Array;
import java.util.*;

public class ProductsDAOimp implements ProductsDAO<Product> {
    ArrayList<Product> products;
    final String fileName = "src/data/Product.txt";

    public ProductsDAOimp(String filename) {
        try {
            products = FileDAO.loadData(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductsDAOimp() {
        products = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public boolean addProduct(Product product) {
        return products.add(product);
    }

    @Override
    public boolean createProduct() {
        Product newProduct = new Product();
        newProduct.inputProduct();
        for (Product prod : products) {
            if (prod.getProductName().equalsIgnoreCase(newProduct.getProductName().trim())) {
                return false;
            }
        }
        this.addProduct(newProduct);
        return true;
    }

    @Override
    public void checkIsExisted() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Check product existed ? ");
        System.out.println("Name of product search: ");
        String productName = sc.nextLine();
        try {
            ArrayList<String> productNameList = FileDAO.loadProductName(fileName);
            Iterator<Product> iterator = products.iterator();
            int count = 0;
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getProductName().equalsIgnoreCase(productName.trim())) {
                    count++;
                }
            }
            if (count >= 1) {
                System.out.println("Exist product ! ");
            } else System.out.println("No product found !");
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    @Override
    public void searchProduct() {
        ArrayList<Product> listSearchProduct = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Input string to search by name: ");
            String string = sc.nextLine();
//            String[] arr = string.split("");
            for (Product prod : products) {
                if (prod.getProductName().contains(string)) {
                    listSearchProduct.add(prod);
                }
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        if (listSearchProduct.isEmpty()) System.out.println("Have no any product ! ");
        else {
            for (Product prod : listSearchProduct) {
                System.out.println(prod);
            }
        }
    }

    @Override
    public void updateProduct() {
        Scanner sc = new Scanner(System.in);
        if (!products.isEmpty()) {
            System.out.print("Input the id of Product to update: ");
            String idToUpdate = sc.nextLine();
            Product prod = this.search(idToUpdate);
            if (prod == null) System.out.println("Product " + idToUpdate + " does not exist !");
            else {
                System.out.println("Input new information of Product:");
                while (true) {
                    try {
                        String newStatus = "";
                        String newName = Validation.inputBlankString("Name: ");
                        double newUnitPrice = Validation.inputBlankNumber("Unit Price: ", 0, 10000);
                        int newQuantity = (int) Validation.inputBlankNumber("Quantity: ", 0, 1000);
                        String newCheckedStatus = Validation.inputBlankBoolean("Status is available (true) / not available (false): ");
                        if (newCheckedStatus.equalsIgnoreCase("true")) {
                            newStatus = "Available";
                        } else if (newCheckedStatus.equalsIgnoreCase("false")) {
                            newStatus = "Not available";
                        }
                        if (newName.contains("\n")) {
                            prod.setProductName(prod.getProductName());
                        } else {
                            prod.setProductName(newName);
                        }
                        if (newUnitPrice == 0.0) {
                            prod.setUnitPrice(prod.getUnitPrice());
                        } else {
                            prod.setUnitPrice(newUnitPrice);
                        }
                        if (newQuantity == 0.0) {
                            prod.setQuantity(prod.getQuantity());
                        } else {
                            prod.setQuantity(newQuantity);
                        }
                        if (newCheckedStatus.contains("\n")) {
                            prod.setStatus(prod.getStatus());
                        } else {
                            prod.setStatus(newStatus);
                        }

                        break;
                    } catch (Exception er) {
                        System.out.println("Please input again !");
                    }
                }
            }
        } else {
            System.out.println("Empty list. No update can be performed ! ");
        }
    }

    public Product search(String id) {
        for (Product p : products) {
            if (p.getProductId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    @Override
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        if (!products.isEmpty()) {
            System.out.print("Input the id of Product to remove: ");
            String idToRemove = sc.nextLine();
            Product prod = this.search(idToRemove);
            if (prod == null) System.out.println("Product " + idToRemove + " does not exist !");
            else {
                products.remove(prod);
                System.out.println("Product " + idToRemove + " has been removed !");
            }
        } else System.out.println("Empty list. No delete can be performed !");
    }

    @Override
    public void displayProductsFromFile() {
        System.out.println("All products in file: ");
        if (!products.isEmpty()) {
            Collections.sort(products);
            for (int i = 0; i < products.size(); i++) {
                for (int j = i + 1; j < products.size(); j++) {
                    if (products.get(i).getQuantity() == products.get(j).getQuantity()) {
                        Collections.swap(products, i, j);
                    }
                }
            }
            for (Product prod : products) {
                System.out.println(prod);
            }
        } else System.out.println("No data in file ! ");
    }

    @Override
    public void displayProducts() {
        System.out.println("Products be added: ");
        if (!products.isEmpty()) {
            for (Product p : products) {
                System.out.println(p);
            }
        } else System.out.println("No data be added !");
    }

    @Override
    public boolean saveToFile() {
        try {
            if (!products.isEmpty()) {
                FileDAO.writeData(fileName, products);
                System.out.println("Saved to File");
            } else System.out.println("No data to save ! ");
            ;
        } catch (Exception er) {
            er.printStackTrace();
            return false;
        }
        return true;
    }
}
