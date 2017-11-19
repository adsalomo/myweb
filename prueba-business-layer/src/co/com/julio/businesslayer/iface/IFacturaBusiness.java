/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.julio.businesslayer.iface;

import co.com.julio.dataaccesslayer.model.Factura;
import java.util.List;

/**
 *
 * @author aye
 */
public interface IFacturaBusiness {
    
    /**
     * Listar
     * @return 
     */
    List<Factura> listar();
}
