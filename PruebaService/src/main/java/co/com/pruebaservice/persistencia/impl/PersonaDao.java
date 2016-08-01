/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaservice.persistencia.impl;

import co.com.pruebaservice.persistencia.helper.PersonaPersistenciaHelper;
import co.com.pruebaservice.persistencia.iface.IPersonaDao;
import co.com.pruebaservice.persistencia.modelo.PersonaPersistencia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author aye
 */
@Component
public class PersonaDao implements IPersonaDao{

    private final String sqlInsert = "INSERT INTO persona(nombre, apellido) values (?, ?)";
    private final String sqlSelect = "SELECT * FROM persona";
    private final String sqlDelete = "DELETE FROM persona WHERE id = ?";
    private final String sqlUpdate = "UPDATE persona SET nombre = ?, apellido = ? WHERE id = ?";
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public boolean insertarPersona(PersonaPersistencia persona) {
        jdbcTemplate.update(sqlInsert, new Object[]{persona.getNombre(), persona.getApellido()});
        return true;
    }

    @Override
    public List<PersonaPersistencia> listarPersona() {
        return jdbcTemplate.query(sqlSelect, new PersonaPersistenciaHelper());
    }

    @Override
    public boolean eliminarPersona(PersonaPersistencia persona) {
        jdbcTemplate.update(sqlDelete, new Object[]{persona.getId()});
        return true;
    }

    @Override
    public boolean editarPersona(PersonaPersistencia persona) {
        jdbcTemplate.update(sqlUpdate, new Object[]{persona.getNombre(), persona.getApellido(), persona.getId()});
        return true;
    }
    
}
