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

        try {
            readModel();
            //getConsulta();
            //getQuery();
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void readModel() {
        try {
            ModelBusiness business = new ModelBusiness();
            Model model = new Model();
            model.setNameTable("asignatura");
            List<Model> list = business.getEstructuraTabla(model);

            if (list != null && !list.isEmpty()) {
                System.out.println(" CAMPO | TABLA ");
                System.out.println("----------------");
                list.stream().forEach((Model row) -> {
                    
                    System.out.println("" + row.getColumnName() + " " + row.getNameTable());
                  
                });
            }
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void getConsulta() throws IOException {
        try {
            IModelDao iModelDao = new ModelDao();
            Model model = new Model();
            QueryModel queryModel = new QueryModel();
            QueryModel m = iModelDao.getConsulta(queryModel);
            ObjectMapper mapper = new ObjectMapper();
            
            //mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //mapper.writeValue(new File("c:\\Notacion\\file.json"), m.getListResult());
            String jsonInString = mapper.writeValueAsString(m.getListResult());
            
            List<CalendarioModel> listCal = mapper.readValue(jsonInString, new TypeReference<List<CalendarioModel>>(){});
            if(listCal != null && !listCal.isEmpty()){
                System.out.println("si");
            }

//            if (m.getListResult() != null && !m.getListResult().isEmpty()) {
//                System.out.println(" CAMPO | TABLA ");
//                System.out.println("----------------");
//                m.getListResult().stream().forEach((Object row) -> {
//                    Map<String, Object> map = (Map<String, Object>) row;
//                    System.out.println("" + map.get(row) + " " + row);
//                  
//                });
//            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void getQuery(){
         int val = 1;
         Query query = new Query();
         query.addTable("CALENDARIO");
         query.addTable("PERIODO_ACADEMICO");
         query.addValuePair("codigo", "0000000001");
         query.addValuePair("nombre_corto", "'1111'");
         query.addValuePair("nombre", "'2222'");
         query.addValuePair("descripcion", "'333'");
         query.addValuePair("ano", 2016);
         query.setQueryTypes(Query.QueryTypes.Insert);
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
