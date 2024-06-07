package br.com.treinaweb.ediaristas.core.exceptions;

import org.springframework.validation.FieldError;

public class UsuarioJaCadastradoException extends ValidacaoExceptions {

    public UsuarioJaCadastradoException(String message, FieldError fieldErrors) {
        super(message, fieldErrors);
       
    }

}
