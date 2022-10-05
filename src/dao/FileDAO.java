package dao;

import entity.Product;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class FileDAO {
    public static ArrayList<Product> loadData(String fileName) throws Exception {
        //check validation
        File tmp = new File(fileName); // lop file de check thong tin file
        if (!tmp.exists() || !tmp.isFile()) return null;
        // mo file
        FileReader f = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(f);
        // doc file
        ArrayList<Product> list = new ArrayList<>();
        while (bf.ready()) {
            String s = bf.readLine();
            //chuyen s thanh object
            String[] arr = s.split("[,.\n]");
            if (arr.length == 5) {
                Product p = new Product(arr[0].trim(), arr[1].trim(), Double.parseDouble(arr[2].trim()), Integer.parseInt(arr[3].trim()), arr[4].trim());
                list.add(p);
            }
        }
        // dong file
        f.close();
        bf.close();
        return list;
    }

    public static ArrayList<String> loadProductName(String fileName) throws Exception {
        File tmp = new File(fileName);
        if (!tmp.exists() || !tmp.isFile()) return null;
        FileReader f = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(f);
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<String> productsName = new ArrayList<>();
        while (bf.ready()) {
            String str = bf.readLine();
            String[] arr = str.split("[,.]");
            if (arr.length == 5) {
                productsName.add(arr[1].trim());
            }
        }
        f.close();
        bf.close();
        return productsName;
    }

    //ghi noi vao file
    public static void writeData(String fileName, ArrayList<Product> products) throws Exception {
        File tmp = new File(fileName);
        if (!tmp.exists() || !tmp.isFile()) return;
        FileOutputStream f = new FileOutputStream(fileName, true); // Stream : Mac dinh la nhi phan
        // de ghi van ban
        OutputStreamWriter w = new OutputStreamWriter(f);
        for (Product prod : products) {
            DecimalFormat format = new DecimalFormat();
            format.setDecimalSeparatorAlwaysShown(false);
            String productInfo = prod.getProductId() + ", " + prod.getProductName() + ", " + format.format(prod.getUnitPrice()) + ", " + prod.getQuantity() + ", " + prod.getStatus();
            w.write("\n"+productInfo);
        }
        w.close();
        f.close();
    }


}
