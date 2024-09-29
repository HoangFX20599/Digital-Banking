package vn.funix.FX20599.java.asm03.models;

import vn.funix.FX20599.java.asm02.models.Account;
import vn.funix.FX20599.java.asm03.ReportService;
import vn.funix.FX20599.java.asm03.Withdraw;

public class SavingsAccount extends Account implements Withdraw, ReportService {
    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000.0; // Số tiền tối đa có thể rút tk thường

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        addTransaction(new Transaction(getAccountNumber(), balance, true));
    }

    @Override
    public boolean withdraw(double amount) {
        if (!isAccepted(amount)) {
            System.out.println("So tien khong hop le hoac vuot qua gioi han cho phep.");
            addTransaction(new Transaction(getAccountNumber(), -amount, false));
            return false;
        }

        if (amount % 10_000 != 0) {
            System.out.println("So tien rut phai la boi so cua 10,000đ.");
            addTransaction(new Transaction(getAccountNumber(), -amount, false));
            return false;
        }

        if (amount > balance - 50_000) {
            System.out.println("So tien vuot qua so du hien tai cua tai khoan.");
            addTransaction(new Transaction(getAccountNumber(), -amount, false));
            return false;
        }

        balance -= amount;
        log(amount);
        addTransaction(new Transaction(getAccountNumber(), -amount, true));
        addTransaction(new Transaction(getAccountNumber(), balance, true));
        return true;
    }



    @Override
    public boolean isAccepted(double amount) {
        if (!isPremiumAccount()) {
        return amount >= 50_000 && amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW;
        } else {
            return amount >= 50_000;
        }
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("|           %-35s |%n", "BIEN LAI GIAO DICH SAVINGS");
        System.out.printf("| NGAY G/D: %35s |%n", Utils.getDateTime());
        System.out.printf("| ATM ID: %37s |%n", "DIGITAL-BANK-ATM 2024");
        System.out.printf("| SO TK: %38s |%n", getAccountNumber());
        System.out.printf("| SO TIEN: %36s |%n", Utils.formatBalance(amount));
        System.out.printf("| SO DU: %38s |%n", Utils.formatBalance(getBalance()));
        System.out.printf("| PHI + VAT: %34s |%n", Utils.formatBalance(0));
        System.out.println(Utils.getDivider());
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }
}
