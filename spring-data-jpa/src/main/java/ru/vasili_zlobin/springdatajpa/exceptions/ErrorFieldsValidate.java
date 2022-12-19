package ru.vasili_zlobin.springdatajpa.exceptions;

import java.util.List;

public class ErrorFieldsValidate {
    private final List<String> messages;

    public ErrorFieldsValidate(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
