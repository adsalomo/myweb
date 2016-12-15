package co.com.elenaschoolmodel.model;

/**
 * Clase maestra para responder las solicitudes del Cliente Web
 * @since 15 Diciembre de 2016
 * @author AdrianL
 */
public class ActionResponse {
    private String response;
    private String token;
    private ErrorRequest error;
    private boolean status;

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the error
     */
    public ErrorRequest getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(ErrorRequest error) {
        this.error = error;
    }

    /**
     * @return the Status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param status the Status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
