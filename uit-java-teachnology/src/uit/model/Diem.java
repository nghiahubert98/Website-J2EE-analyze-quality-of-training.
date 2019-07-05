package uit.model;

public class Diem {
    private float diem;
    private int sothutu;
    private String mssv;
    private int svId;
    private int cauHoiId;
    private String tenSV;
    
    public Diem() {}

    public Diem(float diem, int sothutu, String mssv, String tenSV) {
        this.diem = diem;
        this.sothutu = sothutu;
        this.mssv = mssv;
        this.tenSV = tenSV;
    }
    public Diem(float diem, int svId, int cauHoiId) {
        super();
        this.diem = diem;
        this.svId = svId;
        this.cauHoiId = cauHoiId;
    }

    public int getCauHoiId() {
        return cauHoiId;
    }
    public void setCauHoiId(int cauHoiId) {
        this.cauHoiId = cauHoiId;
    }
    public int getSvId() {
        return svId;
    }
    public void setSvId(int svId) {
        this.svId = svId;
    }
    public float getDiem() {
        return diem;
    }
    public void setDiem(float diem) {
        this.diem = diem;
    }
    public int getSothutu() {
        return sothutu;
    }
    public void setSothutu(int sothutu) {
        this.sothutu = sothutu;
    }
    public String getMssv() {
        return mssv;
    }
    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
    public String getTenSV() {
        return tenSV;
    }
    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }
}
