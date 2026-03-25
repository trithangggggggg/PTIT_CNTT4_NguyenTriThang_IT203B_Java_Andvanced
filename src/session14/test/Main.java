package session14.test;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap tai khoan gui: ");
        String fromId = sc.nextLine();

        System.out.print("Nhap tai khoan nhan: ");
        String toId = sc.nextLine();

        System.out.print("Nhap so tien can chuyen: ");
        BigDecimal amount = sc.nextBigDecimal();

        try {
            transferMoney(fromId, toId, amount);
        } catch (SQLException e) {
            System.out.println("Loi SQL: " + e.getMessage());
        }
    }

    public static void transferMoney(String fromId, String toId, BigDecimal amount) throws SQLException {
        Connection conn = null;
        String checkSQL = "SELECT Balance FROM Accounts WHERE AccountId = ?";
        String callSQL = "{call sp_UpdateBalance(?, ?)}";
        String resultSQL = "SELECT AccountId, FullName, Balance FROM Accounts WHERE AccountId IN (?, ?)";

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
//            System.out.println("Ket noi thanh cong");

            BigDecimal senderBalance = null;

            // Kiem tra tai khoan gui
            try (PreparedStatement psCheck = conn.prepareStatement(checkSQL)) {
                psCheck.setString(1, fromId);

                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next()) {
                        senderBalance = rs.getBigDecimal("Balance");
                    } else {
                        throw new Exception("Tai khoan gui khong ton tai");
                    }
                }
            }

            // Kiem tra tai khoan nhan
            try (PreparedStatement psCheckReceiver = conn.prepareStatement(checkSQL)) {
                psCheckReceiver.setString(1, toId);

                try (ResultSet rs = psCheckReceiver.executeQuery()) {
                    if (!rs.next()) {
                        throw new Exception("Tai khoan nhan khong ton tai");
                    }
                }
            }

            // Kiem tra so du
            if (senderBalance.compareTo(amount) < 0) {
                throw new Exception("So du khong du de chuyen");
            }

            // Tru tien tai khoan gui
            try (CallableStatement cs1 = conn.prepareCall(callSQL)) {
                cs1.setString(1, fromId);
                cs1.setBigDecimal(2, amount.negate());
                cs1.executeUpdate();
            }

            // Cong tien tai khoan nhan
            try (CallableStatement cs2 = conn.prepareCall(callSQL)) {
                cs2.setString(1, toId);
                cs2.setBigDecimal(2, amount);
                cs2.executeUpdate();
            }

            conn.commit();
            System.out.println("Chuyen tien thanh cong");

            // Hien thi ket qua cuoi cung
            try (PreparedStatement psResult = conn.prepareStatement(resultSQL)) {
                psResult.setString(1, fromId);
                psResult.setString(2, toId);

                try (ResultSet rs = psResult.executeQuery()) {
                    System.out.println("\n===== KET QUA SAU KHI CHUYEN =====");
                    while (rs.next()) {
                        String accountId = rs.getString("AccountId");
                        String fullName = rs.getString("FullName");
                        BigDecimal balance = rs.getBigDecimal("Balance");

                        System.out.println("AccountId: " + accountId
                                + ", FullName: " + fullName
                                + ", Balance: " + balance);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());

            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Da rollback giao dich");
                } catch (SQLException ex) {
                    System.out.println("Rollback that bai: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Da dong ket noi");
                } catch (SQLException e) {
                    System.out.println("Dong ket noi that bai: " + e.getMessage());
                }
            }
        }
    }
}