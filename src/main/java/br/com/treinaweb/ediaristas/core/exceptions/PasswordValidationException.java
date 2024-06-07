package br.com.treinaweb.ediaristas.core.exceptions;

import org.springframework.validation.FieldError;

public class PasswordValidationException extends ValidacaoExceptions  {

    public PasswordValidationException (String message, FieldError fieldsErrors){
        super(message, fieldsErrors);
    }
}
