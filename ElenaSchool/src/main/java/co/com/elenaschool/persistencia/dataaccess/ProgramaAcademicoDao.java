package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.ProgramaAcademicoModel;
import co.com.elenaschool.persistencia.contract.IProgramaAcademicoDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:42
 */
@Component
public class ProgramaAcademicoDao implements IProgramaAcademicoDao {

    @Override
    public boolean deleteProgramaAcademico(ProgramaAcademicoModel programa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertProgramaAcademico(ProgramaAcademicoModel programa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProgramaAcademicoModel> readProgramaAcademico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProgramaAcademicoModel programa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}//end ProgramaAcademicoDao
