package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.GrupoAcademicoModel;
import java.sql.SQLException;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:33
 */
public interface IGrupoAcademicoDao {

    /**
     *
     * @param grupo
     */
    public boolean deleteGrupoAcademico(GrupoAcademicoModel grupo) throws SQLException;

    /**
     *
     * @param grupo
     */
    public boolean insertGrupoAcademico(GrupoAcademicoModel grupo) throws SQLException;

    /**
     * 
     * @return
     * @throws SQLException 
     */
    public List<GrupoAcademicoModel> readGrupoAcademico() throws SQLException;

    /**
     *
     * @param grupo
     */
    public boolean updateGrupoAcademico(GrupoAcademicoModel grupo) throws SQLException;

}
