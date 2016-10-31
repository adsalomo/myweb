package co.com.elenaschoolbusiness;

import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import co.com.elenaschooltransverse.util.Query;
import co.com.elenaschooltransverse.util.Util;
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
     * Obtiene consulta
     *
     * @param queryModel Objeto que define la consulta
     * @return
     */
    public QueryModel getConsulta(QueryModel queryModel) {
        try {
            queryModel.setNumberRegistersXPage(Util.readFileConfiguration().getNumberRegisterXPage());
            List<Object> result = iModelDao.getConsulta(getQuery(queryModel));
            queryModel.setCount(getCountTable(queryModel.getModel()));
            queryModel.setListResult(result);
        } catch (SQLException ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queryModel;
    }

    /**
     * Obtiene numero de registros por consulta
     *
     * @param queryModel
     * @return
     * @throws SQLException
     */
    private int getCountTable(String table) throws SQLException {
        query = new Query();
        query.setQueryTypes(Query.QueryTypes.Select);
        // Add table
        query.addTable(table);
        List<Object> result = iModelDao.getConsulta(query.getQuery());
        return result != null ? result.size() : 0;
    }

    /**
     * Arma query con la estructura de la tabla
     *
     * @param queryModel Objeto que define la consulta
     * @return
     */
    private String getQuery(QueryModel queryModel) {
        if (queryModel.getListModel() != null && queryModel.getListModel().size() > 0) {
            query = new Query();
            // Definition query
            query.setQueryTypes(Query.QueryTypes.Select);

            for (Model model : queryModel.getListModel()) {
                // Add column
                query.addColumn(model.getColumnName());

                // Add condition
                if (model.getValor() != null && model.getValor().toString().length() > 0) {
                    query.addCondition(model.getColumnName() + " " + model.getValor().toString());
                }
            }

            // Add table
            query.addTable(queryModel.getModel());

            // Determina orden
            if (queryModel.isIsOrderAscending()) {
                query.addSortOrder(Query.SortOrder.Ascending);
            } else if (queryModel.isIsOrderDescending()) {
                query.addSortOrder(Query.SortOrder.Descending);
            } else {
                query.addSortOrder(Query.SortOrder.None);
            }

            // Add pagination
            if (queryModel.getIsPagination()) {
                query.addPagination(queryModel.getNumberRegistersXPage(), queryModel.getPage());
            }
            return query.getQuery();
        }
        return "";
    }

    /**
     * Realiza insert a una entidad de la base de datos
     * @param queryModel
     * @return 
     */
    public boolean insertModel(QueryModel queryModel) {
        try {
            return iModelDao.insertModel(getQueryInsert(queryModel));
        } catch (SQLException ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Obtiene el query para realizar el insert
     * @param queryModel
     * @return 
     */
    private String getQueryInsert(QueryModel queryModel) {
        if (queryModel.getListModel() != null && queryModel.getListModel().size() > 0) {
            query = new Query();
            
            // Definition query
            query.setQueryTypes(Query.QueryTypes.Insert);

            for (Model model : queryModel.getListModel()) {
                query.addValuePair(model.getColumnName(), getValueXDataType(model));
            }

            // Add table
            query.addTable(queryModel.getModel());

            return query.getQuery();
        }
        return "";
    }
    
    /**
     * Define el tipo de valor si es CADENA o NUMERICO
     * @param model
     * @return 
     */
    private String getValueXDataType(Model model){
        if(model.getDataType().equalsIgnoreCase("character") || model.getDataType().equalsIgnoreCase("character varying") 
                || model.getDataType().equalsIgnoreCase("date"))
            return "'" + model.getValor() + "'";
        else
            return "" + model.getValor() + "";
    }

}
