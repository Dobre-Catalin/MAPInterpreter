package org.example.interpretergui.Model.Expressions;

import  org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import  org.example.interpretergui.Model.DataStructures.HeapInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Type.Type;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.Value;

public class VariableExpression implements ExpressionInterface{
    Value e;
    private final String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new VariableExpression(this.id);
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws MyException {
        return table.getValue(id);
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        return typeEnv.getValue(id);
    }
}
