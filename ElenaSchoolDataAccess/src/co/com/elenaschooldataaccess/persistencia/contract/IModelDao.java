/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschooldataaccess.persistencia.model.Model;
import java.util.List;

/**
 *
 * @author AdrianL
 */
public interface IModelDao {
     
    List<Model> readModel(Model model);
}
