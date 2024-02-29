package org.example.interpretergui.Model.Type;

import org.example.interpretergui.Model.Value.Value;

public interface Type {
    public boolean equals(Type another);
    public Value getDefault();
    public String toString();
}
