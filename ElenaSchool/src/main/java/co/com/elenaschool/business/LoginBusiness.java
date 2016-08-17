/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschool.business;

import java.util.Random;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Maneja la lógica de inicio sesión
 * @author AdrianL
 */
@Service
public class LoginBusiness {

    /**
     * Genera un número hexadecimal (token) por cada login de cada usuario
     * @return 
     */
    public String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
