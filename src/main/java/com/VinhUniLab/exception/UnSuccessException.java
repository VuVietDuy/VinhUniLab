package com.VinhUniLab.exception;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnSuccessException extends RuntimeException {

    private String message;
    private Integer code;

    public UnSuccessException(String message, Integer code){
        super(message);
        this.message = message;
        this.code = code;
    }

    public UnSuccessException(String message){
        super(message);
        this.message = message;
        this.code = 500;
    }
}
