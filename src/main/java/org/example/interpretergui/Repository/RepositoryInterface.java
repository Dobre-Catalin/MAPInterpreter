package org.example.interpretergui.Repository;

import  org.example.interpretergui.Model.DataStructures.ListInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.ProgramState.ProgramState;
import  org.example.interpretergui.Model.Value.StringValue;

public interface  RepositoryInterface {
    void addProgram(ProgramState program);
    void logProgramExecution(ProgramState currentProgram) throws MyException;

    ListInterface<ProgramState> getProgramList();

    void setProgramList (ListInterface<ProgramState> newList);

    ListInterface<ProgramState> getRepo();
}
