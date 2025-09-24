class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected String accountHolderName;
    
    public BankAccount(String accountNumber, double balance, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolderName = accountHolderName;
    }
    
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
        System.out.println("New Balance: $" + balance);
    }
    
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
    
    public void displayAccountInfo() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;
    private double minimumBalance;
    
    public SavingsAccount(String accountNumber, double balance, String accountHolderName,
                         double interestRate, double minimumBalance) {
        super(accountNumber, balance, accountHolderName);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }
    
    public void displayAccountType() {
        System.out.println("Account Type: Savings Account");
    }
    
    public void applyInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest applied: $" + interest);
        System.out.println("New Balance after interest: $" + balance);
    }
    
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        displayAccountType();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Minimum Balance: $" + minimumBalance);
    }
}

class CheckingAccount extends BankAccount {
    private double withdrawalLimit;
    private boolean overdraftProtection;
    
    public CheckingAccount(String accountNumber, double balance, String accountHolderName,
                          double withdrawalLimit, boolean overdraftProtection) {
        super(accountNumber, balance, accountHolderName);
        this.withdrawalLimit = withdrawalLimit;
        this.overdraftProtection = overdraftProtection;
    }
    
    public void displayAccountType() {
        System.out.println("Account Type: Checking Account");
    }
    
    @Override
    public void withdraw(double amount) {
        if (amount > withdrawalLimit) {
            System.out.println("Withdrawal amount exceeds daily limit of $" + withdrawalLimit);
        } else if (amount <= balance) {
            super.withdraw(amount);
        } else if (overdraftProtection) {
            System.out.println("Overdraft protection activated!");
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            System.out.println("New Balance: -$" + Math.abs(balance));
        } else {
            System.out.println("Insufficient funds!");
        }
    }
    
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        displayAccountType();
        System.out.println("Daily Withdrawal Limit: $" + withdrawalLimit);
        System.out.println("Overdraft Protection: " + (overdraftProtection ? "Enabled" : "Disabled"));
    }
}

class FixedDepositAccount extends BankAccount {
    private int termMonths;
    private double fixedInterestRate;
    private String maturityDate;
    
    public FixedDepositAccount(String accountNumber, double balance, String accountHolderName,
                              int termMonths, double fixedInterestRate, String maturityDate) {
        super(accountNumber, balance, accountHolderName);
        this.termMonths = termMonths;
        this.fixedInterestRate = fixedInterestRate;
        this.maturityDate = maturityDate;
    }
    
    public void displayAccountType() {
        System.out.println("Account Type: Fixed Deposit Account");
    }
    
    @Override
    public void withdraw(double amount) {
        System.out.println("Cannot withdraw from Fixed Deposit Account before maturity date!");
        System.out.println("Maturity Date: " + maturityDate);
    }
    
    public void calculateMaturityAmount() {
        double maturityAmount = balance * Math.pow(1 + fixedInterestRate/100, termMonths/12.0);
        System.out.println("Maturity Amount: $" + String.format("%.2f", maturityAmount));
    }
    
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        displayAccountType();
        System.out.println("Term: " + termMonths + " months");
        System.out.println("Fixed Interest Rate: " + fixedInterestRate + "%");
        System.out.println("Maturity Date: " + maturityDate);
        calculateMaturityAmount();
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("SAV001", 5000, "Alice Johnson", 2.5, 100);
        CheckingAccount checking = new CheckingAccount("CHK001", 2500, "Bob Smith", 1000, true);
        FixedDepositAccount fixedDeposit = new FixedDepositAccount("FD001", 10000, "Carol Davis", 12, 5.0, "2025-01-15");
        
        System.out.println("=== Savings Account ===");
        savings.displayAccountInfo();
        savings.deposit(500);
        savings.applyInterest();
        System.out.println();
        
        System.out.println("=== Checking Account ===");
        checking.displayAccountInfo();
        checking.withdraw(3000);
        checking.withdraw(800);
        System.out.println();
        
        System.out.println("=== Fixed Deposit Account ===");
        fixedDeposit.displayAccountInfo();
        fixedDeposit.withdraw(1000);
        System.out.println();
        
        System.out.println("=== Account Type Summary ===");
        BankAccount[] accounts = {savings, checking, fixedDeposit};
        
        for (BankAccount account : accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).displayAccountType();
            } else if (account instanceof CheckingAccount) {
                ((CheckingAccount) account).displayAccountType();
            } else if (account instanceof FixedDepositAccount) {
                ((FixedDepositAccount) account).displayAccountType();
            }
        }
    }
}