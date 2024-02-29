package org.example.interpretergui.Model.Statements;

import javafx.util.Pair;
import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.SemaphoreTableInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.IntType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.IntValue;
import org.example.interpretergui.Model.Value.Value;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements StatementInterface{
    private final String var;
    private static final Lock lock = new ReentrantLock();

    public AcquireStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();
        DictionaryInterface<String, Value> symTable = state.getSymbolTable();
        SemaphoreTableInterface semaphoreTable = state.getSemaphoreTable();
        if (symTable.containsKey(var).getValue()) {
            if (symTable.getValue(var).getType().equals(new IntType())){
                IntValue fi = (IntValue) symTable.getValue(var);
                int foundIndex = fi.getValue();
                if (semaphoreTable.getSemaphoreTable().containsKey(foundIndex)) {
                    Pair<Integer, List<Integer>> foundSemaphore = semaphoreTable.get(foundIndex);
                    int NL = foundSemaphore.getValue().size();
                    int N1 = foundSemaphore.getKey();
                    if (N1 > NL) {
                        if (!foundSemaphore.getValue().contains(state.getId())) {
                            foundSemaphore.getValue().add(state.getId());
                            semaphoreTable.update(foundIndex, new Pair<>(N1, foundSemaphore.getValue()));
                        }
                    } else {
                        state.getExecutionStack().push(this);
                    }
                } else {
                    throw new MyException("Index not a key in the semaphore table!");
                }
            } else {
                throw new MyException("Index must be of int type!");
            }
        } else {
            throw new MyException("Index not in symbol table!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        if (typeEnv.getValue(var).equals(new IntType())) {
            return typeEnv;
        } else {
            throw new MyException(String.format("%s is not int!", var));
        }
    }

    @Override
    public StatementInterface deepCopy() {
        return new AcquireStatement(var);
    }

    @Override
    public String toString() {
        return String.format("acquire(%s)", var);
    }
}
