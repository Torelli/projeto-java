package products.controller;

import products.model.Product;
import products.repository.ProductRepository;
import user.controller.UserController;

import java.util.ArrayList;

public class ProductController implements ProductRepository {

    private ArrayList<Product> allProducts = new ArrayList<>();
    int newId = 0;

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
            System.out.println("|  ID  |  Nome do Produto | Quantidade | Loja  |");
            System.out.println("|  " + product.getId() + "  |  " + product.getName() + "  |  " + product.getQuantity() + "  |  " + store.getFullName() + "  |");
        }
    }

    @Override
    public void findAllByMerchant(int merchantId) {
        for (var product : allProducts) {
            if (product.getMerchantId() == merchantId) {
                System.out.println("|  ID  |  Nome do Produto | Quantidade |");
                System.out.println("|  " + product.getId() + "  |  " + product.getName() + "  |  " + product.getQuantity() + "  |");
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
