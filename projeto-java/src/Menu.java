import products.controller.ProductController;
import products.model.Product;
import user.controller.UserController;
import user.model.Buyer;
import user.model.Merchant;
import user.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        UserController users = new UserController();
        ProductController products = new ProductController();

        Scanner input = new Scanner(System.in);

        int id, type, receiverId, quantity;
        String fullName, userName, password, productName;
        double balance, price;
        char option;

        ArrayList<Product> testMerchantProducts = new ArrayList<>();
        Merchant testMerchant = new Merchant(users.generateId(), 2, "Polishop", "polishop", "123456", 1000, testMerchantProducts);
        Buyer testBuyer = new Buyer(users.generateId(), 1, "Giovanni Torelli", "t0relli", "123456", 1000);

        users.signUp(testMerchant);
        users.signUp(testBuyer);

        users.login("polishop", "123456");

        users.addProduct(new Product(products.generateId(), users.getLoggedUser().getId(), "Churrasqueira elétrica", 200, 4), products);

        while (true) {
            System.out.println("****************************************************************************************");
            System.out.println("                                                                                        ");
            System.out.println("                Java Shopping - Seu Shopping de confiança!                              ");
            System.out.println("                                                                                        ");
            System.out.println("          Você deve fazer login para utilizar nossa plataforma                          ");
            System.out.println("****************************************************************************************");
            System.out.println("                                                                                        ");
            System.out.println("                            1 - Criar Conta                                             ");
            System.out.println("                            2 - Fazer login                                  ");
            System.out.println("                            . - Sair                                                    ");
            System.out.println("                                                                                        ");
            System.out.println("****************************************************************************************");
            System.out.println("Entre com a opção desejada:                                                             ");
            System.out.println("                                                                                        ");

            option = input.next().charAt(0);

            if (option == '.') {
                System.out.println("Obrigado e volte sempre!");
                input.close();
                System.exit(0);
            }

            switch (option) {
                case '1':
                    System.out.println("\nCriar conta\n");

                    System.out.println("\nDigite seu nome completo: \n");
                    fullName = input.nextLine();

                    System.out.println("\nDigite seu nome de usuário: \n");
                    userName = input.nextLine();

                    System.out.println("\nDigite sua senha: \n");
                    password = input.nextLine();

                    do {
                        System.out.println("\nVocê será um comprador (1) ou um lojista (2)?\n");
                        type = input.nextInt();
                    } while (type < 1 && type > 2);

                    System.out.println("\nDigite o saldo da sua conta (R$): \n");
                    balance = input.nextDouble();

                    Buyer newUser = new Buyer(users.generateId(), 1, fullName, userName, password, balance);

                    users.signUp(newUser);

                    users.login(userName, password);

                    break;
                case '2':
                    input.nextLine();
                    System.out.println("\nFazer login\n");
                    System.out.println("\nDigite seu nome de usuário: \n");
                    userName = input.nextLine();

                    System.out.println("\nDigite sua senha: \n");
                    password = input.nextLine();

                    users.login(userName, password);

                    break;
                default:
                    System.out.println("Opção inválida!");
                    keyPress();
                    break;
            }

            Optional<User> optionalUser = Optional.ofNullable(users.getLoggedUser());


                while (true) {
                    if (optionalUser.isPresent()) {
                        if(users.getLoggedUser().getType() == 1) {
                            System.out.println("****************************************************************************************");
                            System.out.println("                                                                                        ");
                            System.out.println("                Java Shopping - Seu Shopping de confiança!                              ");
                            System.out.println("                                                                                        ");
                            System.out.println("                        Selecione uma das opções!                                       ");
                            System.out.println("****************************************************************************************");
                            System.out.println("                                                                                        ");
                            System.out.println("                            1 - Pesquisar lojas                                         ");
                            System.out.println("                            2 - Ver todos os produtos                                   ");
                            System.out.println("                            3 - Configurações de conta                                  ");
                            System.out.println("                            , - Deslogar                                                ");
                            System.out.println("                                                                                        ");
                            System.out.println("****************************************************************************************");
                            System.out.println("Entre com a opção desejada:                                                             ");
                            System.out.println("                                                                                        ");

                            option = input.next().charAt(0);

                            if (option == ',') {
                                System.out.println("Obrigado e volte sempre!");
                                users.logoff();
                                break;
                            }

                            switch (option) {
                                case '1':
                                    while (true) {
                                        System.out.println("Lojas disponíveis:\n");
                                        users.findAllMerchants();
                                        System.out.println("\nSelecione a loja que deseja visitar ou digite v para voltar");
                                        try {
                                            id = input.nextInt();
                                        } catch (InputMismatchException e) {
                                            break;
                                        }

                                        if (users.findUserById(id) != null) {
                                            while (true) {
                                                products.findAllByMerchant(id);
                                                receiverId = id;
                                                System.out.println("\nSelecione o produto para comprar ou digite v para voltar");
                                                try {
                                                    id = input.nextInt();
                                                } catch (InputMismatchException e) {
                                                    break;
                                                }
                                                System.out.println("\nDigite a quantidade: ");
                                                try {
                                                    quantity = input.nextInt();
                                                } catch (InputMismatchException e) {
                                                    System.out.println("\nDigite os valores inteiros!");
                                                    input.nextLine();
                                                    quantity = 0;
                                                }
                                                var product = products.searchAllProductsById(id);
                                                keyPress();
                                                users.transfer(products, users, users.getLoggedUser().getId(), receiverId, product.getPrice(), product.getId(), quantity);
                                                keyPress();
                                                break;
                                            }
                                            input.nextLine();
                                        } else {
                                            System.out.println("Loja não encontrada!");
                                        }

                                    }
                                    input.nextLine();
                                    break;

                                case '2':
                                    while (true) {
                                        System.out.println("Visualizar todos produtos:\n");
                                        products.findAll(users);
                                        System.out.println("\nSelecione o produto para comprar ou digite v para voltar");
                                        try {
                                            id = input.nextInt();
                                        } catch (InputMismatchException e) {
                                            break;
                                        }
                                        var product = products.searchAllProductsById(id);
                                        receiverId = product.getMerchantId();

                                        System.out.println("\nDigite a quantidade: ");
                                        try {
                                            quantity = input.nextInt();
                                        } catch (InputMismatchException e) {
                                            System.out.println("\nDigite os valores inteiros!");
                                            input.nextLine();
                                            quantity = 0;
                                        }
                                        keyPress();
                                        users.transfer(products, users, users.getLoggedUser().getId(), receiverId, product.getPrice(), product.getId(), quantity);
                                        keyPress();
                                        break;
                                    }
                                    input.nextLine();
                                    break;
                                case '3':
                                    users.viewAccount();
                                    keyPress();
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                            }
                        }
                        if(users.getLoggedUser().getType() == 2) {
                            System.out.println("****************************************************************************************");
                            System.out.println("                                                                                        ");
                            System.out.println("                Java Shopping - Seu Shopping de confiança!                              ");
                            System.out.println("                                                                                        ");
                            System.out.println("                        Selecione uma das opções!                                       ");
                            System.out.println("****************************************************************************************");
                            System.out.println("                                                                                        ");
                            System.out.println("                            1 - Visualizar seus produtos                                ");
                            System.out.println("                            2 - Adicionar produtos                                      ");
                            System.out.println("                            3 - Configurações de conta                                  ");
                            System.out.println("                            , - Deslogar                                                ");
                            System.out.println("                                                                                        ");
                            System.out.println("****************************************************************************************");
                            System.out.println("Entre com a opção desejada:                                                             ");
                            System.out.println("                                                                                        ");

                            option = input.next().charAt(0);

                            if (option == ',') {
                                System.out.println("Obrigado e volte sempre!");
                                users.logoff();
                                break;
                            }

                            switch (option) {
                                case '1':
                                    while (true) {
                                        System.out.println("Meus produtos:\n");
                                        System.out.println("\nSelecione um produto para alterar ou digite v para voltar\n");
                                        products.findAllByMerchant(users.getLoggedUser().getId());
                                        try {
                                            id = input.nextInt();
                                        } catch (InputMismatchException e) {
                                            break;
                                        }

                                        if (products.searchAllProductsById(id) != null) {
                                            var product = products.searchAllProductsById(id);
                                            while (true) {
                                                System.out.println("\nProduto a ser editado: \n[\n Nome: " + product.getName() + ",\n Preço: " + product.getPrice() + ",\n Quantidade: " + product.getQuantity() + "\n]\n");
                                                System.out.println("\nSelecione o que deseja alterar nome (1), preço (2), quantidade (3), deletar produto (4) ou pressione v para voltar");

                                                productName = product.getName();
                                                price = product.getPrice();
                                                quantity = product.getQuantity();

                                                option = input.next().charAt(0);

                                                if(option == 'v') break;

                                                switch (option) {
                                                    case '1':
                                                        System.out.println("\nDigite o novo nome do produto\n");
                                                        input.nextLine();
                                                        try {
                                                            productName = input.nextLine();
                                                        } catch (Exception e) {
                                                            System.out.println("\nValor digitado inválido!\n");
                                                            input.nextLine();
                                                            productName = "";
                                                        }

                                                        System.out.println("\nDeseja continuar editando? (s/n)\n");
                                                        option = input.next().charAt(0);
                                                        if(option == 'n') break;
                                                    case '2':
                                                        System.out.println("\nDigite o novo preço: \n");
                                                        try {
                                                            price = input.nextDouble();
                                                        } catch (Exception e) {
                                                            System.out.println("\nValor digitado inválido!\n");
                                                            input.nextLine();
                                                            price = 0;
                                                        }

                                                        System.out.println("\nDeseja continuar editando? (s/n)\n");
                                                        option = input.next().charAt(0);
                                                        if(option == 'n') break;
                                                    case '3':
                                                        System.out.println("\nDigite a quantidade: \n");
                                                        try {
                                                            quantity = input.nextInt();
                                                        } catch (Exception e) {
                                                            System.out.println("\nValor digitado inválido!\n");
                                                            input.nextLine();
                                                            quantity = 0;
                                                        }
                                                        break;
                                                    case '4':
                                                        System.out.println("Tem certeza que deseja deletar o produto selecionado? (s/n)");
                                                        option = input.next().charAt(0);

                                                        if(option == 's') {
                                                            products.deleteById(id);
                                                            keyPress();
                                                            break;
                                                        } else {
                                                            break;
                                                        }
                                                    default:
                                                        System.out.println("Opção inválida!");
                                                }

                                                product = products.searchAllProductsById(id);

                                                if (product != null) {
                                                    Product updatedProduct = new Product(product.getId(), users.getLoggedUser().getId(),productName, price, quantity);

                                                    products.updateProduct(updatedProduct);
                                                    keyPress();
                                                    break;
                                                }

                                                break;
                                            }
                                            input.nextLine();
                                        } else {
                                            System.out.println("Produto não encontrado!");
                                        }

                                    }
                                    input.nextLine();
                                    break;

                                case '2':
                                    while (true) {
                                        System.out.println("Adicionar novo produto:\n");
                                        System.out.println("\nDigite o nome do produto\n");
                                        input.nextLine();
                                        try {
                                            productName = input.nextLine();
                                        } catch (Exception e) {
                                            System.out.println("\nValor digitado inválido!\n");
                                            input.nextLine();
                                            productName = "";
                                        }

                                        System.out.println("\nDigite o preço: \n");
                                        try {
                                            price = input.nextDouble();
                                        } catch (Exception e) {
                                            System.out.println("\nValor digitado inválido!\n");
                                            input.nextLine();
                                            price = 0;
                                        }

                                        System.out.println("\nDigite a quantidade: \n");
                                        try {
                                            quantity = input.nextInt();
                                        } catch (Exception e) {
                                            System.out.println("\nValor digitado inválido!\n");
                                            input.nextLine();
                                            quantity = 0;
                                        }

                                        Product newProduct = new Product(products.generateId(), users.getLoggedUser().getId(), productName, price, quantity);

                                        users.addProduct(newProduct, products);

                                        keyPress();
                                        break;
                                    }
                                    input.nextLine();
                                    break;
                                case '3':
                                    users.viewAccount();
                                    keyPress();
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                            }
                        }
                    }

                }

        }

    }

    public static void keyPress() {
        try {
            System.out.println("\nPressione Enter para continuar...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("Você pressionou uma tecla diferente de enter!");
        }
    }

}
