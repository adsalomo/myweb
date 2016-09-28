package co.com.elenaschoolmodel.model;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonSetter;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:42:07
 */
public class CalendarioModel {

    private boolean activo;
    private int ano;
    private String codigo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaFin;
    private Date fechaInicio;
    private Date fechaModificacion;
    private Date fechaProceso;
    private int id;
    private String nombre;
    private String nombreCorto;
    private int numeroPeriodos;
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
    @JsonSetter("activo")
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    @JsonSetter("ano")
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    @JsonSetter("codigo")
    public void setCodigo(String codigo) {
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
    @JsonSetter("descripcion")
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
    @JsonSetter("fecha_creacion")
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
    @JsonSetter("fecha_fin")
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
    @JsonSetter("fecha_inicio")
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
    @JsonSetter("fecha_modificacion")
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
    @JsonSetter("fecha_proceso")
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
    @JsonSetter("id")
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
    @JsonSetter("nombre")
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
    @JsonSetter("nombre_corto")
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    /**
     * @return the numeroPeriodos
     */
    public int getNumeroPeriodos() {
        return numeroPeriodos;
    }

    /**
     * @param numeroPeriodos the numeroPeriodos to set
     */
    @JsonSetter("numero_periodos")
    public void setNumeroPeriodos(int numeroPeriodos) {
        this.numeroPeriodos = numeroPeriodos;
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
    @JsonSetter("usuario")
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}//end CalendarioModel
