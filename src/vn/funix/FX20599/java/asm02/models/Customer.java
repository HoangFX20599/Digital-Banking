package vn.funix.FX20599.java.asm02.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class đại diện cho một khách hàng trong hệ thống ngân hàng.
 * Kế thừa từ lớp User để kế thừa các thuộc tính và phương thức chung.
 */
public class Customer extends User {
    private List<Account> accounts; // Danh sách các tài khoản của khách hàng

    /**
     * Constructor khởi tạo cho lớp Customer.
     *
     * @param name       Tên của khách hàng
     * @param customerId Mã CCCD của khách hàng
     */
    public Customer(String name, String customerId) {
        super(name, customerId); // Gọi constructor của lớp cha (User) để khởi tạo tên và mã CCCD
        this.accounts = new ArrayList<>(); // Khởi tạo danh sách tài khoản
    }

    /**
     * Getter cho danh sách tài khoản của khách hàng.
     *
     * @return Danh sách các tài khoản của khách hàng
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Phương thức kiểm tra xem tài khoản có tồn tại trong danh sách của khách hàng không.
     * Tạm để đây vì đã tạo một Set allAccount để lưu trữ toàn bộ account của ngân hàng rồi,
     *
     * @param accountNumber Số tài khoản cần kiểm tra
     * @return true nếu tài khoản tồn tại, ngược lại trả về false
     */
    public boolean isAccountExisted(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Phương thức kiểm tra xem khách hàng có phải là khách hàng cao cấp (premium) hay không.
     *
     * @return true nếu khách hàng là premium, ngược lại trả về false
     */
    public boolean isPremiumAccount() {
        // Duyệt qua danh sách các tài khoản và kiểm tra từng tài khoản
        for (Account account : accounts) {
            if (account.isPremiumAccount()) { // Nếu tài khoản nào là premium, trả về true
                return true;
            }
        }
        return false; // Nếu không có tài khoản premium nào, trả về false
    }

    /**
     * Phương thức thêm một tài khoản mới vào danh sách tài khoản của khách hàng.
     *
     * @param newAccount Tài khoản mới cần thêm
     */
    public void addAccount(Account newAccount) {
        if (!accounts.contains(newAccount)) { // Kiểm tra xem tài khoản đã tồn tại trong danh sách chưa
            accounts.add(newAccount); // Nếu chưa, thêm tài khoản mới vào danh sách
        }
    }

    /**
     * Phương thức tính tổng số dư của khách hàng.
     *
     * @return Tổng số dư của khách hàng
     */
    public double getBalance() {
        double totalBalance = 0;
        for (Account account : accounts) {
            totalBalance += account.getBalance(); // Cộng tổng số dư của các tài khoản
        }
        return totalBalance;
    }

    /**
     * Phương thức hiển thị thông tin chi tiết của khách hàng và các tài khoản mà khách hàng sở hữu.
     */
    public void displayInformation() {
        // Hiển thị thông tin khách hàng (CCCD, Tên, Loại) và tổng số tiền
        System.out.printf(
                "%s | %-20s | %10s | %15s\n",
                getCustomerId(),
                getName(),
                isPremiumAccount() ? "Premium" : "Normal",
                String.format("%,.0fđ", getBalance())
        );

        // Hiển thị danh sách các tài khoản mà khách hàng sở hữu
        int index = 1;
        for (Account account : getAccounts()) {
            System.out.printf("%-4d %s\n", index++, account.toString());
        }
    }


//    ############ Asm03


//    public void withdraw(String accountNumber, double amount) {
//        Account account = getAccount(accountNumber);
//        if (account instanceof Withdraw) {
//            ((Withdraw) account).withdraw(amount);
//        } else {
//            System.out.println("Account not found or cannot withdraw from this account.");
//        }
//    }




}
