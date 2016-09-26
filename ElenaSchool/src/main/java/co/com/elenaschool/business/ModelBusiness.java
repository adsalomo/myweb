/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschool.business;

import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.AsignaturaModel;
import co.com.elenaschoolmodel.model.CalendarioModel;
import co.com.elenaschoolmodel.model.CompetenciaModel;
import co.com.elenaschoolmodel.model.DetalleCompetenciaModel;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.PeriodoAcademicoModel;
import co.com.elenaschoolmodel.model.QueryModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * Clase que maneja la lógica de los formularios dinamicos de la APP
 *
 * @created 20-sep-2016 08:38:26
 * @author AdrianL
 */
@Service
public class ModelBusiness {

    private final IModelDao iModelDao;
    private List<Model> models;
    private QueryModel queryModel;

    /**
     * Constructor
     */
    public ModelBusiness() {
        iModelDao = new ModelDao();
    }

    /**
     * Obtiene la estructura de una tabla
     * @param model
     * @return
     */
    public List<Model> getEstructuraTabla(Model model) {
        try {
            models = iModelDao.getEstructura(model);
        } catch (SQLException ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return models;
    }
    
    public QueryModel getConsulta(QueryModel model){
        try {
            queryModel = iModelDao.getConsulta(model);
        } catch (SQLException ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queryModel;
    }

}
