package org.example.interpretergui.Model.Exceptions;

public class MyException extends Exception{
    String message;

    public MyException(String message) {
        super(message);
    }
}
