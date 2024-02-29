package org.example.interpretergui.Model.Value;

import org.example.interpretergui.Model.Type.StringType;
import org.example.interpretergui.Model.Type.Type;

public class StringValue implements Value{
    String value;

    public StringValue(String value){
        this.value = value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(value);
    }

    public String getValue(){
        return this.value;
    }

    public boolean equals(Object another){
        if(another == null){
            return false;
        }
        if(!(another instanceof StringValue)){
            return false;
        }
        StringValue castString = (StringValue) another;
        return this.value.equals(castString.getValue());
    }
    public void add(StringValue another){
        this.value = this.value + another.getValue();
    }

    @Override
    public String toString(){
        return this.value;
    }
}
