/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Random;

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

    /**
     *
     * @param amount
     * @throws Exception
     */
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
    public String accountGenerator(){
        Random rand = new Random();
        long accountNumber;
        accountNumber = 1000000l + rand.nextLong(9000000);
        return String.valueOf(accountNumber);
    }
    public void transfer(Account accType, double amount) throws Exception
    {
        if (amount > 0) {
            if (this.balance >= amount) {
                this.withdraw(amount);
                accType.deposit(amount);
                System.out.println("Transferred " + amount + " to " + accType.getAccountHolder());
            } else {
                throw new Exception("Insufficient funds to transfer.");
            }
        } else {
            System.out.println("Invalid transfer amount.");

    }

    }
}
