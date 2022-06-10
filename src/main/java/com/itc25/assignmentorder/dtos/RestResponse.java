package com.itc25.assignmentorder.dtos;

import lombok.Data;

@Data
public class RestResponse<T> {

    private final T data;
    private final String message;
    private final String status;
}
