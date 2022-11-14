package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    private final List<Product> products = new ArrayList<>();
    private final String PRODUCT_DB_FILE = "products.txt";

    public ProductDB() {

    }

    public Product returnProduct(String name){
        for(Product currentProduct:this.products){
            if(currentProduct.getName().equals(name)) return currentProduct;
        }
        return null;
    }


    public List<Product> getProducts() {
        return products;
    }

}
