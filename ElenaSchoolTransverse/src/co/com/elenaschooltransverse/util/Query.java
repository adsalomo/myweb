package co.com.elenaschooltransverse.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que genera un query dinamicamente
 *
 * @since 28 Septiembre 2016
 * @author AdrianL
 */
public class Query {

    private final List<String> columns;
    private final List<String> tables;
    private final List<String> conditions;

    private QueryTypes queryTypes;
    private SortOrder sortOrder;

    private final List<Map<String, Object>> maps;

    private String order;
    private String pagination;

    /**
     * Constructor
     */
    public Query() {
        columns = new ArrayList<>();
        tables = new ArrayList<>();
        conditions = new ArrayList<>();
        maps = new ArrayList<>();
        order = "";
        pagination = "";
    }

    /**
     *
     * @return
     */
    public QueryTypes getQueryTypes() {
        return queryTypes;
    }

    /**
     *
     * @param queryTypes
     */
    public void setQueryTypes(QueryTypes queryTypes) {
        this.queryTypes = queryTypes;
    }

    /**
     *
     * @param sortOrder
     */
    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Enum order
     *
     * @return
     */
    public SortOrder getSortOrder() {
        return sortOrder;
    }

    /**
     * Enum tipos de query
     */
    public enum QueryTypes {
        Select,
        Insert,
        Update,
        Delete
    }

    /**
     * Enum operadores condicion query
     */
    public enum ConditionOperators {
        And,
        Or
    }

    /**
     *
     */
    public enum SortOrder {
        Ascending,
        Descending,
        None
    }

    /**
     * Order Consulta
     *
     * @param order
     */
    public void addSortOrder(String order) {
        this.order = order;
    }

    /**
     * Order consulta
     * @param sortOrder
     */
    public void addSortOrder(SortOrder sortOrder) {
        switch (sortOrder) {
            case Ascending:
                this.order = " ORDER BY ID ASC ";
                break;
            case Descending:
                this.order = " ORDER BY ID DESC ";
                break;
            default:
                this.order = " ORDER BY ID ";
                break;
        }
    }
    
    /**
     * Agrega paginacion a la consulta
     * @param registerXPage
     * @param page 
     */
    public void addPagination(int registerXPage, int page){
        pagination = "LIMIT " + registerXPage + " OFFSET " + (registerXPage * page);
    }

    /**
     *
     * @param column
     */
    public void addColumn(String column) {
        columns.add(column);
    }

    /**
     *
     * @param condition
     */
    public void addCondition(String condition) {
        conditions.add(condition);
    }

    /**
     *
     * @param table
     */
    public void addTable(String table) {
        tables.add(table);
    }

    /**
     * Agrega una pareja Columna = valor
     *
     * @param column Nombre columna tabla
     * @param valor Valor asignar a la columna
     */
    public void addValuePair(String column, Object valor) {
        Map<String, Object> map = new HashMap<>();
        map.put(column, valor);
        maps.add(map);
    }

    /**
     * Obtiene query pedido INSERT, SELECT, UPDATE, DELETE
     *
     * @return
     */
    public String getQuery() {
        String sql = "";

        switch (getQueryTypes()) {
            case Insert:
                sql = getQueryInsert();
                break;
            case Update:
                sql = getQueryUpdate();
                break;
            case Delete:
                sql = getQueryDelete();
                break;
            case Select:
                sql = getQuerySelect();
                break;
            default:
                break;
        }
        return sql;
    }

    /**
     * Arma query select
     *
     * @return
     */
    private String getQuerySelect() {
        String sql = "";
        String condi = "";
        String table = "";
        String column = "";
        int cont;

        cont = 0;
        column = "SELECT * ";

        if (tables == null || tables.isEmpty()) {
            return sql;
        }

        if (columns != null && columns.size() > 0) {
            column = "SELECT ";
            for (String col : columns) {
                column += cont == columns.size() - 1 ? col : col + ", ";
                cont++;
            }
        }

        cont = 0;
        table = " FROM ";
        for (String tab : tables) {
            table += cont == tables.size() - 1 ? tab : tab + ", ";
            cont++;
        }

        cont = 0;
        if (conditions != null && conditions.size() > 0) {
            condi = " WHERE ";
            for (String con : conditions) {
                condi += cont == conditions.size() - 1 ? con : con + " AND ";
                cont++;
            }
        }

        sql = column + table + condi + order + pagination;
        return sql;
    }

    /**
     * Arma query insert
     *
     * @return
     */
    private String getQueryInsert() {
        String sql = "";
        String campos = "";
        String values = "";
        int cont;

        if (tables == null || tables.isEmpty() || maps == null || maps.isEmpty()) {
            return sql;
        }

        sql = "INSERT INTO " + tables.get(0) + " ";

        cont = 0;

        campos += "(";
        values += " VALUES (";
        for (Map<String, Object> map : maps) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                campos += cont == maps.size() - 1 ? "" + entry.getKey() + "" : "" + entry.getKey() + ", ";
                values += cont == maps.size() - 1 ? "" + entry.getValue() + "" : "" + entry.getValue() + ", ";
            }
            cont++;
        }
        campos += ")";
        values += ")";

        sql = sql + campos + values;
        return sql;
    }

    /**
     * Arma query update
     *
     * @return
     */
    private String getQueryUpdate() {
        String sql = "";
        String values = "";
        String condi = "";
        int cont;

        if (tables == null || tables.isEmpty() || maps == null || maps.isEmpty()) {
            return sql;
        }

        sql = "UPDATE " + tables.get(0) + " SET ";

        cont = 0;

        for (Map<String, Object> map : maps) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                values += cont == maps.size() - 1 ? "" + entry.getKey() + " = " + entry.getValue() + "" : "" + entry.getKey() + " = " + entry.getValue() + ", ";
            }
            cont++;
        }

        cont = 0;
        if (conditions != null && conditions.size() > 0) {
            condi = " WHERE ";
            for (String con : conditions) {
                condi += cont == conditions.size() - 1 ? con : con + " AND ";
            }
        }

        sql = sql + values + condi;
        return sql;
    }

    /**
     * Arma query delete
     *
     * @return
     */
    private String getQueryDelete() {
        String sql = "";
        String values = "";
        String condi = "";
        int cont;

        if (tables == null || tables.isEmpty()) {
            return sql;
        }

        sql = "DELETE FROM " + tables.get(0) + " ";

        cont = 0;
        if (conditions != null && conditions.size() > 0) {
            condi = " WHERE ";
            for (String con : conditions) {
                condi += cont == conditions.size() - 1 ? con : con + " AND ";
                cont++;
            }
        }

        sql = sql + values + condi;
        return sql;
    }
}
