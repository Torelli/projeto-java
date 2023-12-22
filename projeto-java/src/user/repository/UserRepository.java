package user.repository;

import products.controller.ProductController;
import user.controller.UserController;
import user.model.User;

public interface UserRepository {

    public void signUp(User user);

    public void login(String userName, String password);

    public void viewAccount();

    public void updateUser(User user);

    public void deleteUserById();


    public void transfer(ProductController productController, UserController userController, int senderId, int receiverId, double value, int productId, int quantity);

}
