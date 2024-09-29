package vn.funix.FX20599.java.asm03.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final String accountNumber;
    private final double amount;
    private final String time;
    private final boolean status;

    public Transaction(String accountNumber, double amount, boolean status) {
        this.id = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("[GD] %12s | %22s | %25s | %s",
                accountNumber,
                String.format("%,.2f đ", amount),
                time,
                id);
    }
}
