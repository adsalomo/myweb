package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschooldataaccess.persistencia.model.GrupoAcademicoModel;

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
    public boolean deleteGrupoAcademico(GrupoAcademicoModel grupo);

    /**
     *
     * @param grupo
     */
    public boolean insertGrupoAcademico(GrupoAcademicoModel grupo);

    /**
     * 
     * @return
     * @throws SQLException 
     */
    public List<GrupoAcademicoModel> readGrupoAcademico();

    /**
     *
     * @param grupo
     */
    public boolean updateGrupoAcademico(GrupoAcademicoModel grupo);

}
