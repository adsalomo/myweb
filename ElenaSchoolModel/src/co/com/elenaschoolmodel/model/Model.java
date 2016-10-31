package co.com.elenaschoolmodel.model;

/**
 * Define las propiedades de la estructura de una tabla
 * @author AdrianL
 */
public class Model {
    private String nameTable;
    private String columnName;
    private boolean isPrimary;
    private boolean isForeign;
    private String dataType;
    private boolean isNullable;
    private int lenght;
    private int numericPrecision;
    private String labelName;
    private Object valor;
    private String foreignTableName;
    private boolean isSecuence;

    /**
     * 
     * @return 
     */
    public String getForeignTableName() {
        return foreignTableName;
    }

    /**
     * 
     * @param foreignTableName 
     */
    public void setForeignTableName(String foreignTableName) {
        this.foreignTableName = foreignTableName;
    }

    /**
     * 
     * @return 
     */
    public Object getValor() {
        return valor;
    }

    /**
     * 
     * @param valor 
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    /**
     * @return the tableName
     */
    public String getNameTable() {
        return nameTable;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setNameTable(String tableName) {
        this.nameTable = tableName;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the isPrimary
     */
    public boolean isIsPrimary() {
        return isPrimary;
    }

    /**
     * @param isPrimary the isPrimary to set
     */
    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    /**
     * @return the isForeign
     */
    public boolean isIsForeign() {
        return isForeign;
    }

    /**
     * @param isForeign the isForeign to set
     */
    public void setIsForeign(boolean isForeign) {
        this.isForeign = isForeign;
    }

    /**
     * @return the dateType
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @param dateType the dateType to set
     */
    public void setDataType(String dateType) {
        this.dataType = dateType;
    }

    /**
     * @return the isNullable
     */
    public boolean isIsNullable() {
        return isNullable;
    }

    /**
     * @param isNullable the isNullable to set
     */
    public void setIsNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    /**
     * @return the lenght
     */
    public int getLenght() {
        return lenght;
    }

    /**
     * @param lenght the lenght to set
     */
    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    /**
     * @return the numericPrecision
     */
    public int getNumericPrecision() {
        return numericPrecision;
    }

    /**
     * @param numericPrecision the numericPrecision to set
     */
    public void setNumericPrecision(int numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    /**
     * @return the labelName
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * @param labelName the labelName to set
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * @return the isSecuence
     */
    public boolean getIsSecuence() {
        return isSecuence;
    }

    /**
     * @param isSecuence the isSecuence to set
     */
    public void setIsSecuence(boolean isSecuence) {
        this.isSecuence = isSecuence;
    }
}
