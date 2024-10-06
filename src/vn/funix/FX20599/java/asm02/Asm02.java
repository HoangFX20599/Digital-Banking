package vn.funix.FX20599.java.asm02;

import vn.funix.FX20599.java.asm02.models.Account;
import vn.funix.FX20599.java.asm02.models.Bank;
import vn.funix.FX20599.java.asm02.models.Customer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Class chính của chương trình, chứa phương thức main và điều khiển chính của ứng dụng.
 * Class này quản lý việc hiển thị menu, xử lý lựa chọn của người dùng và gọi các phương thức
 * thực thi tương ứng trong class `Bank` để thực hiện các chức năng của ngân hàng.
 */
public class Asm02 {
    private static final Scanner scanner = new Scanner(System.in); // Tạo một đối tượng Scanner tại mức toàn cục
    private static final Bank bank = new Bank(); // Đối tượng Bank là biến toàn cục

    /**
     * Phương thức main là phương thức chính để chạy chương trình.
     *
     * @param args Đối số dòng lệnh
     */
    public static void main(String[] args) {

        // Hiển thị tiêu đề
        displayHeader();

        // Show menu cho người dùng chọn
        showMenu();

        scanner.close();
    }

    /**
     * Phương thức showMenu hiển thị menu và xử lý lựa chọn của người dùng.
     */
    private static void showMenu() {
        int choice;

        do {
            // Hiển thị menu
            displayMenu();

            try {
                // Nhập lựa chọn của người dùng
                System.out.print("Function: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng trống

                // Xử lý lựa chọn của người dùng
                processChoice(choice);
            } catch (InputMismatchException e) {
                // Xử lý ngoại lệ InputMismatchException
                System.out.println("Invalid selection. Please try again.");
                scanner.nextLine(); // Đọc bỏ dòng trống
                choice = -1; // Đặt choice thành giá trị không hợp lệ để tiếp tục vòng lặp
            }
        } while (choice != 0);
    }

    /**
     * Phương thức displayHeader hiển thị tiêu đề của chương trình.
     */
    private static void displayHeader() {
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("| DIGITAL BANK | " + Bank.AUTHOR + "@" + Bank.VERSION + "                 |");
        System.out.println("+----------+-------------------------+----------+");
    }

    /**
     * Phương thức displayMenu hiển thị menu chức năng cho người dùng.
     */
    private static void displayMenu() {
        System.out.println("  1. Add customer");
        System.out.println("  2. Add account for customer");
        System.out.println("  3. Display customer list");
        System.out.println("  4. Search by ID number");
        System.out.println("  5. Search by customer name");
        System.out.println("  0. Exit");
        System.out.println("+----------+-------------------------+----------+");
    }

    /**
     * Phương thức processChoice xử lý lựa chọn của người dùng.
     *
     * @param choice Lựa chọn của người dùng
     */
    private static void processChoice(int choice) {
        switch (choice) {
            case 1:
                // Xử lý chức năng thêm khách hàng
                addCustomerInMain();
                break;
            case 2:
                // Xử lý chức năng thêm tài khoản cho khách hàng
                addAccountForCustomerInMain();
                break;
            case 3:
                // Xử lý chức năng hiển thị danh sách khách hàng
                displayCustomers();
                break;
            case 4:
                // Xử lý chức năng tìm theo CCCD
                searchCustomerByCCCD();
                break;
            case 5:
                // Xử lý chức năng tìm theo tên khách hàng
                searchCustomerByName();
                break;
            case 0:
                // Thoát khỏi chương trình
                break;
            default:
                System.out.println("Lua chon khong hop le. Vui long chon lai!");
        }
    }

    // Các phương thức phụ phục vụ cho các chức năng ####################

    /**
     * Phương thức addCustomerInMain thêm khách hàng vào ngân hàng.
     */
    public static void addCustomerInMain() {
        String name;
        String customerId;
        boolean isValidCustomerId = false;

        // Yêu cầu người dùng nhập tên khách hàng nếu là chuỗi rỗng thì yêu cầu nhập lại
        name = inputName();
        while (name.isEmpty()) {
            System.out.println("Ten khach hang khong duoc de trong. Vui long nhap lai.");
            name = inputName();
        }

        // Khi nhập cccd sẽ gặp 3 trường hợp lỗi: chuỗi rỗng, đã tồn tại, ko đúng định dang
        // Có thể gộp lại trong 1 while nhưng tác giả chọn tách thành 3 phần để thông báo cụ thể cho khách hàng
        // Bước add khách hàng vào ngân hàng đặt ở cuối để check ngoại lệ khi setCustomerId,
        // Nếu lỗi yêu cầu nhập và kiểm tra lại từ khối điều kiện đầu để đảm bảo input sạch.
        do {
            try {
                customerId = inputCCCD();

                // Kiểm tra chuỗi CCCD rỗng
                while (customerId.isEmpty()) {
                    System.out.println("So CCCD khong duoc de trong. Vui long nhap lai.");
                    customerId = inputCCCD();
                }

                // Kiểm tra tồn tại của CCCD
                while (bank.isCustomerExisted(customerId)) {
                    // Nhập lại cccd và set lại giá trị mới cho nó
                    System.out.println("Khach hang da ton tai trong ngan hang. Vui long nhap lai.");
                    customerId = inputCCCD();
                }

                // Thêm khách hàng vào ngân hàng
                bank.addCustomer(name, customerId);
                isValidCustomerId = true;
                System.out.println("Khach hang da duoc them vao ngan hang.");

            } catch (Exception e) {
                // In thông báo lỗi của ngoại lệ đc ném ra
                System.out.println("Da xay ra loi khi them khach hang vao ngan hang: " + e.getMessage());
                // Quay về đầu yêu cầu nhập lại customerId
                System.out.println("Vui long nhap lai so CCCD.");
            }
        } while (!isValidCustomerId);
    }

    /**
     * Phương thức inputName nhập tên khách hàng từ người dùng.
     * Để đảm bảo DRY nên tạo thêm hàm này.
     *
     * @return Tên khách hàng đã nhập
     */
    public static String inputName() {
        System.out.println("Nhap ten khach hang: ");
        return scanner.nextLine().trim(); // Cắt khoảng trắng đầu đuôi tránh lỗi sơ ý nhập liệu
    }

    /**
     * Phương thức inputCCCD nhập số CCCD từ người dùng.
     * Để đảm bảo DRY nên tạo thêm hàm này.
     *
     * @return Số CCCD đã nhập
     */
    public static String inputCCCD() {
        System.out.println("Nhap so CCCD: ");
        return scanner.nextLine().trim(); // Cắt khoảng trắng đầu đuôi tránh lỗi sơ ý nhập liệu
    }

    /**
     * Phương thức addAccountForCustomerInMain thêm tài khoản cho khách hàng.
     * Thêm tài khoản cho khách hàng: (cần tìm khách hàng dựa trên CCCD trước)
     * Có thế tận dụng hàm getCustomerByCustomerId ở đầu hàm addAccount trong class Bank
     * để kiểm tra sự tồn tại của khách hàng trc khi thêm tài khoản mới.
     * Nhưng để code rõ ràng tác giả chọn dùng do-while với hàm isCustomerExisted.
     */
    public static void addAccountForCustomerInMain() {
        String customerId;
        boolean isCustomerExisted;

        do {
            System.out.println("Nhap CCCD cua khach hang:");
            customerId = scanner.nextLine().trim();
            isCustomerExisted = bank.isCustomerExisted(customerId);
            if (!isCustomerExisted) {
                System.out.println("Khach hang khong ton tai trong ngan hang. Vui long nhap lai.");
            }
        } while (!isCustomerExisted);

        // Nhập số tài khoản và kiểm tra tính hợp lệ
        String accountNumber = inputAccountNumber();

        // Nhập số dư tài khoản và kiểm tra tính hợp lệ
        double balance = inputBalance();

        // Tạo tài khoản mới
        Account newAccount = new Account(accountNumber, balance);

        // Thêm tài khoản mới vào danh sách tài khoản của khách hàng và toàn bộ tài khoản của ngân hàng
        bank.addAccount(customerId, newAccount);

        System.out.println("Tai khoan da duoc them cho khach hang.");
    }

    /**
     * Phương thức inputAccountNumber nhập số tài khoản từ người dùng và kiểm tra tính hợp lệ.
     *
     * @return Số tài khoản đã nhập
     */
    private static String inputAccountNumber() {
        String accountNumber;
        boolean isValidAccountNumber = false;

        do {
            System.out.println("Nhap so tai khoan (6 chu so): ");
            accountNumber = scanner.nextLine().trim();
            if (accountNumber.matches("\\d{6}")) { // Kiểm tra số tài khoản có đúng 6 chữ số không
                if (!bank.isAccountExisted(accountNumber)) { // Kiểm tra số tài khoản đã tồn tại chưa
                    isValidAccountNumber = true;
                } else {
                    System.out.println("So tai khoan da ton tai. Vui long nhap lai.");
                }
            } else {
                System.out.println("So tai khoan phai gom 6 chu so. Vui long nhap lai.");
            }
        } while (!isValidAccountNumber);

        return accountNumber;
    }

    /**
     * Phương thức inputBalance nhập số dư tài khoản từ người dùng và kiểm tra tính hợp lệ.
     *
     * @return Số dư tài khoản đã nhập
     */
    private static double inputBalance() {
        double balance = 0;
        boolean isValidInput = false;

        do {
            System.out.println("Nhap so du tai khoan (tren 50,000 VND): ");
            String input = scanner.nextLine().trim(); // Đọc chuỗi từ người dùng

            try {
                balance = Double.parseDouble(input); // Chuyển đổi chuỗi thành số dấu phẩy động

                if (balance >= 50_000) { // Kiểm tra số dư tối thiểu
                    isValidInput = true;
                } else {
                    System.out.println("So du tai khoan khong hop le. Vui long nhap lai.");
                }
            } catch (NumberFormatException e) { // Xử lý ngoại lệ nếu không thể chuyển đổi thành số
                System.out.println("So du tai khoan khong hop le. Vui long nhap lai.");
            }
        } while (!isValidInput);
        return balance;
    }

    /**
     * Phương thức searchCustomerByCCCD tìm khách hàng theo số CCCD.
     */
    public static void searchCustomerByCCCD() {
        System.out.println("Nhap CCCD can tim:");
        String customerId = scanner.nextLine();

        // Tìm kiếm khách hàng
        Customer foundCustomer = bank.getCustomerByCustomerId(customerId);

        // Hiển thị thông tin khách hàng nếu tìm thấy, ngược lại thông báo không tìm thấy
        if (foundCustomer != null) {
            foundCustomer.displayInformation();
        } else {
            System.out.println("Khong tim thay khach hang voi CCCD: " + customerId);
        }
    }

    /**
     * Phương thức searchCustomerByName tìm khách hàng theo tên gần đúng.
     */
    private static void searchCustomerByName() {
        // Nhập và chuyển đổi sang chữ thường để so sánh không phân biệt hoa thường
        System.out.println("Nhap ten khach hang can tim: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Customer> foundCustomers = new ArrayList<>();

        // Duyệt qua danh sách khách hàng trong ngân hàng
        List<Customer> customers = bank.getCustomers();
        for (Customer customer : customers) {
            // So sánh từ khoá tìm kiếm với tên của mỗi khách hàng (dùng chuỗi con)
            if (customer.getName().toLowerCase().contains(keyword)) {
                foundCustomers.add(customer);
            }
        }

        // Hiển thị thông tin các khách hàng được tìm thấy
        if (!foundCustomers.isEmpty()) {
            System.out.println("Danh sach khach hang tim thay: ");
            for (Customer customer : foundCustomers) {
                customer.displayInformation();
            }
        } else {
            System.out.println("Khong tim thay khach hang voi ten chua: " + keyword);
        }
    }

    /**
     * Phương thức displayCustomers hiển thị danh sách tất cả khách hàng trong ngân hàng.
     */
    private static void displayCustomers() {
        List<Customer> customers = bank.getCustomers();
        for (Customer customer : customers) {
            customer.displayInformation();
        }
    }

}
