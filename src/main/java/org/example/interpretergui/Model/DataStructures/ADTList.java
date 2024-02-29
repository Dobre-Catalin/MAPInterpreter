package org.example.interpretergui.Model.DataStructures;

import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Value.BoolValue;
import  org.example.interpretergui.Model.Value.IntValue;

import java.util.Iterator;
import java.util.LinkedList;

public class ADTList<T> implements ListInterface<T>{
    LinkedList<T> list;

    public ADTList(){
        this.list = new LinkedList<>();
    }
    @Override
    public void add(T element) {
        this.list.add(element);
    }

    @Override
    public T pop() throws MyException {
        T element = this.list.pop();
        if(element == null){
            throw new MyException("List is empty!");
        }
        return element;
    }

    @Override
    public BoolValue isEmpty() {
        return new BoolValue(this.list.isEmpty());
    }

    @Override
    public LinkedList<T> getList() {
        return list;
    }

    @Override
    public T get(IntValue index) throws MyException {
        if(index.getValue() < 0 || index.getValue() >= this.list.size()){
            throw new MyException("Index out of bounds!");
        }
        return this.list.get(index.getValue());
    }

    @Override
    public IntValue size() {
        return new IntValue(this.list.size());
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    @Override
    public String toString() {
        String result = "{";
        for (T t : this.list) {
            result += t.toString() + "; ";
        }
        result += "}";
        return result;
    }

    @Override
    public ListInterface<T> deepCopy() {
        ListInterface<T> newList = new ADTList<>();
        for(T element : this.list){
            newList.add(element);
        }
        return newList;
    }
}
