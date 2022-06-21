package com.general.template.common.exception;

public class NotFoundException extends Exception {
    private String message;
    private String page;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotFoundException(String message,String page) {
        super(message);
        this.message = message;
        this.page = page;
    }

    public NotFoundException() {
        super();
    }

    public String getErrorMessage() {
        return message;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}