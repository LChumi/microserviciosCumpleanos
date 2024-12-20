package com.cumpleanos.core.models.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T>{
    private T data;
    private ErrorResponse error;

    public ApiResponse(T data){
        this.data = data;
    }
    public ApiResponse(ErrorResponse error){
        this.error = error;
    }
}