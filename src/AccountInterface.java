public interface AccountInterface {
    void deposit(double amount);
    void withdraw(double amount) throws Exception;
    String accountGenerator();
    void transfer(Account accType, double amount) throws Exception;
}
