/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaservice.service.negocio.iface;

import co.com.pruebaservice.service.modelo.PersonaService;
import java.util.List;

/**
 *
 * @author aye
 */
public interface IPersonaNegocio {
    
    boolean insertarPersona(PersonaService persona);
    
    List<PersonaService> listarPersona();
    
    boolean eliminarPersona(PersonaService persona);
    
    boolean editarPersona(PersonaService persona);
}
