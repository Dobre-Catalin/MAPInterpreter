package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.ListInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.Value;

public class PrintStatement implements StatementInterface{
    private final ExpressionInterface expression;

    public PrintStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public StatementInterface deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ListInterface<Value> out = state.getOutput();
        out.add(expression.evaluate(state.getSymbolTable(), state.getHeap()));
        return null;
    }

    @Override
    public String toString() {
        return "print(" +expression.toString()+")";
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }
}
