package br.com.treinaweb.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class NotFoundObjectException extends EntityNotFoundException  {

   
    public NotFoundObjectException (String message){
        super(message);
    }
    

}
