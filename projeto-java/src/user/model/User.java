package user.model;

public abstract class User {

    private int id;
    private int type;
    private String fullName;
    private String userName;
    private String password;
    private double balance;

    public User(int id, int type, String fullName, String userName, String password, double balance) {
        this.id = id;
        this.type = type;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void displayUserInfo() {

        String tipo = switch (this.type) {
            case 1 -> "Conta Cliente";
            case 2 -> "Conta Lojista";
            default -> "";
        };

        System.out.println("\n\n***********************************************************");
        System.out.println("Dados da Conta:");
        System.out.println("***********************************************************");
        System.out.println("ID da Conta: " + this.id);
        System.out.println("Nome Completo: " + this.fullName);
        System.out.println("Tipo da Conta: " + tipo);
        System.out.println("Nome de usuário: " + this.userName);
        System.out.println("Saldo: " + this.balance);
    }
}
