package session13.bai4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public List<BenhNhanDTO> layDanhSachDashboard() {
        List<BenhNhanDTO> result = new ArrayList<>();
        Map<Integer, BenhNhanDTO> benhNhanMap = new LinkedHashMap<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseManager.getConnection();

            String sql = "SELECT bn.maBenhNhan, bn.tenBenhNhan, bn.tuoi, bn.gioiTinh, "
                    + "dv.maDichVu, dv.tenDichVu, dv.loaiDichVu "
                    + "FROM BenhNhan bn "
                    + "LEFT JOIN DichVuSuDung dv ON bn.maBenhNhan = dv.maBenhNhan "
                    + "WHERE bn.trangThai = N'DANG_DIEU_TRI' "
                    + "ORDER BY bn.maBenhNhan";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int maBenhNhan = rs.getInt("maBenhNhan");

                BenhNhanDTO benhNhan = benhNhanMap.get(maBenhNhan);

                if (benhNhan == null) {
                    benhNhan = new BenhNhanDTO();
                    benhNhan.setMaBenhNhan(maBenhNhan);
                    benhNhan.setTenBenhNhan(rs.getString("tenBenhNhan"));
                    benhNhan.setTuoi(rs.getInt("tuoi"));
                    benhNhan.setGioiTinh(rs.getString("gioiTinh"));
                    benhNhan.setDsDichVu(new ArrayList<>());

                    benhNhanMap.put(maBenhNhan, benhNhan);
                }

                /*
                 * Bẫy 2:
                 * Dùng LEFT JOIN nên bệnh nhân chưa có dịch vụ vẫn xuất hiện.
                 * Khi đó các cột dịch vụ sẽ là null.
                 * Chỉ thêm dịch vụ khi maDichVu khác null để tránh mất bệnh nhân
                 * và tránh lỗi NullPointerException khi map dữ liệu.
                 */
                Integer maDichVu = (Integer) rs.getObject("maDichVu");
                if (maDichVu != null) {
                    DichVuDTO dichVu = new DichVuDTO();
                    dichVu.setMaDichVu(maDichVu);
                    dichVu.setTenDichVu(rs.getString("tenDichVu"));
                    dichVu.setLoaiDichVu(rs.getString("loaiDichVu"));

                    benhNhan.getDsDichVu().add(dichVu);
                }
            }

            result = new ArrayList<>(benhNhanMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}