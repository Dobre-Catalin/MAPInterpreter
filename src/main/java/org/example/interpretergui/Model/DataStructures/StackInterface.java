package org.example.interpretergui.Model.DataStructures;

import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Value.BoolValue;

import java.util.Stack;
public interface StackInterface <T> extends Iterable<T>{
    T pop() throws MyException;

    T peek() throws MyException;

    void push(T value);

    BoolValue isEmpty();

    Stack<T> getStack();

    String toString();

    StackInterface<T> deepCopy();
}
