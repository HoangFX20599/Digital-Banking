package vn.funix.FX20599.java.asm03.models;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    // Trả về một chuỗi dùng để phân chia các phần của biên lai
    public static String getDivider() {
        return "+----------+-------------------------+----------+";
    }

    // Trả về thời gian hiện tại dưới dạng chuỗi
    public static String getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    // Định dạng số tiền dưới dạng chuỗi
    public static String formatBalance(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,###.00 đ");
        return formatter.format(amount);
    }
}
