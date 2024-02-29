package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.StackInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.BoolType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.BoolValue;
import org.example.interpretergui.Model.Value.Value;

import java.util.Stack;

public class IfStatement implements StatementInterface{
    private final ExpressionInterface expression;
    private final StatementInterface ifStatement;
    private final StatementInterface elseStatement;

    public IfStatement(ExpressionInterface expression, StatementInterface ifStatement, StatementInterface elseStatement){
        this.expression = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public StatementInterface deepCopy() {
        return new IfStatement(this.expression.deepCopy(), this.ifStatement.deepCopy(), this.elseStatement.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        Value value = this.expression.evaluate(state.getSymbolTable(), state.getHeap());
        if(!value.getType().equals(new BoolType())){
            throw new MyException("Condition is not a boolean");
        }
        boolean boolValue = ((BoolValue) value).getValue();
        if(boolValue){
            stack.push(this.ifStatement);
        }
        else{
            stack.push(this.elseStatement);
        }
        return null;
    }

    @Override
    public String toString() {
        return "(IF(" + expression .toString() + ") THEN (" + ifStatement.toString() +
                ") ELSE(" + elseStatement.toString()+"))";
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException{
        Type typexp=expression.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            ifStatement.typecheck(typeEnv.deepCopy());
            elseStatement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }
}
