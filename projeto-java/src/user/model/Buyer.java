package user.model;

public class Buyer extends User{

    public Buyer(int id, int type, String fullName, String userName, String password, double balance) {
        super(id, type, fullName, userName, password, balance);
    }

    public boolean buy(float value) {
        if(this.getBalance() < value) {
            System.out.println("\nSaldo Insuficiente!");
            return false;
        }

        this.setBalance(this.getBalance() - value);
        return true;
    }
}
