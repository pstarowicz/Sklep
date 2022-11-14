package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    private final List<Product> products = new ArrayList<>();
    private final String PRODUCT_DB_FILE = "products.txt";

    public ProductDB() {
        /*try {
            BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_DB_FILE));
            String line;
            while((line = reader.readLine()) != null) {
                String[] productData = line.split(";");
                this.products.add(new Product(productData));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("plik nie dziala !!");
        }*/
    }

    public Product returnProduct(String name){
        for(Product currentProduct:this.products){
            if(currentProduct.getName().equals(name)) return currentProduct;
        }
        return null;
    }

    public void persistToFile() {
        try {
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(this.PRODUCT_DB_FILE));
            writer.write(this.products.get(0).convertToData());
            for(int i = 1; i < this.products.size(); i++) {
                writer.newLine();
                writer.write(this.products.get(i).convertToData());
                //writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu");
            e.printStackTrace();
        }
    }

    public List<Product> getProducts() {
        return products;
    }

}
