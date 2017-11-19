/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.julio.dataaccesslayer.helper;

import co.com.julio.dataaccesslayer.model.Factura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aye
 */
public class FacturaHelper {
    
    /**
     * Setea los valores del resultset al objeto
     * @param resultSet
     * @return
     * @throws SQLException 
     */
    public static List<Factura> getFacturas(ResultSet resultSet) throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        if (resultSet == null) {
            return facturas;
        }
        
        while (resultSet.next()) {            
            Factura factura = new Factura();
            factura.setFacturaId(resultSet.getInt("factura_id"));
            factura.setNombre(resultSet.getString("nombre"));
            facturas.add(factura);
        }
        
        return facturas;
    }
}
