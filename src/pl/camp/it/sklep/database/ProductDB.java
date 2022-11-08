package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.Product;

public class ProductDB {
    private Product[] products = new Product[4];

    public ProductDB() {
        this.products[0]= new Product("komputer",4999.99,10);
        this.products[1]= new Product("monitor",999.99,8);
        this.products[2]= new Product("mysz",59.99,20);
        this.products[3]= new Product("klawiatura",149.99,15);
    }

    public Product returnProduct(String name){
        for(Product currentProduct:this.products){
            if(currentProduct.getName().equals(name)) return currentProduct;
        }
        return null;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
