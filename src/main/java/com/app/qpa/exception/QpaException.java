package com.app.qpa.exception;

/**
 * 稽核异常
 * unchecked exception
 *
 */
public class QpaException extends  RuntimeException{

    private String code;
    private String message;

    public QpaException(){
        super();
    }

    public QpaException(String message){
        super(message);
        this.message = message;
    }

    public QpaException(String code,String message){
        super(message);
        this.code = code;
        this.message = message;
    }

}
