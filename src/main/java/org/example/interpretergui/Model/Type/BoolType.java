package org.example.interpretergui.Model.Type;

import org.example.interpretergui.Model.Value.BoolValue;
import org.example.interpretergui.Model.Value.Value;

public class BoolType implements Type{
    @Override
    public boolean equals(Type another) {
        return another instanceof BoolType;
    }

    @Override
    public Value getDefault() {
        return new BoolValue(false);
    }

    @Override
    public String toString() {
        return "bool";
    }
}
