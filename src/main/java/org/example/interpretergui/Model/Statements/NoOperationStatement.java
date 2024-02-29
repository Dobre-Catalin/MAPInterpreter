package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.Type;

public class NoOperationStatement implements StatementInterface{

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "no operation";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NoOperationStatement;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
