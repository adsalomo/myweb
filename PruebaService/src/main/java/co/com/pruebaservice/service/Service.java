/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaservice.service;

import co.com.pruebaservice.service.modelo.PersonaService;
import co.com.pruebaservice.service.negocio.iface.IPersonaNegocio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aye
 */
@RestController
public class Service {
    
    @Autowired
    IPersonaNegocio iPersonaNegocio;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/web/persona/addpersona", method = RequestMethod.POST)
    public boolean registrarPersona(@RequestBody PersonaService persona){
         return iPersonaNegocio.insertarPersona(persona);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/web/persona/listpersona", method = RequestMethod.GET)
    public List<PersonaService> listarPersona(){
         return iPersonaNegocio.listarPersona();
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/web/persona/deletepersona", method = RequestMethod.POST)
    public boolean eliminarPersona(@RequestBody PersonaService persona){
         return iPersonaNegocio.eliminarPersona(persona);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/web/persona/editarpersona", method = RequestMethod.POST)
    public boolean editarPersona(@RequestBody PersonaService persona){
         return iPersonaNegocio.editarPersona(persona);
    }
}
