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
public class ErrorRequest {
    private String message;
    private int errorCode;

    /**
     * @return the menssage
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the menssage to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    
    
}
