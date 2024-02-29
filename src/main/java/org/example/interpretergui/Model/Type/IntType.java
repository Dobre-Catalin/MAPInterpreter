package org.example.interpretergui.Model.Type;

import org.example.interpretergui.Model.Value.Value;
import org.example.interpretergui.Model.Value.IntValue;

public class IntType implements Type{
    @Override
    public boolean equals(Type another) {
        return another instanceof IntType;
    }

    @Override
    public Value getDefault() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "int_val";
    }
}
