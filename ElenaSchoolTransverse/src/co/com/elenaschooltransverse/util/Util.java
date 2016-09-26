/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschooltransverse.util;

import co.com.elenaschoolmodel.model.ConexionModel;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Clase estatica para agregar utilidades que son tranversales en todo el proyecto
 * @author AdrianL
 * @Created 21-sep-2016 08:38:26
 */
public class Util {

    /**
     * Ruta parametros de conexion
     */
    private final static String RUTA_ARCHIVO_PARAM_CONEXION = "C:\\Notacion\\conexion.xml";
    
    /**
     * Obtiene datasource para la conexión a la base de datos
     * @return
     * @throws java.io.FileNotFoundException
     */
    public static DataSource getDataSource() throws FileNotFoundException {
        List<ConexionModel> list = (List<ConexionModel>)deserialize(RUTA_ARCHIVO_PARAM_CONEXION);
        ConexionModel conexionModel = new ConexionModel();
        
        if(list != null && !list.isEmpty())
            conexionModel = list.get(0);
            
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(conexionModel.getDriverClassName());
        dataSource.setUrl(conexionModel.getUrl());
        dataSource.setUsername(conexionModel.getUserName());
        dataSource.setPassword(conexionModel.getPassword());
        return dataSource;
    }

    /**
     * Deserializa XML
     * @param fileName Nombre archivo XML
     * @return objeto leido del archivo XML
     * @throws FileNotFoundException 
     */
    public static Object deserialize(String fileName) throws FileNotFoundException {
        Object obj;
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)))) {
            obj = decoder.readObject();
        }
        return obj;
    }

    /**
     * Serializa un objeto a XML
     * @param obj Objeto a serializar
     * @param fileName Nombre archivo XML
     * @throws FileNotFoundException 
     */
    public static void serialize(Object obj, String fileName) throws FileNotFoundException {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName))) {
            encoder.writeObject(obj);
        }
    }
    
    

}
