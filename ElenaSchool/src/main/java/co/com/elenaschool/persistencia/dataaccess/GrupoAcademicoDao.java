package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.GrupoAcademicoModel;
import co.com.elenaschool.persistencia.contract.IGrupoAcademicoDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:39
 */
@Component
public class GrupoAcademicoDao implements IGrupoAcademicoDao {

    @Override
    public boolean deleteGrupoAcademico(GrupoAcademicoModel grupo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertGrupoAcademico(GrupoAcademicoModel grupo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GrupoAcademicoModel> readGrupoAcademico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateGrupoAcademico(GrupoAcademicoModel grupo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}//end GrupoAcademicoDao
