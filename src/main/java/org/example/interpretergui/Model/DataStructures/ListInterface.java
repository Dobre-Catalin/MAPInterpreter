package org.example.interpretergui.Model.DataStructures;


import  org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Value.BoolValue;
import  org.example.interpretergui.Model.Value.IntValue;

import java.util.LinkedList;

public interface ListInterface<T> extends Iterable<T>{

    void add(T element);

    T pop() throws MyException;

    BoolValue isEmpty();

    LinkedList<T> getList();

    T get(IntValue index) throws MyException;

    IntValue size();

    String toString();

    ListInterface<T> deepCopy();

}
