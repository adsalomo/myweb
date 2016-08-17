package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.ProgramaAcademicoModel;
import java.sql.SQLException;
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
    public boolean deleteProgramaAcademico(ProgramaAcademicoModel programa) throws SQLException;

    /**
     *
     * @param programa
     */
    public boolean insertProgramaAcademico(ProgramaAcademicoModel programa) throws SQLException;

    /**
     *
     * @return
     */
    public List<ProgramaAcademicoModel> readProgramaAcademico() throws SQLException;

    /**
     *
     * @param programa
     */
    public boolean update(ProgramaAcademicoModel programa) throws SQLException;

}
