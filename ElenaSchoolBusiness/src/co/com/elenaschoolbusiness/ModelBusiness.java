package co.com.elenaschoolbusiness;

import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import co.com.elenaschooltransverse.util.Query;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Clase que maneja la lógica de los formularios dinamicos de la APP
 *
 * @created 20-sep-2016 08:38:26
 * @author AdrianL
 */
public class ModelBusiness {

    private final IModelDao iModelDao;
    private List<Model> models;
    private String sql;
    private Query query;

    /**
     * Constructor
     */
    public ModelBusiness() {
        iModelDao = new ModelDao();
    }

    /**
     * Obtiene la estructura de una tabla
     *
     * @param model
     * @return
     */
    public List<Model> getEstructuraTabla(Model model) {
        try {
            models = iModelDao.getEstructura(model);
        } catch (SQLException ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return models;
    }

    /**
     *
     * @param queryModel
     * @return
     */
    public QueryModel getConsulta(QueryModel queryModel) {
        try {
            List<Object> result = iModelDao.getConsulta(getQuery(queryModel.getListModel()));
            queryModel.setListResult(result);
        } catch (SQLException ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
             Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queryModel;
    }

    /**
     * Arma query con la estructura de la tabla
     *
     * @param listModel
     * @return
     */
    private String getQuery(List<Model> listModel) {
        sql = "";
        if (listModel != null && listModel.size() > 0) {
            query = new Query();
            query.setQueryTypes(Query.QueryTypes.Select);
            for(Model model : listModel){
                query.addColumn(model.getColumnName());
            }
            query.addTable(listModel.get(0).getNameTable());
            sql = query.getQuery();
        }
        return sql;
    }

}
