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

    /**
     * @return the numberRegisterPage
     */
    public int getNumberRegisterXPage() {
        return numberRegistersXPage;
    }

    /**
     * @param numberRegistersXPage the numberRegisterPage to set
     */
    public void setNumberRegisterXPage(int numberRegistersXPage) {
        this.numberRegistersXPage = numberRegistersXPage;
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
    
    
}
