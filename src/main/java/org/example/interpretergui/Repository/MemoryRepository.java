package org.example.interpretergui.Repository;

import  org.example.interpretergui.Model.DataStructures.ADTList;
import  org.example.interpretergui.Model.DataStructures.ListInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.ProgramState.ProgramState;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.StringValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MemoryRepository implements RepositoryInterface{
    private ListInterface<ProgramState> programStates;

    public MemoryRepository(ProgramState prg, StringValue filename){
        this.programStates = new ADTList<>();
        this.programStates.add(prg);
    }
    @Override
    public ListInterface<ProgramState> getRepo(){
        return this.programStates;
    }

    public void logProgramExecution(ProgramState currentProgram) throws MyException{
        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter("l0g.txt", true)))){
            logFile.println("Program ID: " + currentProgram.toString());
            logFile.flush();
        }
        catch (IOException e){
            throw new MyException("Could not open log file");
        }
    }

    @Override
    public void addProgram(ProgramState program) {
        this.programStates.add(program);
    }

    @Override
    public void setProgramList(ListInterface<ProgramState> newList) {
        this.programStates = newList;
    }

    @Override
    public ListInterface<ProgramState> getProgramList(){
        return this.programStates;
    }

    @Override
    public String toString(){
        String result = "";
        for (ProgramState programState : this.programStates) {
            result += programState.toString();
            result += "\n";
        }
        return result;
    }
}
