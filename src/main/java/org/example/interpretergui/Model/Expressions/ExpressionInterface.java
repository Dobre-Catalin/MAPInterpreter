package org.example.interpretergui.Model.Expressions;

import  org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import  org.example.interpretergui.Model.DataStructures.HeapInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Type.Type;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.Value;

public interface ExpressionInterface {
    ExpressionInterface deepCopy();
    Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws MyException;

    Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException;
}
