/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.julio.dataaccesslayer.iface;

import co.com.julio.dataaccesslayer.model.Factura;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aye
 */
public interface IFacturaDao {
    
    /**
     * Lista las facturas
     * @return 
     */
    List<Factura> listar() throws SQLException;
    
    /**
     * Crea un registro
     * @param factura
     * @return 
     */
    boolean crear(Factura factura) throws SQLException;
    
    /**
     * Actualiza un registro
     * @param factura
     * @return 
     */
    boolean actualizar(Factura factura) throws SQLException;
    
    /**
     * Elimina registro
     * @param factura
     * @return 
     */
    boolean eliminar(Factura factura) throws SQLException;
}
