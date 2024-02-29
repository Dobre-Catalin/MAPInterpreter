package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.HeapInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.ReferenceType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.IntValue;
import org.example.interpretergui.Model.Value.ReferenceValue;
import org.example.interpretergui.Model.Value.Value;
import org.example.interpretergui.Model.*;
public class WriteHeapStatement implements StatementInterface{
    String VariableName;
    ExpressionInterface expression;

    public WriteHeapStatement(String VariableName, ExpressionInterface expression){
        this.VariableName = VariableName;
        this.expression = expression;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WriteHeapStatement(this.VariableName, this.expression.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        HeapInterface heap = state.getHeap();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        ///check if the variable name is in the symbol table
        if(symbolTable.containsKey(this.VariableName).getValue()){
            ///check if the value associated to the variable name is a reference value
            Value value = symbolTable.getValue(this.VariableName);
            if(value.getType() instanceof ReferenceType){
                ///check if the address is in the heap
                ReferenceValue referenceValue = (ReferenceValue) value;
                Integer address = referenceValue.getAddress();
                if(heap.containsKey(address).getValue()){
                    ///evaluate the expression and check if the type of the expression is int
                    Value expressionValue = this.expression.evaluate(symbolTable, heap);
                    if(expressionValue.getType().equals(new  org.example.interpretergui.Model.Type.IntType())){
                        ///update the heap
                        heap.update(address, expressionValue);
                    }
                    else throw new MyException("Expression does not evaluate to an int value");
                }
                else throw new MyException("Address not found in heap");
            }
            else throw new MyException("Value associated to the variable name is not a reference value");
        }
        else throw new MyException("Variable name not found in symbol table");
    return null;
    }

    @Override
    public String toString() {
        return "writeHeap(" + this.VariableName + ", " + this.expression.toString() + ")";
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.getValue(VariableName);
        Type typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new ReferenceType(typexp)))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }
}
