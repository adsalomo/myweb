package co.com.elenaschoolmodel.model;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonSetter;


/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:42:11
 */
public class GrupoAcademicoModel {
    private int id;
    private String codigo;
    private String nombreCorto;
    private String nombre;
    private String descripcion;
    private String codigoGrado;
    private String calendario;
    private String personaResponsable;
    private boolean isActivo;
    private String usuario;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaProceso;

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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the codigoGrado
     */
    public String getCodigoGrado() {
        return codigoGrado;
    }

    /**
     * @param codigoGrado the codigoGrado to set
     */
    @JsonSetter("codigo_grado")
    public void setCodigoGrado(String codigoGrado) {
        this.codigoGrado = codigoGrado;
    }

    /**
     * @return the calendario
     */
    public String getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(String calendario) {
        this.calendario = calendario;
    }

    /**
     * @return the personaResponsable
     */
    public String getPersonaResponsable() {
        return personaResponsable;
    }

    /**
     * @param personaResponsable the personaResponsable to set
     */
    @JsonSetter("persona_responsable")
    public void setPersonaResponsable(String personaResponsable) {
        this.personaResponsable = personaResponsable;
    }

    /**
     * @return the isActivo
     */
    public boolean isIsActivo() {
        return isActivo;
    }

    /**
     * @param isActivo the isActivo to set
     */
    @JsonSetter("activo")
    public void setIsActivo(boolean isActivo) {
        this.isActivo = isActivo;
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
    
    
	
}