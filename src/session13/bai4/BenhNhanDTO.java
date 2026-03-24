package session13.bai4;

import java.util.List;

public class BenhNhanDTO {
    private int maBenhNhan;
    private String tenBenhNhan;
    private int tuoi;
    private String gioiTinh;
    private List<DichVuDTO> dsDichVu;

    public int getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(int maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public List<DichVuDTO> getDsDichVu() {
        return dsDichVu;
    }

    public void setDsDichVu(List<DichVuDTO> dsDichVu) {
        this.dsDichVu = dsDichVu;
    }
}