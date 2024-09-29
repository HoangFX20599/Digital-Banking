package vn.funix.FX20599.java.asm02.models;

import vn.funix.FX20599.java.asm01.Asm01;

/**
 * Class đại diện cho một người dùng trong hệ thống ngân hàng.
 * Mỗi người dùng có tên và mã căn cước công dân là các thuộc tính chung.
 */
public class User {
    // Thuộc tính tên người dùng và mã căn cước công dân
    private String name;
    private String customerId;

    /**
     * Constructor khởi tạo cho lớp User.
     *
     * @param name       Tên của người dùng
     * @param customerId Mã căn cước công dân của người dùng
     */
    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    /**
     * Phương thức getter cho tên người dùng.
     *
     * @return Tên của người dùng
     */
    public String getName() {
        return name;
    }

    /**
     * Phương thức setter cho tên người dùng.
     *
     * @param name Tên mới của người dùng
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Phương thức getter cho mã căn cước công dân.
     *
     * @return Mã căn cước công dân của người dùng
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Phương thức setter cho mã căn cước công dân.
     *
     * @param customerId Mã căn cước công dân mới của người dùng
     * @throws IllegalArgumentException Ném ra ngoại lệ nếu định dạng CCCD không hợp lệ
     */
    public void setCustomerId(String customerId) {
        if (Asm01.isValidCCCD(customerId)) {
            this.customerId = customerId;
        } else {
            throw new IllegalArgumentException("Dinh dang CCCD khong hop le.");
        }
    }

}
