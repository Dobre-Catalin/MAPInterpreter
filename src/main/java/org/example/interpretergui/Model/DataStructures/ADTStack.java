package org.example.interpretergui.Model.DataStructures;

import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Value.BoolValue;

import java.util.Iterator;
import java.util.Stack;

public class ADTStack<T> implements StackInterface<T>{
    private final Stack<T> stack;

    public ADTStack() {
        stack = new Stack<>();
    }

    @Override
    public T pop() throws MyException {
        BoolValue empty = this.isEmpty();
        if(empty.getValue()){
            throw new MyException("Stack is empty!");
        }
        return this.stack.pop();
    }

    @Override
    public T peek() throws MyException {
        BoolValue empty = this.isEmpty();
        if(empty.getValue()){
            throw new MyException("Stack is empty!");
        }
        return this.stack.peek();
    }

    @Override
    public void push(T value) {
        this.stack.push(value);
    }

    @Override
    public BoolValue isEmpty() {
        return new BoolValue(this.stack.isEmpty());
    }

    @Override
    public Stack<T> getStack() {
        return this.stack;
    }

    @Override
    public Iterator<T> iterator() {
        return this.stack.iterator();
    }

    @Override
    public String toString() {
        String result = "{";
        for (T t : this.stack) {
            result += t.toString() + "; ";
        }
        result += "}";
        return result;
    }

    @Override
    public StackInterface<T> deepCopy() {
        StackInterface<T> copy = new ADTStack<>();
        for (T t : this.stack) {
            copy.push(t);
        }
        return copy;
    }
}
