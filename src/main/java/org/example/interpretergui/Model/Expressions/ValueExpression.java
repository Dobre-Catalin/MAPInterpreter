package org.example.interpretergui.Model.Expressions;

import  org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import  org.example.interpretergui.Model.DataStructures.HeapInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Type.Type;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.Value;

public class ValueExpression implements ExpressionInterface{
    private final Value value;

    public ValueExpression(Value value){
        this.value = value;
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ValueExpression(this.value.deepCopy());
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws MyException {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        return this.value.getType();
    }
}
