package co.com.elenaschooldataaccess.persistencia.model;

import java.util.Date;

/**
 * Model tabla Periodo_Academico
 *
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:42:12
 */
public class PeriodoAcademicoModel {

    private boolean activo;
    private char calendario;
    private char codigo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaFin;
    private Date fechaInicio;
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
     * @return the calendario
     */
    public char getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(char calendario) {
        this.calendario = calendario;
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
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
    
}//end PeriodoAcademicoModel
