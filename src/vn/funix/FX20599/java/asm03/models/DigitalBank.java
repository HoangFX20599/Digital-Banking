package vn.funix.FX20599.java.asm03.models;

import vn.funix.FX20599.java.asm02.models.Account;
import vn.funix.FX20599.java.asm02.models.Bank;
import vn.funix.FX20599.java.asm02.models.Customer;

public class DigitalBank extends Bank {
//    private Map<String, Customer> customers; // Lưu danh sách khách hàng

    public DigitalBank() {
        super(); // Gọi constructor của lớp cha (Bank)
//        this.customers = new HashMap<>();
    }




//    ############### Cac phuong thuc phu tro



    // Thêm khách hàng vào ngân hàng
    @Override
    public void addCustomer(String name, String customerId) {

        // Tạo một đối tượng DigitalCustomer thay vì Customer
        Customer newCustomer = new DigitalCustomer(name, customerId);

        // Gọi phương thức setCustomerId của User để thiết lập CCCD cho khách hàng mới
        newCustomer.setCustomerId(customerId);

        // Thêm khách hàng vào danh sách khách hàng của ngân hàng
        getCustomers().add(newCustomer);

    }

    // Thêm tài khoản vào ngân hàng
    @Override
    public void addAccount(String customerId, Account newAccount) {
        super.addAccount(customerId, newAccount);

    }

    /**
     * Phương thức để thêm một tài khoản mới vào tập hợp chứa tất cả số tài khoản của ngân hàng.
     *
     * @param accountNumber Số tài khoản cần thêm
     */

    @Override
    public void addAccountToAllAccounts(String accountNumber) {
        super.addAccountToAllAccounts(accountNumber);
    }

    // Lấy thông tin khách hàng bằng ID

    public Customer getCustomerById(String customerId) {
       return super.getCustomerByCustomerId(customerId);
    }

    // Rút tiền từ tài khoản
    public boolean withdraw(String customerId, String accountNumber, double amount) {

        DigitalCustomer customer = (DigitalCustomer) getCustomerById(customerId);

        if (customer != null) {
        return customer.withdraw(accountNumber, amount);

        } else {
        return false;
        }
    }





}
