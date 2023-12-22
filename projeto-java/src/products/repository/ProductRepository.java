package products.repository;

import products.model.Product;
import user.controller.UserController;

public interface ProductRepository {

    public void addProduct(Product product);

    public void updateProduct(Product product);

    public void deleteById(int id);

    public void findById(int id);

    public void findAll(UserController userController);

    public void findAllByMerchant(int merchantId);


    public void sell(UserController userController, int merchantId, Product product, int quantity);
}
