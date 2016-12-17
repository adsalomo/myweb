/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschoolconsole.principal;

import co.com.elenaschoolbusiness.ModelBusiness;
import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.ActionRequest;
import co.com.elenaschoolmodel.model.ActionResponse;
import co.com.elenaschoolmodel.model.CalendarioModel;
import co.com.elenaschoolmodel.model.ConexionModel;
import co.com.elenaschoolmodel.model.Configuration;
import co.com.elenaschoolmodel.model.GrupoAcademicoModel;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import co.com.elenaschooltransverse.util.Logging;
import co.com.elenaschooltransverse.util.Query;
import co.com.elenaschooltransverse.util.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author AdrianL
 */
public class Principal {

    public static void main(String[] args) {
        //Logging.writeSQL("sss", "ffff", "fffsss");
        try {
            //getQuery();
            //serialize();
            //Util.readFileConfiguration();

            //readModel();
            getConsulta();
            //getQuery();
        } catch (Exception ex) {

        }
    }

    public static void getConsulta() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ModelBusiness business = new ModelBusiness();
        Model model = new Model();
        model.setNameTable("grupo_academico");

        ActionRequest actionRequest = new ActionRequest();
        actionRequest.setRequest(mapper.writeValueAsString(model));

        // Obtenemos la estrucura
        ActionResponse actionResponse = business.getEstructuraTabla(actionRequest);
        List<Model> listModel = mapper.readValue(actionResponse.getResponse(), new TypeReference<List<Model>>() {
        });

        // Obtenemos la consulta
        QueryModel queryModel = new QueryModel();
        queryModel.setModel("grupo_academico");
        queryModel.setListModel(listModel);
        actionRequest.setRequest(mapper.writeValueAsString(queryModel));
        actionResponse = business.getConsulta(actionRequest);
        queryModel = mapper.readValue(actionResponse.getResponse(), new TypeReference<QueryModel>() {
        });
        System.out.print(queryModel);

        //UPDATE grupo_academico SET id = 3, nombre_corto = 'INGENIERA', nombre = 'INGENIERA GRUPO 11', 
        //descripcion = 'GRUPO 11 DE INGENIERIA SISTEMAS', codigo_grado = '0000000001', calendario = '0000000002', 
        //persona_responsable = 'SANDRIC   ', activo = '1', usuario = 'adsalomo', fecha_creacion = '2016-10-04', 
        //fecha_modificacion = '2016-10-04', fecha_proceso = '2016-10-04' WHERE codigo = '0000000011'
        if (listModel != null && listModel.size() > 0) {
            for (Model row : listModel) {
                if (row.getColumnName().equals("fecha_creacion")) {
                    row.setValor("2016-10-04");
                }
                if (row.getColumnName().equals("fecha_modificacion")) {
                    row.setValor("2016-10-04");
                }
                if (row.getColumnName().equals("fecha_proceso")) {
                    row.setValor("2016-10-04");
                }
                if (row.getColumnName().equals("id")) {
                    row.setValor("3");
                }
                if (row.getColumnName().equals("nombre")) {
                    row.setValor("INGENIERIA GRUPO 11");
                }
                if (row.getColumnName().equals("descripcion")) {
                    row.setValor("GRUPO 11 DE INGENIERIA SISTEMAS");
                }
                if (row.getColumnName().equals("codigo_grado")) {
                    row.setValor("0000000001");
                }
                if (row.getColumnName().equals("calendario")) {
                    row.setValor("0000000002");
                }
                if (row.getColumnName().equals("persona_responsable")) {
                    row.setValor("SANDRIC");
                }
                if (row.getColumnName().equals("usuario")) {
                    row.setValor("adsalomo");
                }
                if (row.getColumnName().equals("codigo")) {
                    row.setValor("0000000011");
                }
                if (row.getColumnName().equals("nombre_corto")) {
                    row.setValor("prueba");
                }
            }
        }
        queryModel = new QueryModel();
        queryModel.setIsUpdate(true);
        queryModel.setModel("grupo_academico");
        queryModel.setListModel(listModel);
        actionRequest.setRequest(mapper.writeValueAsString(queryModel));
        actionResponse = business.updateModel(actionRequest);
        if (actionResponse.getStatus()) {
            System.out.println("");
        }

    }

    public static void serialize() {
        Configuration c = new Configuration();
        c.setConnectionFilePath("C:\\Notacion\\Conexion.xml");
        c.setNumberRegisterXPage(5);
        c.setLoggingFilePath("C:\\Notacion\\Conexion.xml");
        c.setLoggingSqlFilePath("C:\\Notacion\\Conexion.xml");
        c.setIsActiveLogSql(true);

        Util.serialize(c, "C:\\Notacion\\Configuracion.xml");

        c = (Configuration) Util.deserialize("C:\\Notacion\\Configuracion.xml");

    }

    public static void getQuery() {
        int val = 1;
        Query query = new Query();
        query.addTable("CALENDARIO");
        query.addTable("PERIODO_ACADEMICO");
        query.addValuePair("codigo", "0000000001");
        query.addValuePair("nombre_corto", "'1111'");
        query.addValuePair("nombre", "'2222'");
        query.addValuePair("descripcion", "'333'");
        query.addValuePair("ano", 2016);
        query.setQueryTypes(Query.QueryTypes.Select);
        query.addCondition("id = " + val);
        query.addCondition("codigo = " + val);
        query.addColumn("codigo");
        query.addColumn("nombre_corto");
        query.addColumn("nombre");
        query.addColumn("descripcion");
        String res = query.getQuery();
        System.out.println(res);
    }
}
