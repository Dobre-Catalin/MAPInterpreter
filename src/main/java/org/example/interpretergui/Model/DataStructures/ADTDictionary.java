package org.example.interpretergui.Model.DataStructures;

import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Value.BoolValue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ADTDictionary<K, V> implements DictionaryInterface<K, V>{
    private HashMap<K, V> dictionary;

    public ADTDictionary() {
        this.dictionary = new HashMap<>();
    }

    @Override
    public void add(K key, V value) throws MyException {
        if(this.dictionary.containsKey(key))
            throw new MyException("Key "+key+" is already in the dictionary");
        this.dictionary.put(key, value);
    }

    @Override
    public BoolValue containsKey(K key) {
        return new BoolValue(this.dictionary.containsKey(key));
    }

    @Override
    public BoolValue isEmpty() {
        return new BoolValue(this.dictionary.isEmpty());
    }

    @Override
    public void update(K key, V value) throws MyException {
        if(!this.dictionary.containsKey(key))
            throw new MyException("Key "+key+" is not in the dictionary");
        this.dictionary.put(key, value);
    }

    @Override
    public void remove(K key) throws MyException {
        if(!this.dictionary.containsKey(key))
            throw new MyException("Key "+key+" is not in the dictionary");
        this.dictionary.remove(key);
    }

    @Override
    public V getValue(K key) throws MyException {
        if(!this.dictionary.containsKey(key))
            throw new MyException("Key "+key+" is not in the dictionary");
        return this.dictionary.get(key);
    }

    @Override
    public HashMap<K, V> getDictionary() {
        return this.dictionary;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return this.dictionary.entrySet().iterator();
    }

    @Override
    public String toString() {
        String result = "{";
        for (K key : this.dictionary.keySet()) {
            result += key.toString() + "->" + this.dictionary.get(key).toString() + "; ";
        }
        result += "}";
        return result;
    }

    @Override
    public DictionaryInterface<K,V> deepCopy() {
        DictionaryInterface<K,V> copy = new ADTDictionary<>();
        for (K key : this.dictionary.keySet()) {
            try {
                copy.add(key, this.dictionary.get(key));
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        return copy;
    }

    @Override
    public Set<K> keySet() {
        return this.dictionary.keySet();
    }
}
