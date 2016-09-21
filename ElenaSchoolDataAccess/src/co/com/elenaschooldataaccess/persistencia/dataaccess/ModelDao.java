package co.com.elenaschooldataaccess.persistencia.dataaccess;

import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.helper.ModelHelper;
import co.com.elenaschooldataaccess.persistencia.model.Model;
import co.com.elenaschooltransverse.util.Util;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author AdrianL
 */
public class ModelDao implements IModelDao {

    private final JdbcTemplate jdbcTemplate;

    private final String sqlSelect = "SELECT "
            + "c.table_name NameTable, "
            + "c.column_name ColumnName, "
            + "(CASE WHEN substring(k.constraint_name FROM 1 FOR 2) LIKE 'pk' THEN '1'::bit ELSE '0'::bit END) AS IsPrimary, "
            + "(CASE WHEN substring(k.constraint_name FROM 1 FOR 2) LIKE 'fk' THEN '1'::bit ELSE '0'::bit END) AS IsForeign, "
            + "c.data_type DataType, "
            + "(CASE WHEN c.is_nullable LIKE 'NO' THEN '1'::bit ELSE '0'::bit END) AS IsNullable, "
            + "c.character_maximum_length Length, "
            + "c.numeric_precision_radix NumericPrecision, "
            + "("
            + "SELECT "
            + "pg_catalog.col_description(pg.oid, c.ordinal_position::int) "
            + "FROM pg_catalog.pg_class pg "
            + "WHERE "
            + "pg.oid = (SELECT c.table_name::regclass::oid) AND "
            + "pg.relname = c.table_name "
            + ") AS LabelName "
            + "FROM information_schema.columns c "
            + "LEFT JOIN information_schema.key_column_usage k ON c.table_name = k.table_name AND c.column_name = k.column_name "
            + "WHERE "
            + "c.table_schema LIKE 'public' "
            + "AND c.table_catalog LIKE 'elenaschool' "
            + "AND c.table_name LIKE 'grupo_academico' ";

    /**
     * constructor
     */
    public ModelDao() {
        jdbcTemplate = new JdbcTemplate(Util.getDataSource());
    }

    @Override
    public List<Model> readModel(Model model) {
        return jdbcTemplate.query(sqlSelect, new ModelHelper());
    }

}
