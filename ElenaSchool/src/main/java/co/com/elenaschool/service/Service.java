/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschool.service;

import co.com.elenaschool.business.LoginBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que controla el acceso a los servicios rest
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:42:05
 */
@RestController
public class Service {
    
    @Autowired
    LoginBusiness loginBusiness;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/login", method = RequestMethod.GET, produces = "application/json")
    public String login(){
        // Prueba Git
        return loginBusiness.generateToken();
    }
    
    
    
}
