package org.example.interpretergui.Model.DataStructures;

import  org.example.interpretergui.Model.Value.BoolValue;
import  org.example.interpretergui.Model.Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface DictionaryInterface<K, V>  extends Iterable<Map.Entry<K,V>>{
    void add(K key, V value) throws MyException;

    BoolValue containsKey(K key);

    BoolValue isEmpty();

    void update(K key, V value) throws MyException;

    void remove(K key) throws MyException;

    V getValue(K key) throws MyException;

    HashMap<K,V> getDictionary();

    String toString();

    DictionaryInterface<K,V> deepCopy();

    public Set<K> keySet();
}
