package com.itc25.assignmentorder.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundExecption extends ResponseStatusException {

    public EntityNotFoundExecption(String reason){
        super(HttpStatus.NOT_FOUND, reason);
    }

}
