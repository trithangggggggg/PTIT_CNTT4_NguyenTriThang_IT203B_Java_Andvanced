package session13.bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    /*
     * Phần 1:
     * Code đã tắt autoCommit và có commit ở cuối, nhưng khi bước 2 bị lỗi,
     * chương trình chỉ in ra lỗi rồi kết thúc.
     * Việc này chưa đảm bảo transaction được hủy bỏ tường minh.
     * Khi xảy ra SQLException, thao tác cần thiết bị thiếu là rollback().
     * rollback() giúp đưa dữ liệu và transaction về trạng thái an toàn trước khi lỗi xảy ra.
     */

    public void thanhToanVienPhi(int patientId, int invoiceId, double amount) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            conn = DatabaseManager.getConnection();

            conn.setAutoCommit(false);

            String sqlDeductWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            ps1 = conn.prepareStatement(sqlDeductWallet);
            ps1.setDouble(1, amount);
            ps1.setInt(2, patientId);
            ps1.executeUpdate();

            String sqlUpdateInvoice = "UPDATE Invoices SET status = 'PAID' WHERE invoice_id = ?";
            ps2 = conn.prepareStatement(sqlUpdateInvoice);
            ps2.setInt(1, invoiceId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Thanh toán hoàn tất!");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Lỗi hệ thống: " + e.getMessage());

        } finally {
            try {
                if (ps2 != null) ps2.close();
                if (ps1 != null) ps1.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}