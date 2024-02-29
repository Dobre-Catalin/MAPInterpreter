package org.example.interpretergui.Model.Type;

import org.example.interpretergui.Model.Value.StringValue;
import org.example.interpretergui.Model.Value.Value;

public class StringType implements Type{
    @Override
    public boolean equals(Type another) {
        return another instanceof StringType;
    }

    @Override
    public Value getDefault() {
        return new StringValue("\0");
    }

    @Override
    public String toString() {
        return "string";
    }
}
