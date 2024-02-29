package org.example.interpretergui.Controller;

import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Value.BoolValue;
///import com.sun.jdi.BooleanValue;

//import javax.sound.sampled.BooleanControl;

public interface ControllerInterface {
    void allSteps() throws MyException;
    void setDisplayFlag(BoolValue val);
    void addProgram(ProgramState program);
}
