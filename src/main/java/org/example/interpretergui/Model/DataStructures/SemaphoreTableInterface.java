package org.example.interpretergui.Model.DataStructures;

import  org.example.interpretergui.Model.Exceptions.MyException;

import javafx.util.Pair;


import java.util.HashMap;
import java.util.List;

public interface SemaphoreTableInterface {
    void put(int key, Pair<Integer, List<Integer>> value) throws MyException, MyException;
    Pair<Integer, List<Integer>> get(int key) throws MyException, MyException;
    boolean containsKey(int key);
    int getFreeAddress();
    void setFreeAddress(int freeAddress);
    void update(int key, Pair<Integer, List<Integer>> value) throws MyException, MyException;
    HashMap<Integer, Pair<Integer, List<Integer>>> getSemaphoreTable();
    void setSemaphoreTable(HashMap<Integer, Pair<Integer, List<Integer>>> newSemaphoreTable);
}
