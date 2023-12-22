package user.controller;

import products.controller.ProductController;
import products.model.Product;
import user.model.User;
import user.repository.UserRepository;

import java.util.ArrayList;

public class UserController implements UserRepository {

    private ArrayList<User> users = new ArrayList<>();
    private User loggedUser = null;

    int newId = 0;


    public User getLoggedUser() {
        return loggedUser;
    }

    @Override
    public void signUp(User user) {
        users.add(user);
        System.out.println("Sua conta foi criada com sucesso!");
    }

    @Override
    public void login(String userName, String password) {
        var user = findUserByUserNameAndPassword(userName, password);

        if (user != null) {
            loggedUser = user;
            System.out.println("Olá, " + user.getFullName() + "!");
        } else System.out.println("Seu nome de usuário ou senha estão incorretos");
    }

    @Override
    public void viewAccount() {
        if (loggedUser != null) loggedUser.displayUserInfo();
        else System.out.println("Você deve estar logado para fazer isso!");
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
    public void transfer(ProductController productController, UserController userController, int senderId, int receiverId, double value, int productId, int quantitity) {
        var buyer = findUserById(senderId);
        var merchant = findUserById(receiverId);

        if (buyer != null && merchant != null) {

            var products = merchant.getProducts();
            Product buyedProduct = null;

            for (var product : products) {
                if (product.getId() == productId) {
                    buyedProduct = product;
                }
            }

            if (buyedProduct != null) {
                if (buyedProduct.getQuantity() < quantitity) System.out.println("\nQuantidade maior que no estoque!\n");
                else {
                    buyer.buy(value * quantitity);
                    productController.sell(userController, receiverId, buyedProduct, quantitity);
                    merchant.sell(value * quantitity);
                    System.out.println("\nCompra realizada com sucesso\n");
                }
            } else {
                System.out.println("\nProduto não encontrado!\n");
            }

        }
    }

    public void addProduct(Product product, ProductController productController) {
        if (loggedUser.getType() == 2) {
            var products = loggedUser.getProducts();
            products.add(product);
            productController.addProduct(product);
            loggedUser.setProducts(products);
        } else {
            System.out.println("Você deve ser um lojista para adicionar um produto!");
        }
    }

    public User findUserById(int id) {
        for (var user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void findAllMerchants() {
        for(var user : users) {
            if (user.getType() == 2) {
                System.out.println(user.getId() + " - " + user.getFullName());
            }
        }
    }

    public void logoff() {
        loggedUser = null;
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

    public int generateId() {
        return ++newId;
    }
}
