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
     * @param actionRequest
     * @return
     */
    public ActionResponse getStructureTable(ActionRequest actionRequest) {
        ActionResponse actionResponse = new ActionResponse();

        try {
            // Obtenemos el objeto que viene en el request
            Model model = mapper.readValue(actionRequest.getRequest(), new TypeReference<Model>() {
            });

            // Obtenemos la estructura y la serializamos
            List<Model> listModel = iModelDao.getStructureTable(model);
            actionResponse.setResponse(mapper.writeValueAsString(listModel));

            actionResponse.setError(null);
            actionResponse.setStatus(true);
        } catch (SQLException | IOException ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        } catch (Exception ex) {
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
    public ActionResponse getQuery(ActionRequest actionRequest) {
        ActionResponse actionResponse = new ActionResponse();

        try {
            // Obtenemos el objeto que viene en el request
            QueryModel queryModel = mapper.readValue(actionRequest.getRequest(), new TypeReference<QueryModel>() {
            });

            // Leemos el archivo configuracion
            Configuration config = Util.readFileConfiguration();

            // Obtenemos el sql
            String sql = getQuery(queryModel);

            // Esta activo el parametro de guardar Sentencia sql?
            if (config.getIsActiveLogSql()) {
                stackTrace = Thread.currentThread().getStackTrace();
                Logging.writeSQL(sql, stackTrace[1].getClassName(), stackTrace[1].getMethodName());
            }

            // Ejecutamos el metodo para traer la consulta
            List<Object> result = iModelDao.getQuery(sql);
            queryModel.setListResult(result);

            // Serializamos el objeto queryModel
            actionResponse.setResponse(mapper.writeValueAsString(queryModel));
            actionResponse.setError(null);
            actionResponse.setStatus(true);
        } catch (SQLException | IOException ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        } catch (Exception ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        }
        return actionResponse;
    }
    
    /**
     * Realiza actualizacion a una entidad de la base de datos
     * @param actionRequest
     * @return
     */
    public ActionResponse updateModel(ActionRequest actionRequest) {
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
                stackTrace = Thread.currentThread().getStackTrace();
                Logging.writeSQL(sql, stackTrace[1].getClassName(), stackTrace[1].getMethodName());
            }

            actionResponse.setError(null);
            boolean resp = iModelDao.updateModel(sql);
            actionResponse.setStatus(resp);
            actionResponse.setResponse("Operación Completada con Éxito.");
        } catch (SQLException | IOException ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        } catch (Exception ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        }
        return actionResponse;
    }
    
    /**
     * Obtiene los maximos de las columnas que sean autonumericas y llaves primarias
     * @param actionRequest
     * @return 
     */
    public ActionResponse getMaxCode(ActionRequest actionRequest) {
        ActionResponse actionResponse = new ActionResponse();

        try {
            // Obtenemos el objeto que viene en el request
            QueryModel queryModel = mapper.readValue(actionRequest.getRequest(), new TypeReference<QueryModel>() {
            });

            if(queryModel.getListModel() != null && queryModel.getListModel().size() > 0)
                for (Model model : queryModel.getListModel()) {
                    // Si la columna es autonumerica
                    if (model.getDataType().equalsIgnoreCase("date")) {
                        if (model.getColumnName().equalsIgnoreCase("fecha_creacion") || model.getColumnName().equalsIgnoreCase("fecha_modificacion") || model.getColumnName().equalsIgnoreCase("fecha_proceso")) {
                            model.setValor("current_date");
                        }
                    } else if (model.getDataType().equalsIgnoreCase("bit")) {
                        model.setValor(true);
                    } else {
                        if (model.getIsSecuence()) {
                            model.setValor(getMaxTable(model));
                        } else if (model.getIsPrimary()) {
                            model.setValor(getMaxCodeTable(model));
                        } else if (model.getColumnName().equals("usuario")) {
                            model.setValor(actionRequest.getUser());
                        }
                    }
                }

            // Serealizamos el objeto listModel(Estructura tabla)
            actionResponse.setResponse(mapper.writeValueAsString(queryModel.getListModel()));
            actionResponse.setError(null);
            actionResponse.setStatus(true);
        } catch (SQLException | IOException ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        } catch (Exception ex) {
            actionResponse = Util.getError(ex.getMessage(), 0);
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        }
        return actionResponse;
    }

    /**
     * Arma query con la estructura de la tabla
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
                    query.addCondition(model.getColumnName() + " = " + getValueXDataType(model));
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

            return query.getQuery();
        }
        return "";
    }

    /**
     * Obtiene el query para realizar el insert
     * @param queryModel
     * @return
     */
    private String getQueryInsert(QueryModel queryModel) throws SQLException {
        if (queryModel.getListModel() != null && queryModel.getListModel().size() > 0) {
            query = new Query();

            // Definition query
            query.setQueryTypes(Query.QueryTypes.Insert);

            for (Model model : queryModel.getListModel()) {
                // Si la columna es autonumerica
                if (model.getIsSecuence()) {
                    query.addValuePair(model.getColumnName(), "" + getMaxTable(model) + "");
                } else if (model.getIsPrimary()) {
                    query.addValuePair(model.getColumnName(), "'" + getMaxCodeTable(model) + "'");
                } else if(model.getDataType().equalsIgnoreCase("date")){
                    query.addValuePair(model.getColumnName(), "current_date");
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
     * Obtiene el id maximo de una tabla
     * @param model
     * @return
     * @throws SQLException 
     */
    private int getMaxTable(Model model) throws SQLException{
        int max = 0;
        Query sql = new Query();
        sql.setQueryTypes(Query.QueryTypes.Select);
        sql.addColumn("(CASE WHEN MAX(ID) IS NULL THEN 1 ELSE (MAX(ID) + 1) END)");
        // Add table
        sql.addTable(model.getNameTable());
        max = iModelDao.getMaxTable(sql.getQuery());
        return max;
    }
    
    /**
     * Obtiene el codigo maximo de la tabla que se referencia en la consulta
     * @param model
     * @return
     * @throws SQLException 
     */
    private String getMaxCodeTable(Model model) throws SQLException{
        String max;
        Query sql = new Query();
        sql.setQueryTypes(Query.QueryTypes.Select);
        sql.addColumn("MAX(CODIGO)");
        // Add table
        sql.addTable(model.getNameTable());
        
        String codigo = iModelDao.getMaxCodeTable(sql.getQuery());
        
        if(codigo != null && codigo.length() > 0)
            max = String.format("%010d", Long.parseLong(codigo) + 1);
        else
            max = String.format("%010d", 1);
        return max;
    }   

    /**
     * Obtiene el query que realiza el update
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
                } else if (model.getDataType().equalsIgnoreCase("date") && (model.getColumnName().equalsIgnoreCase("fecha_modificacion") || model.getColumnName().equalsIgnoreCase("fecha_proceso"))) {
                    query.addValuePair(model.getColumnName(), "current_date");
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
     * @param model
     * @return
     */
    private String getValueXDataType(Model model) {
        String value;
        if (model.getDataType().equalsIgnoreCase("character") || model.getDataType().equalsIgnoreCase("character varying") || model.getDataType().equalsIgnoreCase("date")) {
            value = "'" + model.getValor() + "'";
        } else if (model.getDataType().equalsIgnoreCase("bit")) {
            boolean valor = model.getValor() != null ? (boolean) model.getValor() : false;
            if (valor) {
                value = "'1'";
            } else {
                value = "'0'";
            }
        } else {
            value = "" + model.getValor() + "";
        }
        return value;
    }

}
