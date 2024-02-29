package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.ADTHeap;
import org.example.interpretergui.Model.DataStructures.ADTStack;
import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.StackInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.BoolType;
import org.example.interpretergui.Model.Type.Type;

public class ForkStatement implements StatementInterface{
    StatementInterface statement;

    public ForkStatement(StatementInterface statement){
        this.statement = statement;
    }


    @Override
    public StatementInterface deepCopy() {
        return new ForkStatement(this.statement.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> newStack = new ADTStack<>();
        return new ProgramState(newStack,
                state.getSymbolTable(),
                state.getOutput(),
                this.statement,
                state.getFileTable(),
                (ADTHeap) state.getHeap(), state.getSemaphoreTable());
    }

    @Override
    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException{
        statement.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }
}
