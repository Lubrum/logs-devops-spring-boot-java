package br.com.alura.logs.dto;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogsErrorDto {

    private List<String> errors;

    public LogsErrorDto(BindingResult result) {
        this.errors = new ArrayList<>();
        result.getAllErrors().forEach(error ->
                this.errors.add(error.getDefaultMessage()));
    }

    public LogsErrorDto(RuntimeException ex) {
        this.errors = Arrays.asList(ex.getMessage());
    }

    public List<String> getErrors() {
        return errors;
    }
}