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
    public void updateProduct(Product product) {
        var oldProduct = searchAllProductsById(product.getId());

        if (oldProduct != null) {
            allProducts.set(allProducts.indexOf(oldProduct), product);
            System.out.println("\nProduto editado com sucesso!\n");
        } else System.out.println("\nProduto não encontrado!\n");
    }

    @Override
    public void deleteById(int id) {
        var product = searchAllProductsById(id);

        if (product != null) {
            if (allProducts.remove(product))
                System.out.println("O produto " + product.getName() + " foi removido com sucesso!");
            else System.out.println("O produto não foi encontrado!");
        }
    }

    @Override
    public void findById(int id) {
        var product = searchAllProductsById(id);

        if (product != null) System.out.println(product);
        else System.out.println("Produto não encontrado!");
    }

    @Override
    public void findAll(UserController userController) {
       if(allProducts.size() > 0) {
           System.out.println("|\tID\t|\tNome do Produto\t\t\t|\tQuantidade\t|\tLoja\t\t|");
           for (var product : allProducts) {
               var store = userController.findUserById(product.getMerchantId());
               System.out.println("|\t" + product.getId() + "\t|\t" + product.getName() + "\t|\t\t" + product.getQuantity() + "\t\t|\t" + store.getFullName() + "\t|");
           }
       } else {
           System.out.println("Nenhum produto cadastrado!");
       }

    }

    @Override
    public void findAllByMerchant(int merchantId) {
        boolean hasProduct = false;
        System.out.println("|\tID\t|\tNome do Produto\t\t\t|\tQuantidade\t|");
        for (var product : allProducts) {
            if (product.getMerchantId() == merchantId) {
                hasProduct = true;
                System.out.println("|\t" + product.getId() + "\t|\t" + product.getName() + "\t|\t\t" + product.getQuantity() + "\t\t|");
            }
        }
        if (!hasProduct) System.out.println("Nenhum produto cadastrado!");
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
