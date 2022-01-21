package br.com.alura.control.financeiro.validations;

import org.springframework.http.HttpStatus;

import br.com.alura.control.financeiro.exceptions.BusinessException;

public enum Message {
    
    IS_PRESENT_EXPENSE("Despesa já cadastrada",HttpStatus.BAD_REQUEST),
    IS_PRESENT_REVENUE("Receita já cadastrada",HttpStatus.BAD_REQUEST),
    NOT_FOUND_REVENUE("Receita não encontrado",HttpStatus.NOT_FOUND),
    NOT_FOUND_EXPENSE("Despesa não encontrado", HttpStatus.NOT_FOUND);

    private String value;
    private String description;
    private HttpStatus statusCode;

    private Message(String value, HttpStatus statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }

    private Message(String value, String description, HttpStatus statusCode) {
        this.value = value;
        this.description = description;
        this.statusCode = statusCode;
    }

    private Message(String value) {
        this.value = value;
    }

    public String getMessage() {
        return this.value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus())
                .code(String.valueOf(this.getStatus().value())).message(this.getMessage())
                .description(this.getDescription()).build();
    }
}
