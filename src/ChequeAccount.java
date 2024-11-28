import java.util.Random;

public class ChequeAccount extends Account {

    private static final double INTEREST_RATE = 0.03;  // Interest rate on withdrawals after the free limit
    private static final double MONTHLY_FEE = 50.0;    // Example monthly account fee
    private static final double DEFAULT_OVERDRAFT_LIMIT = 5000.0; // Default overdraft limit
    private double overdraftLimit;
    private int freeWithdrawals = 4;  // Free withdrawals per month

    // Constructor
    public ChequeAccount(double initialBalance, double overdraftLimit) {
        setBalance(initialBalance);   // Use parent's balance field
        setAccountNumber(accountGenerator());
        this.overdraftLimit = overdraftLimit;
    }

    // Overloaded constructor with default overdraft limit
    public ChequeAccount(double initialBalance) {
        this(initialBalance, DEFAULT_OVERDRAFT_LIMIT);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    // Withdraw method with overdraft and validation checks
    @Override
    public void withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be greater than zero.");
        }
        super.withdraw(amount);  // Calling the superclass method to handle balance update
    }

    // Issue cheque method with validation and overdraft check
    public void issueCheque(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Cheque amount must be greater than zero.");
        }

        // Calculate available balance: current balance + overdraft limit
        double availableBalance = getBalance() + overdraftLimit;

        // Strictly check if the amount exceeds the available balance
        if (amount > availableBalance) {
            throw new Exception("Insufficient funds for cheque. Overdraft limit exceeded.");
        }

        // If amount is within balance and overdraft, deduct it
        setBalance(getBalance() - amount);

        // Confirm cheque issuance
        System.out.println("Cheque issued for: " + amount);
        System.out.println("New Balance: " + getBalance());
    }


    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        setBalance(getBalance() + amount);
        System.out.println("Deposited: " + amount);
        System.out.println("New balance: " + getBalance());
    }

    @Override
    public void transfer(Account accType, double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Transfer amount must be greater than zero.");
        }
        double availableBalance = getBalance();
        if (amount > availableBalance + overdraftLimit) {
            throw new Exception("Insufficient funds to transfer. Overdraft limit exceeded.");
        }
        this.withdraw(amount);       // Deduct from current account
        accType.deposit(amount);     // Deposit into target account
        System.out.println("Transferred " + amount + " to " + accType.getAccountHolder());
    }

    // Account number generator
    @Override
    public String accountGenerator() {
        Random rand = new Random();
        long accountNumber = 1000000L + rand.nextLong(9000000);
        return String.valueOf(accountNumber);
    }
}
