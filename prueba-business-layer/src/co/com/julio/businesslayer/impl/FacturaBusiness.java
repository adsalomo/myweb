/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.julio.businesslayer.impl;

import co.com.julio.businesslayer.iface.IFacturaBusiness;
import co.com.julio.dataaccesslayer.iface.IFacturaDao;
import co.com.julio.dataaccesslayer.impl.FacturaDao;
import co.com.julio.dataaccesslayer.model.Factura;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aye
 */
public class FacturaBusiness implements IFacturaBusiness {

    private final IFacturaDao iFacturaDao;
    
    public FacturaBusiness() {
        iFacturaDao = new FacturaDao();
    }
    
    @Override
    public List<Factura> listar() {
        List<Factura> facturas = null;
        try {
            facturas = iFacturaDao.listar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return facturas;
    }
    
}
