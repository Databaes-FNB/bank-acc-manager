import java.util.Random;

public class ChequeAccount extends Account {
    private double overdraftLimit;


    public ChequeAccount(String accountHolder, double initialBalance, double overdraftLimit) {
        this.setAccountHolder(accountHolder);
        this.setBalance(initialBalance);
        this.setAccountNumber(accountGenerator());
        this.overdraftLimit = overdraftLimit;
    }


    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }


    @Override
    public void withdraw(double amount) throws Exception {
        double availableBalance = getBalance() + overdraftLimit;
        if (amount > availableBalance) {
            throw new Exception("Insufficient funds, overdraft limit exceeded.");
        } else if (amount > 0) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn: " + amount);
            System.out.println("New balance: " + getBalance());
        } else {
            System.out.println("Invalid withdraw amount.");
        }
    }


    public void issueCheque(double amount) throws Exception {
        double availableBalance = getBalance() + overdraftLimit;
        if (amount > availableBalance) {
            throw new Exception("Insufficient funds for cheque, overdraft limit exceeded.");
        } else {
            setBalance(getBalance() - amount);
            System.out.println("Cheque issued for: " + amount);
            System.out.println("New balance: " + getBalance());
        }
    }


    @Override
    public String accountGenerator(){
        Random rand = new Random();
        long accountNumber;
        accountNumber = 1000000l + rand.nextLong(9000000);
        return String.valueOf(accountNumber);
    }
}
