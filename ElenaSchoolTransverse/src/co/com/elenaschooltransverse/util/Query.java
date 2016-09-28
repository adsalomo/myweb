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

    /**
     * Constructor
     */
    public Query() {
        columns = new ArrayList<>();
        tables = new ArrayList<>();
        conditions = new ArrayList<>();
        maps = new ArrayList<>();
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
        Descending
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
     * @return 
     */
    private String getQuerySelect() {
        String sql = "";
        String condi = "";
        String table = "";
        String column = "";
        int cont;

        cont = 0;
        if (columns != null && !columns.isEmpty()) {
            column = "SELECT ";
            for (String col : columns) {
                column += cont == columns.size() - 1 ? col : col + ", ";
                cont++;
            }
        }

        cont = 0;
        if (tables != null && !tables.isEmpty()) {
            table = " FROM ";
            for (String tab : tables) {
                table += cont == tables.size() - 1 ? tab : tab + ", ";
                cont++;
            }
        }

        cont = 0;
        if (conditions != null && !conditions.isEmpty()) {
            condi = " WHERE ";
            for (String con : conditions) {
                condi += cont == conditions.size() - 1 ? con : con + " AND ";
                cont++;
            }
        }
        sql = column + table + condi;
        return sql;
    }

    /**
     * Arma query insert
     * @return 
     */
    private String getQueryInsert() {
        String sql = "";
        String campos = "";
        String values = "";
        int cont;

        if (tables != null && !tables.isEmpty()) {
            sql = "INSERT INTO " + tables.get(0) + " ";
        }

        cont = 0;
        if (maps != null && !maps.isEmpty()) {
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
        }
        sql = sql + campos + values;
        return sql;
    }

    /**
     * Arma query update
     * @return 
     */
    private String getQueryUpdate() {
        String sql = "";
        String values = "";
        String condi = "";
        int cont;

        if (tables != null && !tables.isEmpty()) {
            sql = "UPDATE " + tables.get(0) + " SET ";
        }

        cont = 0;
        if (maps != null && !maps.isEmpty()) {
            for (Map<String, Object> map : maps) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    values += cont == maps.size() - 1 ? "" + entry.getKey() + " = " + entry.getValue() + "" : "" + entry.getKey() + " = " + entry.getValue() + ", ";
                }
                cont++;
            }
        }

        cont = 0;
        if (conditions != null && !conditions.isEmpty()) {
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
     * @return 
     */
    private String getQueryDelete() {
        String sql = "";
        String values = "";
        String condi = "";
        int cont;

        if (tables != null && !tables.isEmpty()) {
            sql = "DELETE FROM " + tables.get(0) + " ";
        }

        cont = 0;
        if (conditions != null && !conditions.isEmpty()) {
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
