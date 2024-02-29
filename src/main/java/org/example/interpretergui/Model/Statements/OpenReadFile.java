package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.StringValue;
import org.example.interpretergui.Model.Value.Value;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class OpenReadFile implements StatementInterface{
    private final ExpressionInterface expression;
    public OpenReadFile(ExpressionInterface expression){
        this.expression = expression;
    }

    @Override
    public StatementInterface deepCopy() {
        return new OpenReadFile(this.expression.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value filename = this.expression.evaluate(state.getSymbolTable(), state.getHeap());
        DictionaryInterface<String, BufferedReader> table = state.getFileTable();
        ///try to cast filename to StringValue
        if (!filename.getType().equals(new StringValue("").getType())){
            throw new MyException("Filename is not a string");
        }
        String stringValue = filename.toString();
        if (table.containsKey(stringValue).getValue()){
            throw new MyException("File already opened");
        }
        else{
            //try to open file
            try{
                BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(stringValue));
                table.add(stringValue, bufferedReader);
            }
            catch (Exception exception){
                throw new MyException(exception.getMessage());
            }
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
