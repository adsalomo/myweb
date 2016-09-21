/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschool.business;

import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.Model;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Clase que maneja la lógica de los formularios dinamicos de la APP
 * @created 20-sep-2016 08:38:26
 * @author AdrianL
 */
@Service
public class ModelBusiness {
    
    private final IModelDao iModelDao;
    
    /**
     * Constructor
     */
    public ModelBusiness(){
        iModelDao = new ModelDao();
    }
    
    /**
     * Obtiene la estructura de una tabla
     * @return 
     */
    public List<Model> getEstructuraTabla(){
        return iModelDao.readModel(new Model());
    }
}
