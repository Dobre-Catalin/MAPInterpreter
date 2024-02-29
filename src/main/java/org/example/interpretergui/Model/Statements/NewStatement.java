package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.HeapInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.IntType;
import org.example.interpretergui.Model.Type.ReferenceType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.IntValue;
import org.example.interpretergui.Model.Value.ReferenceValue;
import org.example.interpretergui.Model.Value.StringValue;
import org.example.interpretergui.Model.Value.Value;

import java.nio.IntBuffer;

public class NewStatement implements StatementInterface{
    String variableName;
    ExpressionInterface expression;

    public NewStatement(String variableName, ExpressionInterface expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NewStatement(this.variableName, this.expression);
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        DictionaryInterface<String, Value> symTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        if (!symTable.containsKey(variableName).getValue())
            throw new MyException(String.format("ERROR: %s not in symTable", variableName));
        Value varValue = symTable.getValue(variableName);
        if (!(varValue.getType() instanceof ReferenceType))
            throw new MyException(String.format("ERROR: %s not of ReferenceType", variableName));
        Value evaluated = expression.evaluate(symTable, heap);
        Type locationType = ((ReferenceValue)varValue).getLocationType();
        if (!locationType.equals(evaluated.getType()))
            throw new MyException(String.format("ERROR: %s not of %s", variableName, evaluated.getType()));
        Integer newPosition = heap.add(evaluated);
        symTable.update(variableName, new ReferenceValue(newPosition, locationType));
        return null;
    }

    @Override
    public String toString() {
        return String.format("new(%s, %s)", variableName, expression.toString());
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type typeVariable = typeEnv.getValue(variableName);
        Type typeExpression = expression.typecheck(typeEnv);
        if (typeVariable.equals(new ReferenceType(typeExpression))){
            return typeEnv;
        }
        else{
            throw new MyException("New statement: right hand side and left hand side have different types");
        }
    }
}
