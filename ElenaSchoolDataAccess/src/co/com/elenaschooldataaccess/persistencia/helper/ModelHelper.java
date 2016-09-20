/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschooldataaccess.persistencia.helper;

import co.com.elenaschooldataaccess.persistencia.model.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author AdrianL
 */
public class ModelHelper implements RowMapper<Model>{

    /**
     * Mapea los campos de la consulta
     * @param rs
     * @param i
     * @return Objeto Model
     * @throws SQLException 
     */
    @Override
    public Model mapRow(ResultSet rs, int i) throws SQLException {
        Model model = new Model();
        model.setNameTable(rs.getString("NameTable"));
        model.setColumnName(rs.getString("ColumnName"));
        model.setIsPrimary(rs.getBoolean("IsPrimary"));
        model.setIsForeign(rs.getBoolean("IsForeign"));
        model.setDateType(rs.getString("DataType"));
        model.setIsNullable(rs.getBoolean("IsNullable"));
        model.setLenght(rs.getInt("Length"));
        model.setNumericPrecision(rs.getInt("NumericPrecision"));
        model.setLabelName(rs.getString("LabelName"));
        return model;
    }
    
}
