package ru.nikola.diploma.cloudstorageapplication.exception;

public class ServerFail extends RuntimeException {
    public ServerFail(String msg) {
        super(msg);
    }
}
