package org.example.interpretergui.Model.ProgramState;

import org.example.interpretergui.Model.DataStructures.*;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Statements.StatementInterface;
import org.example.interpretergui.Model.Value.Value;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ProgramState {
    private final ADTHeap heap;
    private StackInterface<StatementInterface> executionStack;
    private DictionaryInterface<String, Value> symbolTable;
    private ListInterface<Value> output;
    private StatementInterface originalProgram;
    private DictionaryInterface<String, BufferedReader> fileTable;

    private SemaphoreTableInterface semaphoreTable;

    private int id;

    private static ArrayList<Integer> ids = new ArrayList<>();

    public ProgramState(StackInterface<StatementInterface> executionStack){
        this.executionStack = executionStack;
        this.symbolTable = new ADTDictionary<>();
        this.output = new ADTList<>();
        this.fileTable = new ADTDictionary<>();
        this.heap = new ADTHeap();
        //id is max +1 from ids
        synchronized (ids) {
            if (ids.isEmpty())
                this.id = 1;
            else {
                this.id = ids.get(ids.size() - 1) +1;
            }
            ids.add(this.id);
        }
        this.semaphoreTable = new SemaphoreTable();
    }

    public ProgramState(StackInterface<StatementInterface> executionStack,
                        DictionaryInterface<String, Value> symbolTable,
                        ListInterface<Value> output,
                        StatementInterface originalProgram,
                        DictionaryInterface<String, BufferedReader> fileTable,
                        ADTHeap heap, SemaphoreTableInterface semaphoreTable){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
        this.fileTable = fileTable;
        this.heap = heap;
        //id is max +1 from ids
        synchronized (ids) {
            if (ids.isEmpty())
                this.id = 1;
            else {
                this.id = ids.get(ids.size() - 1) +1;
            }
            ids.add(this.id);
        }
        this.semaphoreTable = semaphoreTable;
    }
    public HeapInterface getHeap(){
        return (HeapInterface) this.heap;
    }

    public StackInterface<StatementInterface> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(StackInterface<StatementInterface> executionStack) {
        this.executionStack = executionStack;
    }

    public DictionaryInterface<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(DictionaryInterface<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ListInterface<Value> getOutput() {
        return output;
    }

    public DictionaryInterface<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setOutput(ListInterface<Value> output) {
        this.output = output;
    }

    public StatementInterface getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(StatementInterface originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        if (semaphoreTable == null){
            return "{" +
                    "ID=" + id + "\n" +
                    "executionStack=" + executionStack.toString() + "\n" +
                    "symbolTable=" + symbolTable.toString() + "\n" +
                    "output=" + output.toString() + "\n" +
                    "fileTable=" + fileTable.toString() + "\n" +
                    "heap=" + heap.toString() + "\n" +
                    '}';
        }
        return "{" +
                "ID=" + id + "\n" +
                "executionStack=" + executionStack.toString() + "\n" +
                "symbolTable=" + symbolTable.toString() + "\n" +
                "output=" + output.toString() + "\n" +
                "fileTable=" + fileTable.toString() + "\n" +
                "heap=" + heap.toString() + "\n" +
                "semaphoreTable=" + semaphoreTable.toString() + "\n" +
                '}';
    }

    public boolean isNotCompleted(){
        return !(this.executionStack.isEmpty().getValue());
    }

    public ProgramState oneStep() throws MyException {
        /*
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        if(stack.isEmpty().getValue()){
            throw new RuntimeException("Execution stack is empty");
        }
        StatementInterface currentStatement = stack.pop();
        ///System.out.println(currentStatement.toString());
        return currentStatement.execute(state);
         */

        if(executionStack.isEmpty().getValue()){
            throw new RuntimeException("Execution stack is empty");
        }
        else{
            StatementInterface currentStatement = executionStack.pop();
            return currentStatement.execute(this);
        }
    }

    public ProgramState deepCopy(){
        /*
        return new ProgramState(this.executionStack.deepCopy(),
                this.symbolTable.deepCopy(),
                this.output.deepCopy(),
                this.originalProgram.deepCopy(),
                this.fileTable.deepCopy(),
                (ADTHeap) this.heap.deepCopy(), this.semaphoreTable.;

         */
        return null;
    }

    public ArrayList<Integer> getIDs(){
        return ids;
    }

    public int getId() {
        return id;
    }

    public SemaphoreTableInterface getSemaphoreTable() {
        if(semaphoreTable == null){
            System.out.println("Semaphore table is null");
        }
        return semaphoreTable;
    }
}
