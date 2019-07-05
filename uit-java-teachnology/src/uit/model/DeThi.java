package uit.model;

public class DeThi {
    private int id;
    private boolean type;
    private int lopId;
    
    
    public DeThi() { }
    public DeThi(int id, boolean type, int lopId) {
        this.id = id;
        this.type = type;
        this.lopId = lopId;
    }
    public DeThi(boolean type, int lopId) {
        this.type = type;
        this.lopId = lopId;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean getType() {
        return type;
    }
    public void setType(boolean type) {
        this.type = type;
    }
    public int getLopId() {
        return lopId;
    }
    public void setLopId(int lopId) {
        this.lopId = lopId;
    }
}
