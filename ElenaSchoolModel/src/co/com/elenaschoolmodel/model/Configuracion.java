/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschoolmodel.model;

/**
 *
 * @author AdrianL
 */
public class Configuracion {
    private int numberRegistersXPage;
    private String connectionFilePath;
    private String loggingFilePath;

    /**
     * @return the numberRegisterPage
     */
    public int getNumberRegisterXPage() {
        return getNumberRegistersXPage();
    }

    /**
     * @param numberRegistersXPage the numberRegisterPage to set
     */
    public void setNumberRegisterXPage(int numberRegistersXPage) {
        this.setNumberRegistersXPage(numberRegistersXPage);
    }

    /**
     * @return the connectionFilePath
     */
    public String getConnectionFilePath() {
        return connectionFilePath;
    }

    /**
     * @param connectionFilePath the connectionFilePath to set
     */
    public void setConnectionFilePath(String connectionFilePath) {
        this.connectionFilePath = connectionFilePath;
    }

    /**
     * @return the numberRegistersXPage
     */
    public int getNumberRegistersXPage() {
        return numberRegistersXPage;
    }

    /**
     * @param numberRegistersXPage the numberRegistersXPage to set
     */
    public void setNumberRegistersXPage(int numberRegistersXPage) {
        this.numberRegistersXPage = numberRegistersXPage;
    }

    /**
     * @return the loggingFilePath
     */
    public String getLoggingFilePath() {
        return loggingFilePath;
    }

    /**
     * @param loggingFilePath the loggingFilePath to set
     */
    public void setLoggingFilePath(String loggingFilePath) {
        this.loggingFilePath = loggingFilePath;
    }
    
    
}
