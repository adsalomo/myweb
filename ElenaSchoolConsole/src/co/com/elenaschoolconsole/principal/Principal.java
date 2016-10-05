/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschoolconsole.principal;

import co.com.elenaschoolbusiness.ModelBusiness;
import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.dataaccess.ModelDao;
import co.com.elenaschoolmodel.model.CalendarioModel;
import co.com.elenaschoolmodel.model.ConexionModel;
import co.com.elenaschoolmodel.model.Configuracion;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
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

        //serialize();
        Util.readFileConfiguration();

        try {
            //readModel();
            getConsulta();
            //getQuery();
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getConsulta() throws IOException {
        ModelBusiness business = new ModelBusiness();

        Model model = new Model();
        model.setNameTable("calendario");

        List<Model> list = business.getEstructuraTabla(model);
//        QueryModel queryModel = new QueryModel();
//        queryModel.setListModel(list);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        QueryModel qml = mapper.readValue(new File("C:\\Notacion\\prueba.json"), QueryModel.class);
//
//        QueryModel qm = business.getConsulta(qml);
//
//        String jsonInString2 = mapper.writeValueAsString(list);
//
//        mapper.writeValue(System.out, list);
//
//        String jsonInString = mapper.writeValueAsString(qm.getListResult());
//        List<CalendarioModel> listCal = mapper.readValue(jsonInString, new TypeReference<List<CalendarioModel>>() {
//        });
//
//        if (listCal != null && !listCal.isEmpty()) {
//            System.out.println("si");
//        }
    }

    public static void serialize() {
        Configuracion c = new Configuracion();
        c.setConnectionFilePath("C:\\Notacion\\Conexion.xml");
        c.setNumberRegisterPage(5);

        Util.serialize(c, "C:\\Notacion\\Configuracion.xml");

        c = (Configuracion) Util.deserialize("C:\\Notacion\\Configuracion.xml");

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
//         query.addCondition("id = " + val);
//         query.addCondition("codigo = " + val);
        query.addColumn("codigo");
        query.addColumn("nombre_corto");
        query.addColumn("nombre");
        query.addColumn("descripcion");
        String res = query.getQuery();
        System.out.println(res);
    }
}
