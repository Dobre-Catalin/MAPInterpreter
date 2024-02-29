package org.example.interpretergui.Model.DataStructures;

import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Value.BoolValue;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.Value;

import java.util.HashMap;

public interface HeapInterface {
    Integer add(Value value) throws MyException;

    ///BoolValue containsKey(K key);

    BoolValue isEmpty();

    void update(Integer key, Value value) throws MyException;

    void remove(Integer key) throws MyException;

    Value getValue(Integer key) throws MyException;

    HashMap<Integer,Value> getDictionary();

    String toString();

    BoolValue containsKey(Integer key);

    HeapInterface deepCopy();
}
