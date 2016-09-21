package co.com.elenaschoolmodel.model;

import java.util.Date;

/**
 * Model tabla Programa_Academico
 *
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:42:13
 */
public class ProgramaAcademicoModel {

    private boolean activo;
    private char codigo;
    private String codigoSnies;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaProceso;
    private int id;
    private String nombre;
    private String nombreCorto;
    private String usuario;

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the codigo
     */
    public char getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigoSnies
     */
    public String getCodigoSnies() {
        return codigoSnies;
    }

    /**
     * @param codigoSnies the codigoSnies to set
     */
    public void setCodigoSnies(String codigoSnies) {
        this.codigoSnies = codigoSnies;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the fechaProceso
     */
    public Date getFechaProceso() {
        return fechaProceso;
    }

    /**
     * @param fechaProceso the fechaProceso to set
     */
    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombreCorto
     */
    public String getNombreCorto() {
        return nombreCorto;
    }

    /**
     * @param nombreCorto the nombreCorto to set
     */
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}//end ProgramaAcademicoModel
