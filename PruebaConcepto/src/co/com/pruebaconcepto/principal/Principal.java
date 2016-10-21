/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaconcepto.principal;

import co.com.pruebaconcepto.Cine;
import co.com.pruebaconcepto.Documental;
import co.com.pruebaconcepto.MyRunnable;
import co.com.pruebaconcepto.Pelicula;
import co.com.pruebaconcepto.hilos.Cajera;
import co.com.pruebaconcepto.hilos.Cajera2;
import co.com.pruebaconcepto.hilos.CajeraRunnable;
import co.com.pruebaconcepto.hilos.Cliente;
import java.util.TreeSet;

/**
 *
 * @author AdrianL
 */
public class Principal {

    public static void main(String[] args) {
        System.out.println("String".substring(0, 4));
        Boolean var = true;
        pruebaRunnable();

    }

    public static void pruebaPolimorfismo() {
        Cine cine = new Cine();
        Documental documental = new Documental();
        Pelicula pelicula = new Pelicula();
        cine.reproducir(documental);
        cine.reproducir(pelicula);
    }

    public static void pruebaRunnable() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Adrian");
        cliente.setCarroCompra(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Sair");
        cliente2.setCarroCompra(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});
        
        Cajera2 cajera = new Cajera2("Elena", cliente);
        Cajera2 cajera2 = new Cajera2("Sandric", cliente2);
        
        Runnable runnable = new CajeraRunnable(cajera, cliente);
        Runnable runnable2 = new CajeraRunnable(cajera2, cliente2);
        
        new Thread(runnable).start();
        new Thread(runnable2).start();
    }

    public static void pruebaHilo() {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);

        Cliente cliente = new Cliente();
        cliente.setNombre("Adrian");
        cliente.setCarroCompra(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Sair");
        cliente2.setCarroCompra(new int[]{1, 2, 3, 4, 5});

        Cajera cajera = new Cajera("Elena", cliente);
        Cajera cajera2 = new Cajera("Sandric", cliente2);

        cajera.start();
        cajera2.start();
    }

    public static void pruebaMap() {
        TreeSet<String> s = new TreeSet<String>();
        TreeSet<String> subs = new TreeSet<String>();
        s.add("a");
        s.add("b");
        s.add("c");
        s.add("d");
        s.add("e");

        subs = (TreeSet) s.subSet("b", true, "d", true);
        s.add("g");
        s.pollFirst();
        s.pollFirst();
        s.add("c2");
        System.out.println(s.size() + " " + subs.size());
    }
}
