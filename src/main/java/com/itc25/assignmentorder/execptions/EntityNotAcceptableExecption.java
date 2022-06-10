package com.itc25.assignmentorder.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotAcceptableExecption extends ResponseStatusException {

    public EntityNotAcceptableExecption(String reason){
        super(HttpStatus.NOT_ACCEPTABLE, reason);
    }


}
