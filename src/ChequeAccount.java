import java.util.Random;

public class ChequeAccount extends Account {

    private static final double INTEREST_RATE = 0.03;  // Interest rate on withdrawals after the free limit
    private static final double MONTHLY_FEE = 50.0;  // Example monthly account fee
    private long balance;
    private double overdraftLimit;
    private int freeWithdrawals = 4;  // Free withdrawals per month

    // Constructor
    public ChequeAccount(double initialBalance, double overdraftLimit) {
        this.balance = (long) initialBalance;
        this.setBalance(initialBalance);
        this.setAccountNumber(accountGenerator());
        this.overdraftLimit = overdraftLimit;
    }

    // Withdraw method with overdraft check
    @Override
    public void withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be greater than zero.");
        }
        double availableBalance = balance + overdraftLimit;
        if (amount > availableBalance) {
            throw new Exception("Insufficient funds, overdraft limit exceeded.");
        } else {
            balance -= amount;
            setBalance(balance); // Update balance in Account class
            System.out.println("Withdrawn: " + amount);
            System.out.println("New balance: " + getBalance());
        }
    }

    // Issue cheque method with overdraft check
    public void issueCheque(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be greater than zero.");
        }
        double availableBalance = balance + overdraftLimit;
        if (amount > availableBalance) {
            throw new Exception("Insufficient funds for cheque, overdraft limit exceeded.");
        } else {
            balance -= amount;
            setBalance(balance); // Update balance in Account class
            System.out.println("Cheque issued for: " + amount);
            System.out.println("New balance: " + getBalance());
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;  // Reject negative or zero deposit amounts
        }
        balance += amount;
        setBalance(balance); // Update balance in Account class
        System.out.println("Deposited: " + amount);
        System.out.println("New balance: " + getBalance());
    }




    @Override
    public void transfer(Account accType, double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be greater than zero.");
        }
        if (this.balance >= amount) {
            this.withdraw(amount);
            accType.deposit(amount);
            System.out.println("Transferred " + amount + " to " + accType.getAccountHolder());
        } else {
            throw new Exception("Insufficient funds to transfer.");
        }
    }

    // Account number generator
    @Override
    public String accountGenerator() {
        Random rand = new Random();
        long accountNumber = 1000000l + rand.nextLong(9000000);
        return String.valueOf(accountNumber);
    }
}
