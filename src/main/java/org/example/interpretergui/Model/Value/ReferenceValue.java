package org.example.interpretergui.Model.Value;

import org.example.interpretergui.Model.Type.ReferenceType;
import org.example.interpretergui.Model.Type.Type;

public class ReferenceValue implements Value{
    int adress;
    Type locationType;

    public ReferenceValue(int adress, Type locationType){
        this.adress = adress;
        this.locationType = locationType;
    }

    public int getAddress(){
        return adress;
    }

    public Type getLocationType(){
        return locationType;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new ReferenceValue(adress, locationType);
    }

    @Override
    public String toString() {
        return String.format("(%d, %s)", adress, locationType.toString());
    }
}
