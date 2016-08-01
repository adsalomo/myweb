/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.pruebaservice.service.negocio.impl;

import co.com.pruebaservice.persistencia.iface.IPersonaDao;
import co.com.pruebaservice.persistencia.modelo.PersonaPersistencia;
import co.com.pruebaservice.service.modelo.PersonaService;
import co.com.pruebaservice.service.negocio.iface.IPersonaNegocio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aye
 */
@Service
public class PersonaNegocio implements IPersonaNegocio{

    @Autowired
    IPersonaDao iPersonaDao;
    private PersonaPersistencia pers;
    
    @Override
    public boolean insertarPersona(PersonaService personaModelService) {
        pers = new PersonaPersistencia();
        pers.setNombre(personaModelService.getNombre());
        pers.setApellido(personaModelService.getApellido());
        return iPersonaDao.insertarPersona(pers);
    }

    @Override
    public List<PersonaService> listarPersona() {
        List<PersonaPersistencia> list = iPersonaDao.listarPersona();
        List<PersonaService> listPersonaService = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            for (PersonaPersistencia per : list) {
                PersonaService personaService = new PersonaService();
                personaService.setId(per.getId());
                personaService.setNombre(per.getNombre());
                personaService.setApellido(per.getApellido());
                listPersonaService.add(personaService);
            }
        }
        return listPersonaService;
    }

    @Override
    public boolean eliminarPersona(PersonaService persona) {
        pers = new PersonaPersistencia();
        pers.setId(persona.getId());
        return iPersonaDao.eliminarPersona(pers);
    }

    @Override
    public boolean editarPersona(PersonaService persona) {
        pers = new PersonaPersistencia();
        pers.setId(persona.getId());
        pers.setNombre(persona.getNombre());
        pers.setApellido(persona.getApellido());
        return iPersonaDao.editarPersona(pers);
    }
    
}
