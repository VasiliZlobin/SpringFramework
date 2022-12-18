package ru.vasili_zlobin.springdatajpa.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class ValidateException extends RuntimeException{
    private final List<String> messages;

    public ValidateException(List<String> messages) {
        super(messages.stream().collect(Collectors.joining("/n")));
        this.messages = messages;
    }

    public List<String> getErrorMessages() {
        return messages;
    }
}
