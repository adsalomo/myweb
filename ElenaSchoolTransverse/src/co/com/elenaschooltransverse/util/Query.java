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
        StringBuilder sql = new StringBuilder();
        StringBuilder condi = new StringBuilder();
        StringBuilder table = new StringBuilder();
        StringBuilder column = new StringBuilder();
        int cont;

        cont = 0;
        column.append("SELECT * ");

        if (tables == null || tables.isEmpty()) {
            return sql.append("").toString();
        }

        if (columns != null && columns.size() > 0) {
            column.append("SELECT ");
            for (String col : columns) {
                column.append(cont == columns.size() - 1 ? col : col + ", ");
                cont++;
            }
        }

        cont = 0;
        table.append(" FROM ");
        for (String tab : tables) {
            table.append(cont == tables.size() - 1 ? tab : tab + ", ");
            cont++;
        }

        cont = 0;
        if (conditions != null && conditions.size() > 0) {
            condi.append(" WHERE ");
            for (String con : conditions) {
                condi.append(cont == conditions.size() - 1 ? con : con + " AND ");
                cont++;
            }
        }

        sql.append(column.toString()); 
        sql.append(table.toString()); 
        sql.append(condi.toString());
        sql.append(order);
        sql.append(pagination);
        return sql.toString();
    }

    /**
     * Arma query insert
     *
     * @return
     */
    private String getQueryInsert() {
        StringBuilder sql = new StringBuilder();
        StringBuilder campos = new StringBuilder();
        StringBuilder values = new StringBuilder();
        int cont;

        if (tables == null || tables.isEmpty() || maps == null || maps.isEmpty()) {
            return sql.append("").toString();
        }

        sql.append("INSERT INTO "); 
        sql.append(tables.get(0)); 
        sql.append(" ");

        cont = 0;

        campos.append("(");
        values.append(" VALUES (");
        for (Map<String, Object> map : maps) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                campos.append(cont == maps.size() - 1 ? "" + entry.getKey() + "" : "" + entry.getKey() + ", ");
                values.append(cont == maps.size() - 1 ? "" + entry.getValue() + "" : "" + entry.getValue() + ", ");
            }
            cont++;
        }
        campos.append(")");
        values.append(")");

        sql.append(campos); 
        sql.append(values);
        
        return sql.toString();
    }

    /**
     * Arma query update
     *
     * @return
     */
    private String getQueryUpdate() {
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder condi = new StringBuilder();
        int cont;

        if (tables == null || tables.isEmpty() || maps == null || maps.isEmpty()) {
            return sql.append("").toString();
        }

        sql.append("UPDATE "); 
        sql.append(tables.get(0)); 
        sql.append(" SET ");

        cont = 0;

        for (Map<String, Object> map : maps) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                values.append(cont == maps.size() - 1 ? "" + entry.getKey() + " = " + entry.getValue() + "" : "" + entry.getKey() + " = " + entry.getValue() + ", ");
            }
            cont++;
        }

        cont = 0;
        if (conditions != null && conditions.size() > 0) {
            condi.append(" WHERE ");
            for (String con : conditions) {
                condi.append(cont == (conditions.size() - 1) ? con : con + " AND ");
                cont++;
            }
        }

        sql.append(values);
        sql.append(condi);
        
        return sql.toString();
    }

    /**
     * Arma query delete
     *
     * @return
     */
    private String getQueryDelete() {
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder condi = new StringBuilder();
        int cont;

        if (tables == null || tables.isEmpty()) {
            return sql.toString();
        }

        sql.append("DELETE FROM "); 
        sql.append(tables.get(0)); 
        sql.append(" ");

        cont = 0;
        if (conditions != null && conditions.size() > 0) {
            condi.append(" WHERE ");
            for (String con : conditions) {
                condi.append(cont == conditions.size() - 1 ? con : con + " AND ");
                cont++;
            }
        }

        sql.append(values);
        sql.append(condi);
        return sql.toString();
    }
}
