package uit.modelview;

import java.util.List;

import uit.utils.Constants;

public class IndexModelView <T>{
    private int id;
    private int currentPage;
    private int totalItems = 0;
    private int limit = Constants.NUMBER_ITEMS_PER_PAGE;
    private List<T> items;
    
    public IndexModelView(List<T> items) {
        this.items = items;
    }
    
    public IndexModelView(int currentPage, int totalItems, int limit) {
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.limit = limit;
    }

    public IndexModelView(int currentPage, int totalItems, int limit, List<T> items) {
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.limit = limit;
        this.items = items;
    }
    
    public IndexModelView(int id,int currentPage, int totalItems, int limit, List<T> items) {
        this.id = id;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.limit = limit;
        this.items = items;
    }

    public int getNumberPage() {
        double temp = (double)totalItems / Constants.NUMBER_ITEMS_PER_PAGE;
        int totalPage = (int) Math.ceil(temp);
        
        return totalPage < Constants.DEFAULT_PAGE_NUMBER
               ? Constants.DEFAULT_PAGE_NUMBER
               : totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
