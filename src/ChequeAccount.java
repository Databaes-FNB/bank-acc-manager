public class ChequeAccount extends Account {
    private static final double DEFAULT_OVERDRAFT_LIMIT = 5000.0;
    private double overdraftLimit;

    // Constructor for ChequeAccount with balance and overdraft limit
    public ChequeAccount(double balance, double overdraftLimit) {
        super(balance); // Call the parent constructor to set the balance
        this.overdraftLimit = overdraftLimit;
    }

    // Getter and setter for overdraftLimit
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    // Deposit and withdraw methods
    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        setBalance(getBalance() + amount); // Update the balance
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        double availableBalance = getBalance() + overdraftLimit;
        if (amount > availableBalance) {
            throw new IllegalArgumentException("Insufficient funds including overdraft limit.");
        }
        setBalance(getBalance() - amount); // Update the balance
    }

    // You can add more methods specific to ChequeAccount here if needed
}
