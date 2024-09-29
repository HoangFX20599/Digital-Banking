package vn.funix.FX20599.java.asm02.models;

import vn.funix.FX20599.java.asm03.models.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Class đại diện cho một tài khoản ngân hàng.
 * Mỗi tài khoản có số tài khoản và số dư là các thuộc tính chung.
 */
public class Account {
    private String accountNumber; // Số tài khoản
    protected double balance; // Số dư trong tài khoản
    private List<Transaction> transactions;

    /**
     * Tạo một đối tượng Account mới với số tài khoản và số dư cho trước.
     *
     * @param accountNumber Số tài khoản
     * @param balance       Số dư trong tài khoản
     */
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // Getter và setter cho các thuộc tính: accountNumber, balance

    /**
     * Lấy số dư trong tài khoản.
     *
     * @return Số dư trong tài khoản
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Đặt số dư cho tài khoản.
     *
     * @param balance Số dư trong tài khoản
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Lấy số tài khoản.
     *
     * @return Số tài khoản
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Đặt số tài khoản.
     *
     * @param accountNumber Số tài khoản
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Các phương thức phụ trợ

    /**
     * Kiểm tra xem tài khoản có phải là tài khoản premium dựa trên số dư.
     *
     * @return true nếu tài khoản là premium, ngược lại là false
     */
    public boolean isPremiumAccount() {
        return balance >= 10_000_000;
    }

    /**
     * Phương thức toString() để xuất thông tin tài khoản theo định dạng
     * Trả về một chuỗi biểu diễn của tài khoản.
     *
     * @return Một chuỗi biểu diễn của tài khoản
     */
    @Override
    public String toString() {
        return String.format("%12s | %51s", accountNumber, String.format("%,.0fđ", balance));
    }


    public String getAccountType() {
        return null;
    };

}
