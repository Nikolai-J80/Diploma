package ru.nikola.diploma.cloudstorageapplication.exception;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials(String msg) {
        super(msg);
    }
}
