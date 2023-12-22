package user.model;

import products.model.Product;

import java.util.ArrayList;

public class Merchant extends User {

    private ArrayList<Product> products = new ArrayList<>();

    public Merchant(int id, int type, String fullName, String userName, String password, double balance, ArrayList<Product> products) {
        super(id, type, fullName, userName, password, balance);
        this.products = products;
    }

    @Override
    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public void sell(double value) {
        this.setBalance(this.getBalance() + value);
    }
}
