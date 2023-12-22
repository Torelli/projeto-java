package products.repository;

import products.model.Product;
import user.controller.UserController;

public interface ProductRepository {

    public void findById(int id);

    public void findAll(UserController userController);

    public void findAllByMerchant(int merchantId);


    public void sell(UserController userController, int merchantId, Product product, int quantity);
}
