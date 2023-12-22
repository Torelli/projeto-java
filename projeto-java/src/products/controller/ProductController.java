package products.controller;

import products.model.Product;
import products.repository.ProductRepository;
import user.controller.UserController;

import java.util.ArrayList;

public class ProductController implements ProductRepository {

    private ArrayList<Product> allProducts = new ArrayList<>();
    int newId = 0;

    @Override
    public void addProduct(Product product) {
        allProducts.add(product);
    }

    @Override
    public void findById(int id) {
        var product = searchAllProductsById(id);

        if (product != null) System.out.println(product);
        else System.out.println("Produto não encontrado!");
    }

    @Override
    public void findAll(UserController userController) {
        for (var product : allProducts) {
            var store = userController.findUserById(product.getMerchantId());
            System.out.println("|\tID\t|\tNome do Produto\t\t\t|\tQuantidade\t|\tLoja\t\t|");
            System.out.println("|\t" + product.getId() + "\t|\t" + product.getName() + "\t|\t\t" + product.getQuantity() + "\t\t|\t" + store.getFullName() + "\t|");
        }
    }

    @Override
    public void findAllByMerchant(int merchantId) {
        for (var product : allProducts) {
            if (product.getMerchantId() == merchantId) {
                System.out.println("|\tID\t|\tNome do Produto\t\t\t|\tQuantidade\t|");
                System.out.println("|\t" + product.getId() + "\t|\t" + product.getName() + "\t|\t\t" + product.getQuantity() + "\t\t|");
            }
        }
    }

    @Override
    public void sell(UserController userController, int merchantId, Product product, int quantity) {
        var user = userController.findUserById(merchantId);
        var products = user.getProducts();

        for (var updatedProduct : products) {
            if (updatedProduct.getId() == product.getId()) {
                if (updatedProduct.getQuantity() > quantity)
                    updatedProduct.setQuantity(updatedProduct.getQuantity() - quantity);
                else System.out.println("Quantidade maior que no estoque!");
            }
        }

        user.setProducts(products);
    }

    public int generateId() {
        return ++newId;
    }

    public Product searchAllProductsById(int id) {
        for (var product : allProducts) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }
}
