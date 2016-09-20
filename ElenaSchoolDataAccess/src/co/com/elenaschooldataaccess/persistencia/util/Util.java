/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschooldataaccess.persistencia.util;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author AdrianL
 */
public class Util {
    
    /**
     * Obtiene datasource
     * @return 
     */
    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/elenaschool");
        dataSource.setUsername("postgres");
        dataSource.setPassword("adrian06");
        return dataSource;
    }
}
