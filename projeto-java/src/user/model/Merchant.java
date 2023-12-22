package user.model;

import products.model.Product;

import java.util.ArrayList;

public class Merchant extends User{

    private ArrayList<Product> products = new ArrayList<>();

    public Merchant(int id, int tipo, String nomeCompleto, String nomeUsuario, String senha, String saldo, Product product) {
        super(id, tipo, nomeCompleto, nomeUsuario, senha, saldo);
        this.products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void sell(float valor) {
        this.setSaldo(this.getSaldo() + valor);
    }
}
