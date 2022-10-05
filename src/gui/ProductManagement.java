package gui;

import dao.ProductsDAO;
import entity.Product;
import dao.ProductsDAOimp;

import java.util.Scanner;

public class ProductManagement {
    final static String fileName = "src/data/Product.txt";

    public static void main(String[] args) {
        int choice = 0;
        ProductsDAO<Product> productFromFile = new ProductsDAOimp(fileName);
        ProductsDAO<Product> products = new ProductsDAOimp();
        do {
            System.out.println("||==========================================================||");
            System.out.println("<<<=================Product Management=====================>>>");
            System.out.println("||==========================================================||");
            System.out.println("||    Option 1: Display all products                        ||");
            System.out.println("||    Option 2: Create a Product                            ||");
            System.out.println("||    Option 3: Check to exist Product                      ||");
            System.out.println("||    Option 4: Search Product information by name          ||");
            System.out.println("||    Option 5: Update a Product                            ||");
            System.out.println("||    Option 6: Delete a Product                            ||");
            System.out.println("||    Option 7: Save to file                                ||");
            System.out.println("||    Option 8: Display all lists Product from file         ||");
            System.out.println("||==========================================================||");
            System.out.println("==============================================================");
            System.out.println("||==========================================================||");
            Scanner sc = new Scanner(System.in);
            System.out.print("Your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            String ans;
            switch (choice) {
                case 2:
                    if (products.createProduct()) System.out.println("Added !");
                    else System.out.println("Can not add because product name is duplicated !");
                    System.out.print("Return the main menu (y/n) ? ");
                    ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("n"))
                        choice = -1;
                    break;
                case 3:
                    productFromFile = new ProductsDAOimp(fileName);
                    productFromFile.checkIsExisted();
                    System.out.print("Return the main menu (y/n) ? ");
                    ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("n"))
                        choice = -1;
                    break;
                case 4:
                    productFromFile.searchProduct();
                    System.out.print("Return the main menu (y/n) ? ");
                    ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("n"))
                        choice = -1;
                    break;
                case 5:
                    products.displayProducts();
                    products.updateProduct();

                    System.out.print("Return the main menu (y/n) ? ");
                    ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("n"))
                        choice = -1;
                    break;
                case 6:
                    products.displayProducts();
                    products.deleteProduct();
                    System.out.print("Return the main menu (y/n) ? ");
                    ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("n"))
                        choice = -1;
                    break;
                case 7:
                    if (products.saveToFile()) System.out.println("No Error ! ");
                    else System.out.println("Error ......");
                    System.out.print("Return the main menu (y/n) ? ");
                    ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("n"))
                        choice = -1;
                    break;
                case 8:
                    productFromFile = new ProductsDAOimp(fileName);
                    productFromFile.displayProductsFromFile();
                    System.out.print("Return the main menu (y/n) ? ");
                    ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("n"))
                        choice = -1;
                    break;
                case 1:
                    productFromFile = new ProductsDAOimp(fileName);
                    products.displayProducts();
                    productFromFile.displayProductsFromFile();

                    System.out.print("Return the main menu (y/n) ? ");
                    ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("n"))
                        choice = -1;
                    break;
            }


        } while (choice != -1);
    }
}