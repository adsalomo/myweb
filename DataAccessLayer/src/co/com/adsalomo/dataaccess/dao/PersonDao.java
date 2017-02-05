/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.adsalomo.dataaccess.dao;

import co.com.adsalomo.common.model.PersonModel;
import co.com.adsalomo.dataaccess.helper.PersonHelper;
import co.com.adsalomo.dataaccess.idao.IPersonDao;
import co.com.adsalomo.transverse.util.Util;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author aye
 */
public class PersonDao implements IPersonDao {

    private final JdbcTemplate jdbcTemplate;

    public PersonDao() {
        jdbcTemplate = new JdbcTemplate(Util.getDataSource());
    }

    @Override
    public boolean insertPerson(PersonModel personModel) throws SQLException {
        jdbcTemplate.update("INSERT INTO PERSONA(ID, NOMBRE, APELLIDO) VALUES (?, ?, ?)", new Object[]{personModel.getId(), personModel.getName(), personModel.getLastName()});
        return true;
    }

    @Override
    public boolean updatePerson(PersonModel personModel) throws SQLException {
        jdbcTemplate.update("UPDATE PERSONA SET NOMBRE = ?, APELLIDO = ? WHERE ID = ?", new Object[]{personModel.getName(), personModel.getLastName(), personModel.getId()});
        return true;
    }

    @Override
    public boolean deletePerson(PersonModel personModel) throws SQLException {
        jdbcTemplate.update("DELETE FROM PERSONA WHERE ID = ?", new Object[]{personModel.getId()});
        return true;
    }

    @Override
    public List<PersonModel> listPerson() throws SQLException {
        return jdbcTemplate.query("SELECT * FROM PERSONA  ORDER BY ID", new PersonHelper());
    }

}
