package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.ProgramaAcademicoModel;
import co.com.elenaschool.persistencia.contract.IProgramaAcademicoDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:42
 */
public class ProgramaAcademicoDao implements IProgramaAcademicoDao {

    public ProgramaAcademicoDao() {

    }

    /**
     *
     * @param programa
     */
    public boolean deleteProgramaAcademico(ProgramaAcademicoModel programa) {
        return false;
    }

    /**
     *
     * @param programa
     */
    public boolean insertProgramaAcademico(ProgramaAcademicoModel programa) {
        return false;
    }

    /**
     *
     * @return
     */
    public List<ProgramaAcademicoModel> readProgramaAcademico() {
        return null;
    }

    /**
     *
     * @param programa
     */
    public boolean update(ProgramaAcademicoModel programa) {
        return false;
    }
}//end ProgramaAcademicoDao
