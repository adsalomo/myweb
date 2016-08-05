/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaconcepto.principal;

import co.com.pruebaconcepto.Cine;
import co.com.pruebaconcepto.Documental;
import co.com.pruebaconcepto.Pelicula;

/**
 *
 * @author AdrianL
 */
public class Principal {
    
    public static void main(String[] args){
        Cine cine = new Cine();
        Documental documental = new Documental();
        Pelicula pelicula = new Pelicula();
        cine.reproducir(documental);
        cine.reproducir(pelicula);
    }
}
