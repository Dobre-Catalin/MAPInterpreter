package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.Value;

public class VariableDeclarationStatement implements StatementInterface{
    private final String id;
    private final Type type;

    public VariableDeclarationStatement(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public StatementInterface deepCopy() {
        return new VariableDeclarationStatement(this.id, this.type);
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        if(symbolTable.containsKey(this.id).getValue()){
            throw new MyException("Variable already declared");
        }
        symbolTable.add(this.id, this.type.getDefault());
        return null;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.id;
    }

    @Override
    public DictionaryInterface <String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        typeEnv.add(id, type);
        return typeEnv;
    }
}
