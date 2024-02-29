package org.example.interpretergui.View;

import  org.example.interpretergui.Model.Statements.StatementInterface;
import  org.example.interpretergui.Model.Value.BoolValue;

import java.util.ArrayList;

public interface ViewInterface {
    void mainMenu();
    void run(ArrayList<StatementInterface> programs, int typeToRun);
    ArrayList<StatementInterface> selectProgram();
    void setDisplayFlag(BoolValue value);
}
