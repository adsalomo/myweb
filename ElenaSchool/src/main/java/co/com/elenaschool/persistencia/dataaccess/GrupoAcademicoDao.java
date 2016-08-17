package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.GrupoAcademicoModel;
import co.com.elenaschool.persistencia.contract.IGrupoAcademicoDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:39
 */
public class GrupoAcademicoDao implements IGrupoAcademicoDao {

    public GrupoAcademicoDao() {

    }

    /**
     *
     * @param grupo
     */
    public boolean deleteGrupoAcademico(GrupoAcademicoModel grupo) {
        return false;
    }

    /**
     *
     * @param grupo
     */
    public boolean insertGrupoAcademico(GrupoAcademicoModel grupo) {
        return false;
    }

    /**
     *
     * @return
     */
    public List<GrupoAcademicoModel> readGrupoAcademico() {
        return null;
    }

    /**
     *
     * @param grupo
     */
    public boolean updateGrupoAcademico(GrupoAcademicoModel grupo) {
        return false;
    }
}//end GrupoAcademicoDao
