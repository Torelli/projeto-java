package user.model;

import products.model.Product;

import java.util.ArrayList;

public class Merchant extends User{

    private ArrayList<Product> products = new ArrayList<>();

    public Merchant(int id, int type, String fullName, String userName, String password, double balance, ArrayList<Product> products) {
        super(id, type, fullName, userName, password, balance);
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void sell(float valor) {
        this.setBalance(this.getBalance() + valor);
    }
}
