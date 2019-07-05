package uit.model;

public class CauHoi {
    private int id;
    private int order;
    private float maxPoint;
    private int dethiId;
    private String hocKyName;
    private boolean dethiType;
    private int lopId;
    private String maGV;
    private int gID;
    private String maG;
    private String motaG;
    private String malop;
    
    public CauHoi() {}
    public CauHoi(int id, int order, float maxPoint, int dethiId, String maGV) {
        this.id = id;
        this.order = order;
        this.maxPoint = maxPoint;
        this.dethiId = dethiId;
        this.maGV = maGV;
    }
    public CauHoi(int id, int order, float maxPoint, int dethiId) {
        this.id = id;
        this.order = order;
        this.maxPoint = maxPoint;
        this.dethiId = dethiId;
    }
    
    public CauHoi(int id, int order, float maxPoint) {
        this.id = id;
        this.order = order;
        this.maxPoint = maxPoint;
    }
    
    public String getMaGV() {
        return maGV;
    }
    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }
    public String getMalop() {
        return malop;
    }
    public void setMalop(String malop) {
        this.malop = malop;
    }
    public int getgID() {
        return gID;
    }
    public void setgID(int gID) {
        this.gID = gID;
    }
    public String getMaG() {
        return maG;
    }
    public void setMaG(String maG) {
        this.maG = maG;
    }
    public String getMotaG() {
        return motaG;
    }
    public void setMotaG(String motaG) {
        this.motaG = motaG;
    }
    public String getHocKyName() {
        return hocKyName;
    }
    public void setHocKyName(String hocKyName) {
        this.hocKyName = hocKyName;
    }
    public boolean getDethiType() {
        return dethiType;
    }
    public void setDethiType(boolean dethiType) {
        this.dethiType = dethiType;
    }
    public int getLopId() {
        return lopId;
    }
    public void setLopId(int lopId) {
        this.lopId = lopId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public float getMaxPoint() {
        return maxPoint;
    }
    public void setMaxPoint(float maxPoint) {
        this.maxPoint = maxPoint;
    }
    public int getDethiId() {
        return dethiId;
    }
    public void setDethiId(int dethiId) {
        this.dethiId = dethiId;
    }
}
