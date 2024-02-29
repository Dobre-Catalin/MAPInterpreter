package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.StackInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.Type;

public class CompoundStatement implements StatementInterface{
    private final StatementInterface first;
    private final StatementInterface second;

    public CompoundStatement(StatementInterface first, StatementInterface second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public StatementInterface deepCopy() {
        return new CompoundStatement(this.first.deepCopy(), this.second.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        stack.push(this.second);
        stack.push(this.first);
        return null;
    }

    @Override
    public String toString() {
        return "("+this.first.toString() + ";" + this.second.toString()+")";
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
