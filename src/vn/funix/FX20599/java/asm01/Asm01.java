package vn.funix.FX20599.java.asm01;

import java.util.*;

public class Asm01 {
    // Khai báo hằng số cho tác giả và phiên bản
    private static final String AUTHOR = "FX20599";
    private static final String VERSION = "v1.0.0";
    // Khai báo hằng số cho giới hạn của số bảo mật
    private static final int MIN_AUTHENTICATION_CODE = 100;
    private static final int MAX_AUTHENTICATION_CODE = 999;

    // Khai báo HashMap để lưu trữ mã tỉnh và thành phố tương ứng
    private static final Map<String, String> provinceMap = new HashMap<>();

    // Khối static initializer block để khởi tạo danh sách mã tỉnh và thành phố tương ứng
    static {
        provinceMap.put("001", "Ha Noi");
        provinceMap.put("002", "Ha Giang");
        provinceMap.put("004", "Cao Bang");
        provinceMap.put("006", "Bac Kan");
        provinceMap.put("008", "Tuyen Quang");
        provinceMap.put("010", "Lao Cai");
        provinceMap.put("011", "Dien Bien");
        provinceMap.put("012", "Lai Chau");
        provinceMap.put("014", "Son La");
        provinceMap.put("015", "Yen Bai");
        provinceMap.put("017", "Hoa Binh");
        provinceMap.put("019", "Thai Nguyen");
        provinceMap.put("020", "Lang Son");
        provinceMap.put("022", "Quang Ninh");
        provinceMap.put("024", "Bac Giang");
        provinceMap.put("025", "Phu Tho");
        provinceMap.put("026", "Vinh Phuc");
        provinceMap.put("027", "Bac Ninh");
        provinceMap.put("030", "Hai Duong");
        provinceMap.put("031", "Hai Phong");
        provinceMap.put("033", "Hung Yen");
        provinceMap.put("034", "Thai Binh");
        provinceMap.put("035", "Ha Nam");
        provinceMap.put("036", "Nam Dinh");
        provinceMap.put("037", "Ninh Binh");
        provinceMap.put("038", "Thanh Hoa");
        provinceMap.put("040", "Nghe An");
        provinceMap.put("042", "Ha Tinh");
        provinceMap.put("044", "Quang Binh");
        provinceMap.put("045", "Quang Tri");
        provinceMap.put("046", "Thua Thien Hue");
        provinceMap.put("048", "Da Nang");
        provinceMap.put("049", "Quang Nam");
        provinceMap.put("051", "Quang Ngai");
        provinceMap.put("052", "Binh Dinh");
        provinceMap.put("054", "Phu Yen");
        provinceMap.put("056", "Khanh Hoa");
        provinceMap.put("058", "Ninh Thuan");
        provinceMap.put("060", "Binh Thuan");
        provinceMap.put("062", "Kon Tum");
        provinceMap.put("064", "Gia Lai");
        provinceMap.put("066", "Dak Lak");
        provinceMap.put("067", "Dak Nong");
        provinceMap.put("068", "Lam Dong");
        provinceMap.put("070", "Binh Phuoc");
        provinceMap.put("072", "Tay Ninh");
        provinceMap.put("074", "Binh Duong");
        provinceMap.put("075", "Dong Nai");
        provinceMap.put("077", "Ba Ria - Vung Tau");
        provinceMap.put("079", "Ho Chi Minh");
        provinceMap.put("080", "Long An");
        provinceMap.put("082", "Tien Giang");
        provinceMap.put("083", "Ben Tre");
        provinceMap.put("084", "Tra Vinh");
        provinceMap.put("086", "Vinh Long");
        provinceMap.put("087", "Dong Thap");
        provinceMap.put("089", "An Giang");
        provinceMap.put("091", "Kien Giang");
        provinceMap.put("092", "Can Tho");
        provinceMap.put("093", "Hau Giang");
        provinceMap.put("094", "Soc Trang");
        provinceMap.put("095", "Bac Lieu");
        provinceMap.put("096", "Ca Mau");
    }

    /**
     * Phương thức main.
     *
     * @param args Tham số dòng lệnh (không sử dụng trong chương trình này).
     */
    public static void main(String[] args) {
        // Đọc dữ liệu từ bàn phím
        Scanner scanner = new Scanner(System.in);

        //Hiển thị Header
        displayHeader();

        // Hiển thị menu chức năng
        displayMenu();

        // Mở Menu chính
        openMenu(scanner);

        scanner.close();
    }

    //##################################### Các hàm phụ lớp Asm01 ###################################

    /**
     * Hiển thị tiêu đề chương trình.
     */
    private static void displayHeader() {
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "                 |");

    }

    /**
     * Hiển thị menu chức năng.
     */
    private static void displayMenu() {
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("| 1. Nhap CCCD                                  |");
        System.out.println("| 0. Thoat                                      |");
        System.out.println("+----------+-------------------------+----------+");
    }

    /**
     * Hiển thị menu chọn chế độ xác thực EASY/HARD.
     */
    private static void displayAuthenticationMode() {
        // Chọn chế độ EASY hoặc HARD
        System.out.println("Hay chon che do ma xac thuc EASY/HARD.");
        System.out.println("1. EASY (Ma xac thuc 3 chu so 100-999)");
        System.out.println("2. HARD (Ma xac thuc 6 ky tu gom chu va so)");
        System.out.println("0. Thoat");
    }

    /**
     * Hiển thị và thực thi các chức năng liên quan đến số CCCD.
     *
     * @param scanner Đối tượng Scanner để đọc dữ liệu từ bàn phím.
     */
    private static void openMenu(Scanner scanner) {
        int choice;
        String authenticationCode;

        // Yêu cầu người dùng nhập lựa chọn và kiểm tra
        do {
            try {
                System.out.print("Chuc nang: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng trống
                if (choice != 0 && choice != 1) {
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                scanner.next(); // Đọc và loại bỏ dữ liệu không hợp lệ từ bàn phím
                choice = -1; // Gán cho choice một giá trị không hợp lệ để tiếp tục vòng lặp
            }
        } while (choice != 0 && choice != 1);

        // Xử lý lựa chọn của người dùng
        switch (choice) {
            case 0:
                System.out.println("Ket thuc chuong trinh.");
                break;
            case 1:
                System.out.println("Ban da chon chuc nang Nhap CCCD.");

                // Gọi hàm lựa chọn mã xác thực
                displayAuthenticationMode();
                authenticationCode = openAuthenticationMode(scanner);
                authenticationCheck(scanner, authenticationCode);

                // Gọi hàm xử lý chức năng nhập CCCD
                inputCCCD(scanner);
                break;
            default:
                break;
        }
    }

    /**
     * Mở chế độ EASY/HARD và tạo mã xác thực tương ứng.
     *
     * @param scanner Đối tượng Scanner để đọc dữ liệu từ bàn phím.
     * @return Mã xác thực được tạo ra.
     */
    private static String openAuthenticationMode(Scanner scanner) {
        int mode;

        do {
            try {
                System.out.print("Chon mode: ");
                mode = scanner.nextInt();
                scanner.nextLine();
                if (mode != 0 && mode != 1 && mode != 2) {
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                scanner.nextLine();
                mode = -1;
            }

        } while (mode != 0 && mode != 1 && mode != 2);

        // Xu ly lua chon và khởi tạo mã xác thực ngẫu nhiên
        switch (mode) {
            case 0:
                System.out.println("Ket thuc chuong trinh.");
                System.exit(0);
            case 1:
                // Tạo ma xac thuc 3 chu so 100-999
                return generateEasyAuthenticationCode();
            case 2:
                // Tạo ma xac thuc 6 ky tu gom chu va so
                return generateHardAuthenticationCode();

        }

        return null;
    }

    /**
     * Nhập số CCCD từ bàn phím và kiểm tra tính hợp lệ của nó.
     *
     * @param scanner Đối tượng Scanner để đọc dữ liệu từ bàn phím.
     */
    private static void inputCCCD(Scanner scanner) {


        // Mã xác thực đúng, yêu cầu nhập số CCCD
        System.out.print("Nhap so CCCD: ");
        String cccd = scanner.nextLine();

        // Kiểm tra tính hợp lệ của CCCD và mở menu bổ sung nếu hợp lệ
        while (!isValidCCCD(cccd)) {
            System.out.println("So CCCD khong hop le. Vui long nhap lai hoac 'No' de thoat.");
            cccd = scanner.next();
            if (cccd.equalsIgnoreCase("No")) {
                System.exit(0); // Thoát khỏi chương trình với mã trạng thái 0
            }

        }

        openAdditionalMenu(scanner, cccd);

    }

    // Hàm kiểm tra mã xác thực (tạm thời chưa dùng return value - sau mở rộng code sẽ dùng)

    /**
     * Phương thức xác thực mã xác thực.
     *
     * @param scanner            Scanner để đọc dữ liệu từ bàn phím.
     * @param authenticationCode Mã xác thực cần xác thực.
     * @return True nếu mã xác thực đúng, ngược lại trả về false.
     */
    private static boolean authenticationCheck(Scanner scanner, String authenticationCode) {

        System.out.println("Ma xac thuc: " + authenticationCode);

        // Hiển thị thông báo và yêu cầu người dùng nhập mã xác thực
        System.out.print("Nhap ma xac thuc: ");
        String userInput = scanner.nextLine();

        // Yêu cầu nhập lại nếu nhập sai mã xác thực
        while (!userInput.equals(authenticationCode)) {
            System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
            System.out.print("Nhap ma xac thuc: ");
            userInput = scanner.nextLine();

        }
        return true;

    }

    /**
     * Phương thức kiểm tra tính hợp lệ của số CCCD.
     *
     * @param cccd Số CCCD cần kiểm tra.
     * @return True nếu số CCCD hợp lệ, ngược lại trả về false.
     */
    public static boolean isValidCCCD(String cccd) {
        // Kiểm tra độ dài và xem tất cả các ký tự có phải là số không
        if (cccd.length() != 12 || !cccd.matches("[0-9]+")) {
            return false;
        }

        // Tách các phần của CCCD
        String provinceCode = cccd.substring(0, 3);
        String genderCenturyCode = cccd.substring(3, 4);
        String birthYearCode = cccd.substring(4, 6);
        //String randomNumbers = cccd.substring(6);

        // Kiểm tra xem mã tỉnh có tồn tại trong Map không
        if (!provinceMap.containsKey(provinceCode)) {
            return false;
        }

        // Kiểm tra mã giới tính và thế kỷ
        int genderCentury = Integer.parseInt(genderCenturyCode);
        int birthYear = Integer.parseInt(birthYearCode);
        if (genderCentury < 0 || genderCentury > 9 || birthYear < 0 || birthYear > 99) {
            return false;
        }

        // Kiểm tra xem các số cuối là số ngẫu nhiên hay không (không kiểm tra cụ thể)
        return true;
    }


    /**
     * Mở menu bổ sung khi CCCD hợp lệ.
     *
     * @param scanner Scanner để đọc dữ liệu từ bàn phím.
     * @param cccd    Số CCCD hợp lệ.
     */
    private static void openAdditionalMenu(Scanner scanner, String cccd) {
        int choice;
        do {
            try {
                // Hiển thị menu các chức năng
                System.out.println("  | 1. Kiem tra noi sinh");
                System.out.println("  | 2. Kiem tra nam sinh, gioi tinh");
                System.out.println("  | 3. Kiem tra so ngau nhien");
                System.out.println("  | 0. Thoat");
                System.out.print("Chon chuc nang: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng trống

                switch (choice) {
                    case 1:
                        // Hiển thị thông tin về tỉnh thành - nơi sinh dựa trên CCCD
                        displayProvinceInfo(cccd);

                        break;
                    case 2:
                        // Thực hiện chức năng kiểm tra năm sinh và giới tính
                        displayGenderAndBirthYear(cccd);

                        break;
                    case 3:
                        // Thực hiện chức năng kiểm tra số ngẫu nhiên
                        displayRandomNumbers(cccd);
                        break;
                    case 0:
                        // Hiển thị menu thoát hoặc kiểm tra thêm số CCCD khác
                        displayExitOrCheckAnotherCCCDMenu(scanner);
                        break;
                    default:
                        System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                scanner.nextLine(); // Xóa bỏ dòng dữ liệu không hợp lệ từ bộ đệm đầu vào của Scanner
                choice = -1;
            }

        } while (choice != 0);
    }

    /**
     * Hiển thị thông tin về tỉnh/thành phố dựa trên số CCCD.
     *
     * @param cccd Số CCCD cần kiểm tra.
     */
    private static void displayProvinceInfo(String cccd) {
        String provinceCode = cccd.substring(0, 3);
        // Kiểm tra xem provinceCode có tồn tại trong provinceMap không
        if (provinceMap.containsKey(provinceCode)) {
            String provinceName = provinceMap.get(provinceCode);
            System.out.println("Noi sinh: " + provinceName);
        } else {
            System.out.println("Khong tim thay thong tin tinh/thanh pho co ma: " + provinceCode);
        }
    }

    /**
     * Hiển thị thông tin về giới tính và năm sinh từ số CCCD.
     *
     * @param cccd Số CCCD cần kiểm tra.
     */
    private static void displayGenderAndBirthYear(String cccd) {
        // Tách mã giới tính và năm sinh từ CCCD
        String genderCenturyCode = cccd.substring(3, 4);
        String birthYearCode = cccd.substring(4, 6);

        // Hiển thị thông tin về giới tính
        String gender = (Integer.parseInt(genderCenturyCode) % 2 == 0) ? "Nam" : "Nu"; // Xác định giới tính

        // Tính thế kỷ
        int century = (Integer.parseInt(genderCenturyCode) / 2) + 20;
//        switch (genderCenturyCode) {
//            case "0":
//            case "1":
//                century = 20;
//                break;
//            case "2":
//            case "3":
//                century = 21;
//                break;
//            case "4":
//            case "5":
//                century = 22;
//                break;
//            case "6":
//            case "7":
//                century = 23;
//                break;
//            case "8":
//            case "9":
//                century = 24;
//                break;
//            default:
//                // Xử lý trường hợp không hợp lệ nếu cần
//                break;
//        }

        // Tính năm sinh
        int birthYear = Integer.parseInt(birthYearCode);
        int fullYear = (century - 1) * 100 + birthYear; // Tính năm đầy đủ (century bị delay 1 so vs tên gọi)
        System.out.println("Gioi tinh: " + gender + " | Nam Sinh: " + fullYear);


    }

    // Hàm hiển thị 6 số cuối là số ngẫu nhiên từ CCCD (tạo hàm để sau mở rộng thêm)

    /**
     * Hiển thị 6 số cuối là số ngẫu nhiên từ số CCCD.
     *
     * @param cccd Số CCCD cần kiểm tra.
     */
    private static void displayRandomNumbers(String cccd) {
        // Tách 6 số cuối từ CCCD
        String randomNumbers = cccd.substring(6);

        // Hiển thị các số ngẫu nhiên
        System.out.println("So ngau nhien: " + randomNumbers);
    }

    /**
     * Tạo mã bảo mật EASY.
     *
     * @return Mã bảo mật EASY được tạo ra.
     */
    private static String generateEasyAuthenticationCode() {
        Random random = new Random();
        int code = random.nextInt(MAX_AUTHENTICATION_CODE - MIN_AUTHENTICATION_CODE + 1) + MIN_AUTHENTICATION_CODE;
        return String.valueOf(code);
    }

    /**
     * Tạo mã bảo mật HARD.
     *
     * @return Mã bảo mật HARD được tạo ra.
     */
    private static String generateHardAuthenticationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = 6; // Chiều dài của mã bảo mật

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    /**
     * Hiển thị menu thoát hoặc kiểm tra số CCCD khác.
     *
     * @param scanner Scanner để đọc dữ liệu từ bàn phím.
     */
    private static void displayExitOrCheckAnotherCCCDMenu(Scanner scanner) {
        int choice;
        boolean exitProgram = false;
        do {
            try {
                System.out.println("Ban co the chon mot trong cac tuy chon sau:");
                System.out.println("  | 1. Thoat chuong trinh");
                System.out.println("  | 2. Kiem tra so CCCD khac");
                System.out.print("Chon chuc nang: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng trống

                switch (choice) {
                    case 1:
                        System.out.println("Ket thuc chuong trinh.");
                        exitProgram = true;
                        break;
                    case 2:
                        inputCCCD(scanner);
                        break;
                    default:
                        System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Lua chon khong hop le. Vui long nhap lai.");
                scanner.nextLine(); // Xóa bỏ dòng dữ liệu không hợp lệ từ bộ đệm đầu vào của Scanner
            }
        } while (!exitProgram);
    }


}
