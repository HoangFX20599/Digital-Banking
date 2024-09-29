package vn.funix.FX20599.java.asm02.models;

import java.util.*;

/**
 * Class đại diện cho một ngân hàng.
 * Class này quản lý danh sách khách hàng và tài khoản của ngân hàng.
 */
public class Bank {
    public static final String AUTHOR = "FX20599";
    public static final String VERSION = "v2.0.0";
    private final String id;

    // Vì code mẫu đề cho dùng List để quản lý customers nên bài này dùng List
    private final List<Customer> customers;

    // Do chỉ cần lưu các chuỗi để mục đích kiểm tra sự tồn tại nên dùng Set
    private final Set<String> allAccounts;

    /**
     * Constructor khởi tạo một đối tượng Bank với danh sách khách hàng và tài khoản rỗng.
     */
    public Bank() {
        this.id = UUID.randomUUID().toString(); // Khởi tạo id ngẫu nhiên
        this.allAccounts = new HashSet<>();
        this.customers = new ArrayList<>();
    }

    /**
     * Phương thức để lấy danh sách tất cả khách hàng của ngân hàng.
     *
     * @return Danh sách khách hàng của ngân hàng
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    public Set<String> getAllAccounts() {
        return allAccounts;
    }



// Các phương thức phụ trong class Bank để giao tiếp với các class khác #############################

    /**
     * Phương thức để thêm một tài khoản mới vào tập hợp chứa tất cả số tài khoản của ngân hàng.
     *
     * @param accountNumber Số tài khoản cần thêm
     */
    public void addAccountToAllAccounts(String accountNumber) {
        allAccounts.add(accountNumber);
    }

    /**
     * Phương thức để thêm một tài khoản mới vào ngân hàng cho một khách hàng cụ thể.
     *
     * @param customerId Mã căn cước công dân của khách hàng
     * @param newAccount Tài khoản mới cần thêm
     */
    public void addAccount(String customerId, Account newAccount) {

        // Lấy thông tin khách hàng từ ngân hàng dựa trên mã CCCD
        Customer customer = getCustomerByCustomerId(customerId);

        // Sử dụng hàm addAccount của lớp Customer thêm tài khoản vào danh sách tài khoản của khách hàng
        customer.addAccount(newAccount);

        // Thêm tài khoản vào danh sách toàn bộ tài khoản của ngân hàng
        addAccountToAllAccounts(newAccount.getAccountNumber());
    }

    /**
     * Phương thức để thêm khách hàng mới vào ngân hàng.
     *
     * @param name       Tên của khách hàng mới
     * @param customerId Mã căn cước công dân của khách hàng mới
     */
    public void addCustomer(String name, String customerId) {

        // Tạo khách hàng mới
        Customer newCustomer = new Customer(name, customerId);

        // Gọi phương thức setCustomerId của User để thiết lập CCCD cho khách hàng mới
        newCustomer.setCustomerId(customerId);

        // Thêm khách hàng vào danh sách
        customers.add(newCustomer);
    }

    /**
     * Phương thức để kiểm tra xem một tài khoản có tồn tại trong ngân hàng hay không.
     *
     * @param accountNumber Số tài khoản cần kiểm tra
     * @return true nếu tài khoản tồn tại, ngược lại trả về false
     */
    public boolean isAccountExisted(String accountNumber) {
        return allAccounts.contains(accountNumber);
    }

    /**
     * Phương thức để kiểm tra xem khách hàng đã tồn tại trong ngân hàng hay chưa.
     *
     * @param customerId Mã căn cước công dân của khách hàng cần kiểm tra
     * @return true nếu khách hàng tồn tại, ngược lại trả về false
     */
    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return true; // Nếu khách hàng tồn tại, trả về true
            }
        }
        return false; // Nếu không tồn tại, trả về false
    }

    /**
     * Phương thức để lấy khách hàng từ danh sách dựa trên mã căn cước công dân.
     *
     * @param customerId Mã căn cước công dân của khách hàng cần lấy
     * @return Khách hàng tương ứng với mã căn cước công dân, hoặc null nếu không tìm thấy
     */
    public Customer getCustomerByCustomerId(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null; // Không tìm thấy khách hàng nào có CCCD tương ứng
    }

    /**
     * Phương thức để lấy danh sách khách hàng từ danh sách dựa trên tên.
     * Vì đã phát triển thêm hàm tìm khách hàng theo tên gần đúng nên tạm thời không dùng hàm này!
     *
     * @param name Tên của khách hàng cần tìm
     * @return Danh sách khách hàng có tên tương ứng
     */
    public List<Customer> getCustomersByName(String name) {
        List<Customer> foundCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                foundCustomers.add(customer);
            }
        }
        return foundCustomers; // Trả về danh sách khách hàng có tên tương ứng
    }

}

