package vn.funix.FX20599.java.asm03;

import vn.funix.FX20599.java.asm02.models.Account;
import vn.funix.FX20599.java.asm02.models.Customer;
import vn.funix.FX20599.java.asm03.models.*;

import java.util.Scanner;

public class Asm03 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "037092123456"; // Mã số CCCD
    private static final String CUSTOMER_NAME = "Hoang"; // Tên khách hàng

    public static void main(String[] args) {
        // Tạo khách hàng mặc định
        activeBank.addCustomer(CUSTOMER_NAME, CUSTOMER_ID);

        // Biến thoát chương trình
        boolean exitProgram = false;

        while (!exitProgram) {
            showMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    showCustomerInfo();
                    break;
                case 2:
                    addATMAccountForCustomerInMain();
                    break;
                case 3:
                    addLoanAccountForCustomerInMain();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    // Lịch sử giao dịch
                    showCustomerInfo();
                    System.out.println();
                    showTransactionHistory();
                    break;
                case EXIT_COMMAND_CODE:
                    // Thoát khỏi chương trình
                    System.out.println("Thoat chuong trinh.");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("| NGAN HANG SO | FX20599@v3.0.0                 |");
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("  1. Xem Thong tin khach hang");
        System.out.println("  2. Them tai khoan ATM");
        System.out.println("  3. Them tai khoan tin dung");
        System.out.println("  4. Rut tien");
        System.out.println("  5. Lich su giao dich");
        System.out.println("  0. Thoat");
        System.out.println("+----------+-------------------------+----------+");
        System.out.print("Chuc nang: ");
    }

    private static int getUserChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                showMenu();
            }
        }
    }

    private static void showCustomerInfo() {
        DigitalCustomer customer = (DigitalCustomer) activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        } else {
            System.out.println("Khach hang khong ton tai.");
        }
    }


    public static void addATMAccountForCustomerInMain() {
        String customerId;
        String typeAccount = "ATM";
        boolean isCustomerExisted;

        do {
            // Theo đề cho sẵn số cccd nên gắn hằng số, sẽ bỏ chỗ này để nâng cấp hệ thống
            customerId = CUSTOMER_ID;
            isCustomerExisted = activeBank.isCustomerExisted(customerId);
            if (!isCustomerExisted) {
                System.out.println("Khach hang khong ton tai trong ngan hang. Vui long nhap lai.");
            }
        } while (!isCustomerExisted);

        // Nhập số tài khoản và kiểm tra tính hợp lệ
        String accountNumber = inputAccountNumber(typeAccount);

        // Nhập số dư tài khoản và kiểm tra tính hợp lệ
        double balance = inputBalance();

        // Tạo mới tài khoản ATM
        SavingsAccount newAccount = new SavingsAccount(accountNumber, balance);

        // Thêm tài khoản mới vào danh sách tài khoản của khách hàng và toàn bộ tài khoản của ngân hàng
        activeBank.addAccount(customerId, newAccount);

        System.out.println("Tai khoan ATM da duoc them cho khach hang.");
    }

    public static void addLoanAccountForCustomerInMain() {
        String customerId;
        String typeAccount = "tin dung";
        boolean isCustomerExisted;

        do {
            // Theo đề cho sẵn số cccd nên gắn hằng số, sẽ bỏ chỗ này để nâng cấp hệ thống
            customerId = CUSTOMER_ID;
            isCustomerExisted = activeBank.isCustomerExisted(customerId);
            if (!isCustomerExisted) {
                System.out.println("Khach hang khong ton tai trong ngan hang. Vui long nhap lai.");
            }
        } while (!isCustomerExisted);

        // Nhập số tài khoản và kiểm tra tính hợp lệ
        String accountNumber = inputAccountNumber(typeAccount);

        // Nhập số dư tài khoản và kiểm tra tính hợp lệ
        double balance = inputBalance();

        // Tạo mới tài khoản Loan
        LoanAccount newAccount = new LoanAccount(accountNumber, balance);

        // Thêm tài khoản mới vào danh sách tài khoản của khách hàng và toàn bộ tài khoản của ngân hàng
        activeBank.addAccount(customerId, newAccount);

        System.out.println("Tai khoan tin dung da duoc them cho khach hang.");
    }



    private static void withdraw() {
        System.out.print("Nhap so tai khoan: ");
        String accountNumber = scanner.nextLine();

        // Kiểm tra khách hàng có tồn tại không
        // Khi cccd ko đc cho sẵn hệ thống đc mở rộng nên thêm cái này
        DigitalCustomer customer = (DigitalCustomer) activeBank.getCustomerById(CUSTOMER_ID);
        if (customer == null) {
            System.out.println("Khach hang khong ton tai.");
            return;
        }

        // Kiểm tra tài khoản có tồn tại không
        Account account = customer.getAccount(accountNumber);
        if (account == null) {
            System.out.println("Tai khoan khong ton tai.");
            return;
        }

        // Kiểm tra tài khoản có hỗ trợ rút tiền không
        if (!(account instanceof Withdraw)) {
            System.out.println("Tai khoan khong ho tro rut tien.");
            return;
        }

        System.out.print("Nhap so tien muon rut: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("So tien khong hop le. Vui long nhap so tien.");
            return;
        }


        if (activeBank.withdraw(CUSTOMER_ID, accountNumber, amount)) {
            System.out.println("Rut tien thanh cong.");
        } else {
            System.out.println("Khong the rut tien.");
        }
    }


    private static void showTransactionHistory() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            System.out.printf("%17s | %22s | %25s | %s%n", "Account", "Amount", "Time", "     Transaction ID");
            for (Account account : customer.getAccounts()) {
                for (Transaction transaction : account.getTransactions()) {
                    System.out.println(transaction);
                }
            }
        }
    }

    private static String inputAccountNumber(String typeAccount) {
        String accountNumber;
        boolean isValidAccountNumber = false;

        do {
            System.out.println("Nhap so tai khoan " + typeAccount + " (6 chu so): ");
            accountNumber = scanner.nextLine().trim();
            if (accountNumber.matches("\\d{6}")) { // Kiểm tra số tài khoản có đúng 6 chữ số không
                if (!activeBank.isAccountExisted(accountNumber)) { // Kiểm tra số tài khoản đã tồn tại chưa
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

    private static double inputBalance() {
        double balance = 0;
        boolean isValidInput = false;

        do {
            System.out.println("Nhap so du tai khoan: ");
            String input = scanner.nextLine().trim(); // Đọc chuỗi từ người dùng

            try {
                balance = Double.parseDouble(input); // Chuyển đổi chuỗi thành số dấu phẩy động

                if (balance >= 50_000) { // Kiểm tra số dư tối thiểu
                    isValidInput = true;
                } else {
                    System.out.println("So du tai khoan khong hop le ( phai tren 50,000 VND).");
                    System.out.println("Vui long nhap lai.");
                }
            } catch (NumberFormatException e) { // Xử lý ngoại lệ nếu không thể chuyển đổi thành số
                System.out.println("So du tai khoan khong hop le. Vui long nhap lai.");
            }
        } while (!isValidInput);
        return balance;
    }


}
