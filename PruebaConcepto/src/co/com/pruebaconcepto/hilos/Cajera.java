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
public class Cajera extends Thread {

    private String nombre;
    private Cliente cliente;

    public Cajera(String nombre, Cliente cliente) {
        this.nombre = nombre;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + this.cliente.getNombre() + "");

        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            System.out.println("Procesado el producto " + (i + 1) + " del cliente " + this.cliente.getNombre() + "");
        }

        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " + this.cliente.getNombre() + "");
    }
}
