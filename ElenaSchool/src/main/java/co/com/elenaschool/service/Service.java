package co.com.elenaschool.service;

import co.com.elenaschool.business.LoginBusiness;
import co.com.elenaschool.business.ModelBusiness;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    LoginBusiness loginBusiness;
    
    @Autowired
    ModelBusiness modelBusiness;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/login", method = RequestMethod.GET, produces = "application/json")
    public String login(){
        return loginBusiness.generateToken();
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getEstructuraTabla", method = RequestMethod.POST, produces = "application/json")
    public List<Model> getEstructuraTabla(@RequestBody Model model){
        return modelBusiness.getEstructuraTabla(model);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getConsulta", method = RequestMethod.GET, produces = "application/json")
    public QueryModel getConsulta(){
        return modelBusiness.getConsulta(new QueryModel());
    }
    
    
    
}
