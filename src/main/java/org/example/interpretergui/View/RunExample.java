package org.example.interpretergui.View;

import org.example.interpretergui.Controller.Controller;
public class RunExample extends Command {
    private Controller ctr;

    public RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try{
            ctr.allSteps();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}