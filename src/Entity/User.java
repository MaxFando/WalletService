package Entity;

public class User {
    private String username;
    private String password;
    private Wallet wallet;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet = new Wallet();
    }

    public void addTransaction(Transaction transaction) {
        this.wallet.addTransaction(transaction);
    }

    public void addCategory(Category category) {
        this.wallet.addCategory(category);
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public String getUsername() {
        return this.username;
    }

    public Boolean passwordIsEqual(String password) {
        return this.password.equals(password);
    }
}
