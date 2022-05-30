package alisa.aniskina.testtaskchat.entity;

import Enums.Status;

public class StatusMessage {

    private Status status;
    private String textError;


    public StatusMessage(Status status, String textError) {
        this.status = status;
        this.textError = textError;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTextError() {
        return textError;
    }

    public void setTextError(String textError) {
        this.textError = textError;
    }
}
