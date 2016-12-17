package co.com.elenaschoolbusiness;

import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.ActionRequest;
import co.com.elenaschoolmodel.model.ActionResponse;
import co.com.elenaschoolmodel.model.Configuration;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import co.com.elenaschooltransverse.util.Logging;
import co.com.elenaschooltransverse.util.Query;
import co.com.elenaschooltransverse.util.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Clase que maneja la lógica de los formularios dinamicos de la APP
 *
 * @created 20-sep-2016 08:38:26
 * @author AdrianL
 */
public class ModelBusiness {

    private final IModelDao iModelDao;
    private Query query;
    private StackTraceElement[] stackTrace;
    private final ObjectMapper mapper;

    /**
     * Constructor
     */
    public ModelBusiness() {
        iModelDao = new ModelDao();
        mapper = new ObjectMapper();
    }

    /**
     * Obtiene la estructura de una tabla
     *
     * @param actionRequest
     * @return
     */
    public ActionResponse getEstructuraTabla(ActionRequest actionRequest) {
        stackTrace = Thread.currentThread().getStackTrace();
        ActionResponse actionResponse = new ActionResponse();

        try {
            // Obtenemos el objeto que viene en el request
            Model model = mapper.readValue(actionRequest.getRequest(), new TypeReference<Model>() {
            });

            // Obtenemos la estructura y la serializamos
            List<Model> listModel = iModelDao.getEstructura(model);
            actionResponse.setResponse(mapper.writeValueAsString(listModel));

            actionResponse.setError(null);
            actionResponse.setStatus(true);
        } catch (SQLException | IOException ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        }
        return actionResponse;
    }

    /**
     * Obtiene la consulta dinamicamente por tabla
     *
     * @param actionRequest
     * @return
     */
    public ActionResponse getConsulta(ActionRequest actionRequest) {
        stackTrace = Thread.currentThread().getStackTrace();
        ActionResponse actionResponse = new ActionResponse();

        try {
            // Obtenemos el objeto que viene en el request
            QueryModel queryModel = mapper.readValue(actionRequest.getRequest(), new TypeReference<QueryModel>() {
            });

            // Leemos el archivo configuracion
            Configuration config = Util.readFileConfiguration();

            queryModel.setNumberRegistersXPage(config.getNumberRegisterXPage());

            // Obtenemos el sql
            String sql = getQuery(queryModel);

            // Esta activo el parametro de guardar Sentencia sql?
            if (config.getIsActiveLogSql()) {
                Logging.writeSQL(sql, stackTrace[1].getClassName(), stackTrace[1].getMethodName());
            }

            // Ejecutamos el metodo para traer la consulta
            List<Object> result = iModelDao.getConsulta(sql);
            queryModel.setCount(getCountTable(queryModel.getModel()));
            queryModel.setListResult(result);

            // Serializamos el objeto queryModel
            actionResponse.setResponse(mapper.writeValueAsString(queryModel));
            actionResponse.setError(null);
            actionResponse.setStatus(true);
        } catch (SQLException | IOException ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        }
        return actionResponse;
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
     * Realiza actualizacion a una entidad de la base de datos
     *
     * @param actionRequest
     * @return
     */
    public ActionResponse updateModel(ActionRequest actionRequest) {
        stackTrace = Thread.currentThread().getStackTrace();
        ActionResponse actionResponse = new ActionResponse();

        try {
            // Obtenemos el objeto que viene en el request
            QueryModel queryModel = mapper.readValue(actionRequest.getRequest(), new TypeReference<QueryModel>() {
            });
            String sql = "";

            // Define el tipo de query
            if (queryModel.getIsInsert()) {
                sql = getQueryInsert(queryModel);
            } else if (queryModel.getIsUpdate()) {
                sql = getQueryUpdate(queryModel);
            }

            Configuration config = Util.readFileConfiguration();
            if (config.getIsActiveLogSql()) {
                Logging.writeSQL(sql, stackTrace[1].getClassName(), stackTrace[1].getMethodName());
            }

            actionResponse.setError(null);
            boolean resp = iModelDao.updateModel(sql);
            actionResponse.setStatus(resp);
            actionResponse.setResponse("Operación Completada con Éxito.");
        } catch (SQLException | IOException ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        }
        return actionResponse;
    }

    /**
     * Obtiene el query para realizar el insert
     *
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
     * Obtiene el query que realiza el update
     *
     * @param queryModel
     * @return
     */
    private String getQueryUpdate(QueryModel queryModel) {
        if (queryModel.getListModel() != null && queryModel.getListModel().size() > 0) {
            query = new Query();

            // Definition query
            query.setQueryTypes(Query.QueryTypes.Update);

            for (Model model : queryModel.getListModel()) {
                if (model.getIsPrimary()) {
                    query.addCondition("" + model.getColumnName() + " = " + getValueXDataType(model) + "");
                } else {
                    query.addValuePair(model.getColumnName(), getValueXDataType(model));
                }
            }
            // Add table
            query.addTable(queryModel.getModel());

            return query.getQuery();
        }
        return "";
    }

    /**
     * Define el tipo de valor si es CADENA o NUMERICO
     *
     * @param model
     * @return
     */
    private String getValueXDataType(Model model) {
        String value = "";
        if (model.getDataType().equalsIgnoreCase("character") || model.getDataType().equalsIgnoreCase("character varying") || model.getDataType().equalsIgnoreCase("date")) {
            value = "'" + model.getValor() + "'";
        } else if (model.getDataType().equalsIgnoreCase("bit")) {
            boolean valor = model.getValor() != null ? (boolean) model.getValor() : false;
            if (valor) {
                value = "'1'";
            } else{
                value = "'0'";
            }
        } else {
            value = "" + model.getValor() + "";
        }
        return value;
    }

}
