/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschoolbusiness;

import java.util.UUID;

/**
 * Maneja la lógica de inicio sesión
 * @author AdrianL
 */
public class LoginBusiness {

    /**
     * Genera un número hexadecimal (token) por cada login de cada usuario
     * @return 
     */
    public String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
