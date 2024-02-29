package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.StringValue;

public class CloseReadFileStatement implements StatementInterface {
    ExpressionInterface expression;
    public CloseReadFileStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        String filename = this.expression.evaluate(state.getSymbolTable(), state.getHeap()).toString();
        if(!state.getFileTable().containsKey(filename).getValue()){
            throw new MyException("File is not opened");
        }
        else{
            state.getFileTable().remove(filename);
        }
        return null;
    }

    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
