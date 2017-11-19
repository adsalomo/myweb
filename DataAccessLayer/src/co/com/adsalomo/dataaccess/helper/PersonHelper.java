/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.adsalomo.dataaccess.helper;

import co.com.adsalomo.common.model.PersonModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author aye
 */
public class PersonHelper implements RowMapper<PersonModel> {

    @Override
    public PersonModel mapRow(ResultSet rs, int i) throws SQLException {
        PersonModel personModel = new PersonModel();
        personModel.setId(rs.getInt("id"));
        personModel.setName(rs.getString("nombre"));
        personModel.setLastName(rs.getString("apellido"));
        
        return personModel;
    }

}
