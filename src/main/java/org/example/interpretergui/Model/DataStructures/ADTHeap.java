package org.example.interpretergui.Model.DataStructures;

import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Value.BoolValue;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.Value;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ADTHeap implements HeapInterface{
    Integer firstFree;
    Map<Integer, Value> dictionary;

    public ADTHeap(){
        this.firstFree = 1;
        this.dictionary = new HashMap<>();
    }

    @Override
    public Integer add(Value value) throws MyException {
        this.dictionary.put(this.firstFree, value);
        this.firstFree = this.firstFree + 1;
        return this.firstFree - 1;
    }

    @Override
    public BoolValue containsKey(Integer key) {
        if (this.dictionary.containsKey(key))
            return new BoolValue(true);
        else return new BoolValue(false);
    }

    @Override
    public BoolValue isEmpty() {
        return firstFree == 1 ? new BoolValue(true) : new BoolValue(false);
    }

    @Override
    public void update(Integer key, Value value) throws MyException {
        if (!this.dictionary.containsKey(key))
            throw new MyException("Key " + key + " is not in the dictionary");
        this.dictionary.put(key, value);
    }

    @Override
    public void remove(Integer key) throws MyException {
        if (!this.dictionary.containsKey(key))
            throw new MyException("Key " + key + " is not in the dictionary");
        this.dictionary.remove(key);
    }

    @Override
    public Value getValue(Integer key) throws MyException {
        if (!this.dictionary.containsKey(key))
            throw new MyException("Key " + key + " is not in the dictionary");
        return this.dictionary.get(key);
    }

    @Override
    public HashMap<Integer, Value> getDictionary() {
        return (HashMap<Integer, Value>) this.dictionary;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        Iterator<Map.Entry<Integer, Value>> it = this.dictionary.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Value> pair = it.next();
            result.append(pair.getKey()).append(" -> ").append(pair.getValue()).append(", ");
        }
        result.append("}");
        return result.toString();
    }

    @Override
    public HeapInterface deepCopy() {
        HeapInterface copy = new ADTHeap();
        for (Map.Entry<Integer, Value> pair : this.dictionary.entrySet()) {
            try {
                copy.add(pair.getValue());
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        return copy;
    }
}
