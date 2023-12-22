package user.model;

public class Buyer extends User{

    public Buyer(int id, int type, String fullName, String userName, String password, double balance) {
        super(id, type, fullName, userName, password, balance);
    }

    @Override
    public boolean buy(double value) {
        if(this.getBalance() < value) {
            System.out.println("\nSaldo Insuficiente!");
            return false;
        }
        this.setBalance(this.getBalance() - value);
        return true;
    }
}
