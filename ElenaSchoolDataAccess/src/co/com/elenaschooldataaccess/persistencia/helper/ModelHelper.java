/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschooldataaccess.persistencia.helper;

import co.com.elenaschoolmodel.model.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AdrianL
 */
public class ModelHelper {

    /**
     * Obtiene la estructura de una tabla
     * @param rs
     * @return
     * @throws SQLException 
     */
    public static List<Model> getEstructura(ResultSet rs) throws SQLException {
        List<Model> models = new ArrayList<>();
        Model model = new Model();
        while (rs.next()) {
            model = new Model();
            model.setNameTable(rs.getString("NameTable"));
            model.setColumnName(rs.getString("ColumnName"));
            model.setIsPrimary(rs.getBoolean("IsPrimary"));
            model.setIsForeign(rs.getBoolean("IsForeign"));
            model.setDataType(rs.getString("DataType"));
            model.setIsNullable(rs.getBoolean("IsNullable"));
            model.setLenght(rs.getInt("Length"));
            model.setNumericPrecision(rs.getInt("NumericPrecision"));
            model.setLabelName(rs.getString("LabelName"));
            model.setForeignTableName(rs.getString("foreignTableName"));
            model.setIsSecuence(rs.getBoolean("IsSecuence"));
            models.add(model);
        }
        return models;
    }
}
