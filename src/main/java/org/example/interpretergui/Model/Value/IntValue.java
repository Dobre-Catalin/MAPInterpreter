package org.example.interpretergui.Model.Value;

import org.example.interpretergui.Model.Type.IntType;
import org.example.interpretergui.Model.Type.Type;

import java.util.Objects;

public class IntValue implements Value {
    int val;

    public IntValue(int val) {
        this.val = val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }

    public int getValue() {
        return val;
    }

    public boolean equals(Object another) {
        if(another == null){
            return false;
        }
        if(!(another instanceof IntValue)){
            return false;
        }
        IntValue castInt = (IntValue) another;
        return this.val == castInt.getValue();
    }
}
