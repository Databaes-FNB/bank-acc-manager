/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccountmanager;

/**
 *
 * @author Capaciti
 */
public class Account {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            System.out.println("You have deposited: " + amount);
            System.out.println("New balance: " + getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) throws Exception {
        if (amount > getBalance()) {
            throw new Exception("Insufficient funds.");
        } else if (amount > 0) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn: " + amount);
            System.out.println("New balance: " + getBalance());
        } else {
            System.out.println("Invalid withdraw amount.");
        }
    }
}
