package org.example.interpretergui.Model.Value;

import org.example.interpretergui.Model.Type.Type;

public interface Value {
    Type getType();

    Value deepCopy();
}
