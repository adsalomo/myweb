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
        model.setNameTable("www");
        
        ActionRequest actionRequest = new ActionRequest();
        actionRequest.setRequest(mapper.writeValueAsString(model));
        

        ActionResponse actionResponse = business.getEstructuraTabla(actionRequest);
        List<Model> listModel = mapper.readValue(actionResponse.getResponse(), new TypeReference<List<Model>>() {});
        System.out.println("OK");
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        QueryModel qml = mapper.readValue(new File("C:\\Notacion\\prueba.json"), QueryModel.class);
//        qml.setPage(0);
//        qml.setIsPagination(true);
//
//        QueryModel qm = business.getConsulta(qml);
//        business.insertModel(qml);
//
//        String jsonInString2 = mapper.writeValueAsString(list);
//
//        mapper.writeValue(System.out, list);
//
//        String jsonInString = mapper.writeValueAsString(qm.getListResult());
//        List<GrupoAcademicoModel> listCal = mapper.readValue(jsonInString, new TypeReference<List<GrupoAcademicoModel>>() {
//        });
//
//        if (listCal != null && !listCal.isEmpty()) {
//            System.out.println("si");
//        }
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
