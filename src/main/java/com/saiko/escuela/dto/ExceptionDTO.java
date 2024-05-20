package com.saiko.escuela.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
    private String error;
    private String message;
    private int status;
    private String date;
}
