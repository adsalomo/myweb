/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.julio.dataaccesslayer.model;

/**
 *
 * @author aye
 */
public class Factura {
    private int facturaId;
    private String nombre;

    /**
     * @return the facturaId
     */
    public int getFacturaId() {
        return facturaId;
    }

    /**
     * @param facturaId the facturaId to set
     */
    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
