package co.com.elenaschool.service;

import co.com.elenaschoolbusiness.ModelBusiness;
import co.com.elenaschoolmodel.model.ActionRequest;
import co.com.elenaschoolmodel.model.ActionResponse;
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
    
    /**
     * Obtiene la estructura de una tabla (metadata)
     * @param actionRequest
     * @return 
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getStructureTable", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ActionResponse getStructureTable(@RequestBody ActionRequest actionRequest){
        modelBusiness = new ModelBusiness();
        return modelBusiness.getStructureTable(actionRequest);
    }
    
    /**
     * Obtiene los registros de una tabla
     * @param actionRequest
     * @return 
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getQuery", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public ActionResponse getQuery(@RequestBody ActionRequest actionRequest){
        modelBusiness = new ModelBusiness();
        return modelBusiness.getQuery(actionRequest);
    }
    
    /**
     * Realiza una actualizacion sobre una tabla
     * @param actionRequest
     * @return 
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/updateModel", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public ActionResponse updateModel(@RequestBody ActionRequest actionRequest){
        modelBusiness = new ModelBusiness();
        return modelBusiness.updateModel(actionRequest);
    }
    
    /**
     * Asigna el valor a las columnas que sean autonumericas llaves primarias
     * @param actionRequest
     * @return 
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/web/elenaschool/getMaxCode", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public ActionResponse getMaxCode(@RequestBody ActionRequest actionRequest){
        modelBusiness = new ModelBusiness();
        return modelBusiness.getMaxCode(actionRequest);
    }
    
}
