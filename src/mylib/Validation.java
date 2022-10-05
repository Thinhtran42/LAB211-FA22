package mylib;

import dao.ProductsDAOimp;
import entity.Product;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static Scanner sc = new Scanner(System.in);

    public static String inputString(String msg) throws Exception {
        System.out.print(msg);
        String str = sc.nextLine();
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(str);
        boolean found = matcher.find();
        if (str.trim().length() < 5 || found) throw new Exception();
        return str;
    }

    public static double inputNumber(String msg, double min, double max) throws Exception {
        System.out.print(msg);
        double number = Double.parseDouble(sc.nextLine());
        if (number < min || number > max) throw new Exception();
        return number;
    }

    public static String inputString2(String msg) throws Exception {
        System.out.print(msg);
        String str = sc.nextLine();
        sc = new Scanner(System.in);
        return str;
    }

    public static boolean inputBoolean(String msg) throws Exception {
        System.out.print(msg);
        Boolean isIt = Boolean.parseBoolean(sc.nextLine());
        return isIt;
    }

    public static String inputBlankString(String msg) throws Exception {
        System.out.print(msg);
        String str = sc.nextLine();
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(str);
        boolean found = matcher.find();

        if (str.isEmpty()) {
            str = "\n";
        } else {
            if (str.trim().length() < 5 || found) throw new Exception();
        }
        return str;
    }

    public static double inputBlankNumber(String msg, double min, double max) throws Exception {
        System.out.print(msg);
        String number = sc.nextLine();

        if (number.isEmpty()) {
            return 0.0;
        } else {
            if (Double.parseDouble(number) < min || Double.parseDouble(number) > max) throw new Exception();
        }
        return Double.parseDouble(number);
    }

    public static String inputBlankBoolean(String msg) {
        System.out.print(msg);
        String str = sc.nextLine();
        if (str.isEmpty()) {
            str = "\n";
        }
        return str;
    }
}
