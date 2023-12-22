package user.controller;

import products.model.Product;
import user.model.User;
import user.repository.UserRepository;

import java.util.ArrayList;

public class UserController implements UserRepository {

    private ArrayList<User> users = new ArrayList<>();
    private User loggedUser = null;

    int newId = 0;


    @Override
    public void signUp(User user) {
        users.add(user);
        System.out.println("Sua conta foi criada com sucesso!");
    }

    @Override
    public void login(String userName, String password) {
        var user = findUserByUserNameAndPassword(userName, password);

        if (user != null) System.out.println("Olá, " + user.getFullName() + "!");
        else System.out.println("Seu nome de usuário ou senha estão incorretos");
    }

    @Override
    public void updateUser(User user) {
        if (loggedUser != null) {
            user.setId(loggedUser.getId());
            users.set(users.indexOf(loggedUser), user);
        } else System.out.println("Você deve estar logado para alterar sua conta!");
    }

    @Override
    public void deleteUserById() {
        if (loggedUser != null) {
            if (users.remove(loggedUser)) System.out.println("\nSua conta foi deletada com sucesso!");
        } else System.out.println("Você deve estar logado para deletar sua conta!");
    }

    @Override
    public void transfer(int senderId, int receiverId, double value, int productId, int quantitity) {
        var buyer = findUserById(senderId);
        var merchant = findUserById(receiverId);

        if (buyer != null && merchant != null) {
            if (buyer.buy(value)) {
                var products = merchant.getProducts();
                Product buyedProduct = null;

                for (var product : products) {
                    if (product.getId() == productId) {
                        buyedProduct = product;
                    }
                }

                if (buyedProduct != null) {
                    if (buyedProduct.getQuantity() > quantitity) System.out.println("Quantidade maior que no estoque!");
                    //else {
                    // ProductController.sell(buyedProduct, quantitity);
                    // merchant.sell(value);
                    // System.out.println("Compra realizada com sucesso");
                    // }
                } else {
                    System.out.println("Produto não encontrado!");
                }
            }
        }
    }

    private User findUserById(int id) {
        for (var user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private User findUserByUserNameAndPassword(String userName, String password) {
        User userFound = null;
        for (var user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                userFound = user;
            }
        }
        return userFound;
    }
}
