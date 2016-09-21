package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschoolmodel.model.ProgramaAcademicoModel;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:37
 */
public interface IProgramaAcademicoDao {

    /**
     *
     * @param programa
     */
    public boolean deleteProgramaAcademico(ProgramaAcademicoModel programa);

    /**
     *
     * @param programa
     */
    public boolean insertProgramaAcademico(ProgramaAcademicoModel programa);

    /**
     *
     * @return
     */
    public List<ProgramaAcademicoModel> readProgramaAcademico();

    /**
     *
     * @param programa
     */
    public boolean update(ProgramaAcademicoModel programa);

}
