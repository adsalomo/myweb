package co.com.elenaschoolbusiness;

import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import co.com.elenaschooltransverse.util.Query;
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
     * Obtiene consulta
     * @param queryModel
     * @return
     */
    public QueryModel getConsulta(QueryModel queryModel) {
        try {
            List<Object> result = iModelDao.getConsulta(getQuery(queryModel.getListModel(), queryModel.getModel(), queryModel.isIsOrderAscending(), queryModel.isIsOrderDescending()));
            queryModel.setListResult(result);
        } catch (SQLException ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModelBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queryModel;
    }

    /**
     * Arma query con la estructura de la tabla
     * @param listModel
     * @param table
     * @param orderAsc
     * @param orderDesc
     * @return 
     */
    private String getQuery(List<Model> listModel, String table, boolean orderAsc, boolean orderDesc) {
        String camposOrder = "";
        boolean isOrden = false;
        sql = "";

        if (listModel != null && listModel.size() > 0) {
            query = new Query();
            query.setQueryTypes(Query.QueryTypes.Select);

            for (Model model : listModel) {
                // Add column
                query.addColumn(model.getColumnName());

                // Add condition
                if (model.getValor() != null && model.getValor().toString().length() > 0) {
                    query.addCondition(model.getColumnName() + " " + model.getValor().toString());
                }

                // Add order
                if (model.isIsOrder()) {
                    camposOrder += !isOrden ? model.getColumnName() : ", " + model.getColumnName();
                    isOrden = true;
                }
            }

            // Add table
            query.addTable(table);

            // Determina orden
            if (camposOrder.length() > 0) {
                if (orderAsc) {
                    query.addSortOrder(camposOrder, Query.SortOrder.Ascending);
                } else if (orderDesc) {
                    query.addSortOrder(camposOrder, Query.SortOrder.Descending);
                } else {
                    query.addSortOrder(camposOrder);
                }
            }

            sql = query.getQuery();
        }
        return sql;
    }

}
