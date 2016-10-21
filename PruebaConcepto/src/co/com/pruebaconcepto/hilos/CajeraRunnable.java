/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaconcepto.hilos;

/**
 *
 * @author AdrianL
 */
public class CajeraRunnable implements Runnable{

    private Cajera2 cajera;
    private Cliente cliente;
    
    public CajeraRunnable(Cajera2 cajera, Cliente cliente){
        this.cajera = cajera;
        this.cliente = cliente;
    }
    
    @Override
    public void run() {
        cajera.procesarCompra();
    }
    
}
