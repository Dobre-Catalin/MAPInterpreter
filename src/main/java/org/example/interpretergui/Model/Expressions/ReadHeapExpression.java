package org.example.interpretergui.Model.Expressions;

import  org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import  org.example.interpretergui.Model.DataStructures.HeapInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Type.ReferenceType;
import  org.example.interpretergui.Model.Type.Type;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.ReferenceValue;
import  org.example.interpretergui.Model.Value.Value;

public class ReadHeapExpression implements ExpressionInterface{
    ExpressionInterface expression;

    public ReadHeapExpression(ExpressionInterface expression){
        this.expression = expression;
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ReadHeapExpression(this.expression.deepCopy());
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws MyException {

        Value value = this.expression.evaluate(table, heap);

            ///cast value to reference value
            ReferenceValue referenceValue = (ReferenceValue) value;

            int addressValue;
            addressValue = referenceValue.getAddress();
            Integer address = addressValue;
            if(heap.containsKey(address).getValue()){
                return heap.getValue(address);
            }
            else throw new MyException("Address not found in heap");
    }

    @Override
    public String toString() {
        return "readHeap(" + this.expression.toString() + ")";
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeTable) throws MyException {
        Type type = expression.typecheck(typeTable);
        if (type instanceof ReferenceType) {
            ReferenceType reference = (ReferenceType) type;
            return reference.getInner();
        } else
            throw new MyException("the ReadHeap argument is not a Reference Type");
    }
}
