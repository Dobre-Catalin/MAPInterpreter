package org.example.interpretergui.Model.Value;

import org.example.interpretergui.Model.Type.BoolType;
import org.example.interpretergui.Model.Type.Type;

public class BoolValue implements Value {
    private boolean val;

    public BoolValue (boolean val) {
        this.val = val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    public boolean getValue() {
        return val;
    }

    @Override
    public String toString() {
        return Boolean.toString(val);
    }

    @Override
    public boolean equals(Object another) {
        if (another == null){
            return false;
        }
        if (another instanceof BoolValue){
            return ((BoolValue) another).getValue() == val;
        }
        else {
            return false;
        }
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }
}
