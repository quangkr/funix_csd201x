package asm2;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
    static File outputFile;
    static PrintStream outputStream;

    public static void main(String[] args) {
        try {
            outputFile = new File("console_output.txt");
            outputFile.createNewFile();
            outputStream = new PrintStream(outputFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProductList list = new ProductList();
//        DoublyLinkedList<Product> list = new DoublyLinkedList<>();

        list.add(new Product("Golden Dildo", 200.99, 100));
        list.add(new Product("Strap on", 310.99, 1));
        list.add(new Product("Dildo", 200.99, 100));
        list.add(new Product("Pegging strap on", 310.99, 1));
        list.add(new Product("Vibrating Dildo", 200.99, 100));
        list.add(new Product("Fleshlight", 399, 1));
        list.add(new Product("Glass Dildo", 200.99, 100));
        list.add(new Product("Suction Dildo", 200.99, 100));
        list.add(new Product("Egg vibrator", 109.99, 85));
        list.add(new Product("Butt plug", 29, 742));
        list.add(new Product("Pegging strap on", 310.99, 1));
        list.add(new Product("Vibrating Dildo", 200.99, 100));
        list.add(new Product("Fleshlight", 399, 1));
        list.add(new Product("Glass Dildo", 200.99, 100));
        list.add(new Product("Suction Dildo", 200.99, 100));
        
        list.remove("P_10");
        list.remove("P_13");

//        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
//        list.addLast(53);
//        list.addLast(51);
//        list.addLast(92);
//        list.addLast(18);
//        list.addLast(27);
//        list.addLast(93);
//        list.addLast(83);

//        list.quickSort();
       
        dualPrintln(list.toString());
//        list.sort();
        dualPrintln(list.toString());
        dualPrintln(Integer.toString(list.size()));
        dualPrintln(list.getProductById("P_2").toString());
        dualPrintln(list.getProductById("P_6").toString());
        dualPrintln(list.getProductById("P_9").toString());
//        dualPrintln(list.getProductById("P_13").toString());
        dualPrintln();
        
        dualPrintln(Integer.toString(Integer.parseInt("1256")));
    }

    static String decimalToBinaryHelper(int decimalInput, String outAccumulator) {
        if (decimalInput == 0) {
            return outAccumulator;
        } else {
            return decimalToBinaryHelper(decimalInput / 2,
                    Integer.toString(decimalInput % 2) + outAccumulator);
        }
    }

    public static String decimalToBinary(int decimalInput) {
        if (decimalInput == 0) {
            return "0";
        }

        return decimalToBinaryHelper(decimalInput, "");
    }

    public static void dualPrint(String output) {
        System.out.print(output);
        outputStream.print(output);
    }

    public static void dualPrintln(String output) {
        System.out.println(output);
        outputStream.println(output);
    }
    
    public static void dualPrintln() {
        dualPrintln("");
    }
}