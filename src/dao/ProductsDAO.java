package dao;

import entity.Product;

import java.util.TreeSet;

public interface ProductsDAO<T> {

    boolean isEmpty();
    boolean addProduct(Product product);
    boolean createProduct();
    void checkIsExisted();
    void searchProduct();
    void updateProduct();
    void deleteProduct();
    void displayProductsFromFile();
    void displayProducts();
    boolean saveToFile();
}
