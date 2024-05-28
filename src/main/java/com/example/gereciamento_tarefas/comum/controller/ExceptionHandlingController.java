package com.example.gereciamento_tarefas.comum.controller;

import com.example.gereciamento_tarefas.comum.exception.NotFoundException;
import com.example.gereciamento_tarefas.comum.exception.ValidacaoException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public List<Message> notFoundError(NotFoundException ex) {
        return Collections.singletonList(new Message(ex.getMessage()));
    }

    @ExceptionHandler(ValidacaoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Message validacaoErrors(ValidacaoException ex) {
        return new Message(ex.getMessage());
    }

    @Data
    private static class Message {
        private String message;
        private String field;

        Message(String message) {
            this.message = message;
        }

    }
}
