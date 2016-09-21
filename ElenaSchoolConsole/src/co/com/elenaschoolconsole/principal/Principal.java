/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschoolconsole.principal;
import co.com.elenaschoolmodel.model.ConexionModel;
import co.com.elenaschooltransverse.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author AdrianL
 */
public class Principal {

    public static void main(String[] args) {
//        IModelDao iModelDao = new ModelDao();
//        Model model = new Model();
//        List<Model> list = iModelDao.readModel(model);
//        
//        if(list != null && !list.isEmpty())
//            for(Model row : list)
//                System.out.println("<. " + row.getColumnName());

        List<ConexionModel> list = new ArrayList<>();

        ConexionModel con = new ConexionModel();
        con.setDriverClassName("setDriverClassName");
        con.setUrl("setUrl");
        con.setUserName("setUserName");
        con.setPassword("setPassword");
        list.add(con);

        //        con = new ConexionModel();
//        con.setDriverClassName("setDriverClassName2");
//        con.setUrl("setUrl2");
//        con.setUserName("setUserName2");
//        con.setPassword("setPassword2");
//list.add(con);
        try {
            DataSource dataSource = Util.getDataSource();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Util.serialize(list, "C:\\Notacion\\test.xml");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            list = (List<ConexionModel>) Util.deserialize("C:\\Notacion\\test.xml");
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
