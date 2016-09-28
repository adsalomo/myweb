package co.com.elenaschoolbusiness;

import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que maneja la lógica de los formularios dinamicos de la APP
 *
 * @created 20-sep-2016 08:38:26
 * @author AdrianL
 */
public class ModelBusiness {

    private final IModelDao iModelDao;
    private List<Model> models;
    private QueryModel queryModel;
    private String sql;

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
    
    private String getQuery(List<Model> listModel){
        sql = "";
        if(listModel != null && !listModel.isEmpty()){
            for (Model model : listModel) {
                
            }
        }
        return sql;
    }

}
