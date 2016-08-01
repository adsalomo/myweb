/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaservice.persistencia.iface;

import co.com.pruebaservice.persistencia.modelo.PersonaPersistencia;
import java.util.List;

/**
 *
 * @author aye
 */
public interface IPersonaDao {
 
    boolean insertarPersona(PersonaPersistencia personaModelPers);
    List<PersonaPersistencia> listarPersona();
    boolean eliminarPersona(PersonaPersistencia persona);
    boolean editarPersona(PersonaPersistencia persona);
}
