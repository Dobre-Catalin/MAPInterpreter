package org.example.interpretergui.Model.Type;

import org.example.interpretergui.Model.Value.ReferenceValue;
import org.example.interpretergui.Model.Value.StringValue;
import org.example.interpretergui.Model.Value.Value;

public class ReferenceType implements Type{
    Type inner;
    public ReferenceType(Type inner){
        this.inner = inner;
    }



    @Override
    public boolean equals(Type another){
        if(another instanceof ReferenceType){
            return inner.equals(((ReferenceType) another).getInner());
        }
        else
            return false;
    }

    @Override
    public Value getDefault() {
            return new ReferenceValue(0, inner);
    }

    public Type getInner(){
        return this.inner;
    }

    @Override
    public String toString(){
        return "Reference(" + inner.toString() + ")";
    }
}
