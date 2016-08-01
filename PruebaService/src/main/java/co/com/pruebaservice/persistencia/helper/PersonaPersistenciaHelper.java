/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaservice.persistencia.helper;

import co.com.pruebaservice.persistencia.modelo.PersonaPersistencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author aye
 */
public class PersonaPersistenciaHelper implements RowMapper<PersonaPersistencia>{

    @Override
    public PersonaPersistencia mapRow(ResultSet rs, int i) throws SQLException {
        PersonaPersistencia modelPers = new PersonaPersistencia();
        modelPers.setId(rs.getInt("id"));
        modelPers.setNombre(rs.getString("nombre"));
        modelPers.setApellido(rs.getString("apellido"));
        return modelPers;
    }
    
}
