package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.Type;

public interface StatementInterface {
    public StatementInterface deepCopy();
    ProgramState execute(ProgramState state) throws MyException;

    DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException;
}
