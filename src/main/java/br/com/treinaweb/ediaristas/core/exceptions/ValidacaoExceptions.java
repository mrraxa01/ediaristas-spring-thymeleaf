package br.com.treinaweb.ediaristas.core.exceptions;

import org.springframework.validation.FieldError;

public class ValidacaoExceptions extends RuntimeException {

    private FieldError fieldErrors;

    public ValidacaoExceptions(String message, FieldError fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    public FieldError getFieldErrors() {
        return fieldErrors;
    }
    
    

}
