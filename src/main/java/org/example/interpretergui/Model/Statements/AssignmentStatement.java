package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.Value;

public class AssignmentStatement implements StatementInterface{
    private final String id;
    private final ExpressionInterface expression;

    public AssignmentStatement(String id, ExpressionInterface exp) {
        this.id = id;
        this.expression = exp;
    }

    @Override
    public StatementInterface deepCopy() {
        return new AssignmentStatement(this.id, this.expression.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        if(!symbolTable.containsKey(this.id).getValue()){
            throw new MyException("Can't use undeclared variable");
        }
        Value value = this.expression.evaluate(symbolTable, state.getHeap());
        Type idType = symbolTable.getValue(this.id).getType();

        if(value.getType().equals(idType) == false){
            throw new MyException("Type of declared variable and expression don't match");
        }
        symbolTable.update(this.id, value);
        return null;
    }
    @Override
    public String toString() {
        return this.id + "=" + this.expression.toString();
    }

    @Override
    public DictionaryInterface <String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        if(!typeEnv.containsKey(this.id).getValue()){
            throw new MyException("Variable not declared");
        }
        return typeEnv;
    }
}
