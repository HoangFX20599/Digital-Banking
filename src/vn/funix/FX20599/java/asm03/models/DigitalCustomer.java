package vn.funix.FX20599.java.asm03.models;

import vn.funix.FX20599.java.asm02.models.Account;
import vn.funix.FX20599.java.asm02.models.Customer;
import vn.funix.FX20599.java.asm03.Withdraw;

public class DigitalCustomer extends Customer {

    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }

    // Override phương thức displayInformation để hiển thị thông tin khách hàng
    @Override
    public void displayInformation() {
        // Hiển thị thông tin khách hàng (CCCD, Tên, Loại) và tổng số tiền
        System.out.printf(
                "%-17s | %20s | %10s | %15s\n",
                getCustomerId(),
                getName(),
                isPremiumAccount() ? "Premium" : "Normal",
                String.format("%,.0fđ", getBalance())
        );

        // Hiển thị danh sách các tài khoản mà khách hàng sở hữu
        int index = 1;
        for (Account account : getAccounts()) {
            System.out.printf(
                    "%-4d %12s | %20s | %28s\n",
                    index++,
                    account.getAccountNumber(),
                    account.getAccountType(),
                    String.format("%,dđ", Math.round(account.getBalance()))
            );
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer ID: ").append(getCustomerId()).append("\n");
        builder.append("Customer Name: ").append(getName()).append("\n");
        builder.append("Accounts: \n");
        for (Account account : getAccounts()) {
            builder.append(account.toString()).append("\n");
        }
        return builder.toString();
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        return ((Withdraw) account).withdraw(amount);

    }

    public Account getAccount(String accountNumber) {
        for (Account account : getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
