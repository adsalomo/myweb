package co.com.elenaschool.service;

import co.com.elenaschoolbusiness.ModelBusiness;
import co.com.elenaschoolmodel.model.ActionRequest;
import co.com.elenaschoolmodel.model.ActionResponse;
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
    
    private ModelBusiness modelBusiness;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getEstructuraTabla", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ActionResponse getEstructuraTabla(@RequestBody ActionRequest actionRequest){
        modelBusiness = new ModelBusiness();
        return modelBusiness.getEstructuraTabla(actionRequest);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getConsulta", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public ActionResponse getConsulta(@RequestBody ActionRequest actionRequest){
        modelBusiness = new ModelBusiness();
        return modelBusiness.getConsulta(actionRequest);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/updateModel", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public ActionResponse updateModel(@RequestBody ActionRequest actionRequest){
        modelBusiness = new ModelBusiness();
        return modelBusiness.updateModel(actionRequest);
    }
    
}
