package ru.kl.proj.customExceptions;

public class OrganizationExistException extends RuntimeException {
    public OrganizationExistException (String errorMessage) {
        super(errorMessage);
    }
}
