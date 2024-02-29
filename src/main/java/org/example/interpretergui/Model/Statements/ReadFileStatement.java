package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.StringType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.StringValue;
import org.example.interpretergui.Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements StatementInterface{
    ExpressionInterface expression;
    String variableName;

    public ReadFileStatement(ExpressionInterface expression, String variableName){
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ReadFileStatement(this.expression.deepCopy(), this.variableName);
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if (this.variableName == null){
            throw new MyException("Variable name is null");
        }
        if (!state.getSymbolTable().containsKey(this.variableName).getValue()){
            throw new MyException("Variable name is not in symbol table");
        }
        Value filename = this.expression.evaluate(state.getSymbolTable(), state.getHeap());
        DictionaryInterface<String, BufferedReader> table = state.getFileTable();
        if (!filename.getType().equals(new StringValue("").getType())){
            throw new MyException("Filename is not a string");
        }
        String filenameS = this.expression.evaluate(state.getSymbolTable(), state.getHeap()).toString();
        if (!state.getFileTable().containsKey(filenameS).getValue()){
            throw new MyException("File is not in file table when reading");
        }
        else{
            try{
                DictionaryInterface<String, BufferedReader> dict= state.getFileTable();
                BufferedReader reader = dict.getValue(filenameS);
                String line = reader.readLine();
                int intRead;
                if (line == null){
                    intRead =0;
                }
                else{
                    intRead = Integer.parseInt(line);
                }
                state.getSymbolTable().update(this.variableName, new StringValue(Integer.toString(intRead)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type typexp = expression.typecheck(typeEnv);
        if (typexp.equals(new StringType())) {
            return typeEnv;
        }
        else
            throw new MyException("ReadFile: right hand side and left hand side have different types ");
    }
}
