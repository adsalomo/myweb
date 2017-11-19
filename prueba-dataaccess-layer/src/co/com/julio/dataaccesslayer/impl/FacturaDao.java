/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.julio.dataaccesslayer.impl;

import co.com.julio.dataaccesslayer.helper.FacturaHelper;
import co.com.julio.dataaccesslayer.iface.IFacturaDao;
import co.com.julio.dataaccesslayer.model.Factura;
import co.com.julio.dataaccesslayer.utils.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author aye
 */
public class FacturaDao implements IFacturaDao {

    private static final String SELECT = "SELECT * FROM FACTURA";
    
    @Override
    public List<Factura> listar() throws SQLException {
        try {
            Statement statement = Conexion.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT);
            return FacturaHelper.getFacturas(resultSet);
        } finally {
            Conexion.close();
        }
    }

    @Override
    public boolean crear(Factura factura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Factura factura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Factura factura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
