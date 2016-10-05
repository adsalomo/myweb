package co.com.elenaschool.service;

import co.com.elenaschoolbusiness.ModelBusiness;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que controla el acceso a los servicios rest
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:42:05
 */
@RestController
public class Service {
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getEstructuraTabla", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<Model> getEstructuraTabla(@RequestBody Model model){
        ModelBusiness modelBusiness = new ModelBusiness();
        return modelBusiness.getEstructuraTabla(model);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getConsulta", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public QueryModel getConsulta(@RequestBody QueryModel queryModel){
        ModelBusiness modelBusiness = new ModelBusiness();
        return modelBusiness.getConsulta(queryModel);
    }
    
    
    
}
