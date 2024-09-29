package vn.funix.FX20599.java.asm03.models;

import vn.funix.FX20599.java.asm02.models.Account;
import vn.funix.FX20599.java.asm03.ReportService;
import vn.funix.FX20599.java.asm03.Withdraw;

public class LoanAccount extends Account implements Withdraw, ReportService {
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100_000_000.0; // Hạn mức tài khoản
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05; // Phí rút tiền cho tài khoản thường
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01; // Phí rút tiền cho tài khoản Premium

    public LoanAccount(String accountNumber, double balance) {
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

        if (amount > balance - 50000) {
            System.out.println("So tien vuot qua so du hien tai cua tai khoan.");
            addTransaction(new Transaction(getAccountNumber(), -amount, false));
            return false;
        }

        double transactionFee = getFee(amount);
        balance = balance - amount - transactionFee;
//        System.out.println("Rút tiền thành công.");
        log(amount);
//        addTransaction(new Transaction(getAccountNumber(), balance, true));
        addTransaction(new Transaction(getAccountNumber(), -amount, true));
        return true;
    }

    @Override
    public boolean isAccepted(double amount) {
        return amount <= balance - 50_000 && amount <= LOAN_ACCOUNT_MAX_BALANCE;
    }

//    @Override
    public double getFee(double amount) {
        return isPremiumAccount() ? amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : amount * LOAN_ACCOUNT_WITHDRAW_FEE;
    }

    @Override
    public void log(double amount) {
        double fee = isPremiumAccount() ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE;
        double totalFee = amount * fee;

        System.out.println(Utils.getDivider());
        System.out.printf("|           %-35s |%n", "BIEN LAI GIAO DICH LOAN");
        System.out.printf("| NGAY G/D: %35s |%n", Utils.getDateTime());
        System.out.printf("| ATM ID: %37s |%n", "DIGITAL-BANK-ATM 2024");
        System.out.printf("| SO TK: %38s |%n", getAccountNumber());
        System.out.printf("| SO TIEN: %36s |%n", Utils.formatBalance(amount));
        System.out.printf("| SO DU: %38s |%n", Utils.formatBalance(getBalance()));
        System.out.printf("| PHI + VAT: %34s |%n", Utils.formatBalance(totalFee));
        System.out.println(Utils.getDivider());
    }


    @Override
    public String getAccountType() {
        return "LOAN";
    }
}
