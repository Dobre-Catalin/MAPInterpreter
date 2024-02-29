package org.example.interpretergui.Model.Statements;


import javafx.util.Pair;
import org.example.interpretergui.Model.DataStructures.ADTDictionary;
import org.example.interpretergui.Model.DataStructures.ADTHeap;
import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.SemaphoreTableInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.IntType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.IntValue;
import org.example.interpretergui.Model.Value.Value;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphoreStatement implements StatementInterface{
    private final String var;
    private final ExpressionInterface expression;
    private static final Lock lock = new ReentrantLock();

    public CreateSemaphoreStatement(String var, ExpressionInterface expression) {
        this.var = var;
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();
        DictionaryInterface<String, Value> symTable = state.getSymbolTable();
        ADTHeap heap = (ADTHeap) state.getHeap();
        SemaphoreTableInterface semaphoreTable = state.getSemaphoreTable();
        if(semaphoreTable == null){
           System.out.println("semaphoreTable is null in createSemaphoreStatement");
        }
        if(!expression.evaluate(symTable, heap).getType().equals(new IntType())){
            throw new MyException("Asd");
        }
        IntValue nr = (IntValue) (expression.evaluate(symTable, heap));
        int number = nr.getValue();
        int freeAddress = semaphoreTable.getFreeAddress();
        semaphoreTable.put(freeAddress, new Pair<>(number, new ArrayList<>()));
        if (symTable.containsKey(var).getValue() && symTable.getValue(var).getType().equals(new IntType()))
            symTable.update(var, new IntValue(freeAddress));
        else
            throw new MyException(String.format("Error for variable %s: not defined/does not have int type!", var));
        lock.unlock();
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        if (typeEnv.getValue(var).equals(new IntType())) {
            if (expression.typecheck(typeEnv).equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("Expression is not of int type!");
        } else {
            throw new MyException(String.format("%s is not of type int!", var));
        }
    }

    @Override
    public StatementInterface deepCopy() {
        return new CreateSemaphoreStatement(var, expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("createSemaphore(%s, %s)", var, expression);
    }
}
