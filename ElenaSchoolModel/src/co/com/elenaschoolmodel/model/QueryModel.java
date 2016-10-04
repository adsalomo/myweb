package co.com.elenaschoolmodel.model;

import java.util.List;

/**
 *
 * @author AdrianL
 */
public class QueryModel {
    private List<Model> listModel;
    private List<Object> listResult;
    private boolean isOrderAscending;
    private boolean isOrderDescending;
    private String model;

    /**
     * @return the listModel
     */
    public List<Model> getListModel() {
        return listModel;
    }

    /**
     * @param listModel the listModel to set
     */
    public void setListModel(List<Model> listModel) {
        this.listModel = listModel;
    }

    /**
     * @return the listResult
     */
    public List<Object> getListResult() {
        return listResult;
    }

    /**
     * @param listResult the listResult to set
     */
    public void setListResult(List<Object> listResult) {
        this.listResult = listResult;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the isOrderAscending
     */
    public boolean isIsOrderAscending() {
        return isOrderAscending;
    }

    /**
     * @param isOrderAscending the isOrderAscending to set
     */
    public void setIsOrderAscending(boolean isOrderAscending) {
        this.isOrderAscending = isOrderAscending;
    }

    /**
     * @return the isOrderDescending
     */
    public boolean isIsOrderDescending() {
        return isOrderDescending;
    }

    /**
     * @param isOrderDescending the isOrderDescending to set
     */
    public void setIsOrderDescending(boolean isOrderDescending) {
        this.isOrderDescending = isOrderDescending;
    }
    
}
