# Bank Account Management System

## Table of Contents
- [Project Description](#project-description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [How to Run the Project](#how-to-run-the-project)
- [Class Structure](#class-structure)
- [Usage Instructions](#usage-instructions)
- [Sample Outputs](#sample-outputs)
- [Future Enhancements](#future-enhancements)
- [Contributors](#contributors)

---

## Project Description
The **Bank Account Management System** is a Java-based console application that allows users to manage both **Savings Accounts** and **Cheque Accounts**. Users can perform operations such as depositing funds, withdrawing funds, issuing cheques, applying interest, viewing transaction history, and displaying account details.

The system demonstrates key programming concepts such as object-oriented design, inheritance, encapsulation, and user interaction through the command-line interface.

---

## Features
### General Features:
- Account creation for **Savings** and **Cheque** accounts.
- Automatically generated account numbers.
- Balance management with deposit and withdrawal functionality.

### **Savings Account Features**:
- Applies a fixed interest rate on the balance.
- Maintains a transaction history.
- Enforces withdrawal limits.

### **Cheque Account Features**:
- Allows for overdrafts with a configurable limit.
- Tracks free withdrawals and applies interest after exceeding the limit.
- Enables cheque issuance with overdraft checks.

---

## Technologies Used
- **Programming Language**: Java
- **Development Tools**: IDE (IntelliJ IDEA, Eclipse, etc.)
- **Concepts Utilized**:
  - Object-Oriented Programming (OOP)
  - Encapsulation, Inheritance, Polymorphism
  - User Input with `Scanner`
  - Error Handling with `try-catch`

---

## How to Run the Project
### Prerequisites
- Install Java Development Kit (JDK) 8 or later.
- Install an IDE such as IntelliJ IDEA, Eclipse, or use a text editor and compile via terminal.

### Steps to Run
1. Clone the project repository or copy the source files into your workspace.
2. Open the project in your IDE or navigate to the project directory via terminal.
3. Compile all `.java` files:
   ```bash
   javac Main.java SavingsAccount.java ChequeAccount.java Account.java

Run the Main class:
bash
Copy code
java Main


markdown
Copy code
# Bank Account Management System

## Table of Contents
- [Project Description](#project-description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [How to Run the Project](#how-to-run-the-project)
- [Class Structure](#class-structure)
- [Usage Instructions](#usage-instructions)
- [Sample Outputs](#sample-outputs)
- [Future Enhancements](#future-enhancements)
- [Contributors](#contributors)

---

## Project Description
The **Bank Account Management System** is a Java-based console application that allows users to manage both **Savings Accounts** and **Cheque Accounts**. Users can perform operations such as depositing funds, withdrawing funds, issuing cheques, applying interest, viewing transaction history, and displaying account details.

The system demonstrates key programming concepts such as object-oriented design, inheritance, encapsulation, and user interaction through the command-line interface.

---

## Features
### General Features:
- Account creation for **Savings** and **Cheque** accounts.
- Automatically generated account numbers.
- Balance management with deposit and withdrawal functionality.

### **Savings Account Features**:
- Applies a fixed interest rate on the balance.
- Maintains a transaction history.
- Enforces withdrawal limits.

### **Cheque Account Features**:
- Allows for overdrafts with a configurable limit.
- Tracks free withdrawals and applies interest after exceeding the limit.
- Enables cheque issuance with overdraft checks.

---

## Technologies Used
- **Programming Language**: Java
- **Development Tools**: IDE (IntelliJ IDEA, Eclipse, etc.)
- **Concepts Utilized**:
  - Object-Oriented Programming (OOP)
  - Encapsulation, Inheritance, Polymorphism
  - User Input with `Scanner`
  - Error Handling with `try-catch`

---

## How to Run the Project
### Prerequisites
- Install Java Development Kit (JDK) 8 or later.
- Install an IDE such as IntelliJ IDEA, Eclipse, or use a text editor and compile via terminal.

### Steps to Run
1. Clone the project repository or copy the source files into your workspace.
2. Open the project in your IDE or navigate to the project directory via terminal.
3. Compile all `.java` files:
   ```bash
   javac Main.java SavingsAccount.java ChequeAccount.java Account.java
Run the Main class:
bash
Copy code
java Main
Class Structure
Account Class (Base Class):

Properties: accountHolder, accountNumber, balance
Methods: accountGenerator, getters and setters
SavingsAccount Class (Inherits Account):

Additional Features: applyInterest, printTransactionHistory
Properties: transactionHistory
ChequeAccount Class (Inherits Account):

Additional Features: issueCheque, withdrawal with overdraft handling
Properties: overdraftLimit, freeWithdrawals
Main Class:

Contains the user interface for interacting with accounts.
Usage Instructions
Savings Account Operations:

Follow the on-screen menu to perform actions:
Deposit Funds: Enter an amount to deposit into the account.
Withdraw Funds: Enter an amount to withdraw; ensures sufficient balance.
Apply Interest: Adds a fixed percentage of interest to the balance.
View Transaction History: Displays all past transactions.
Cheque Account Operations:

Use the dedicated options to:
Issue cheques and verify overdraft.
Track free withdrawals and apply interest if limits are exceeded.
Display account details such as balance and overdraft limit.
Exit the system by selecting the appropriate menu option.


https://github.com/user-attachments/assets/24bb27ca-1334-4487-932d-ba850fc46379


