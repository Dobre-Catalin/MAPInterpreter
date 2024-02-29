package org.example.interpretergui.Model.Expressions;

import  org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import  org.example.interpretergui.Model.DataStructures.HeapInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Type.BoolType;
import org.example.interpretergui.Model.Type.IntType;
import org.example.interpretergui.Model.Type.Type;
import  org.example.interpretergui.Model.Value.BoolValue;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.Value;

public class RelationalExpression implements ExpressionInterface{
    private final ExpressionInterface expression1;
    private final ExpressionInterface expression2;
    private final String operator;

    public RelationalExpression(ExpressionInterface expression1, ExpressionInterface expression2, String operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
        ///operators: 1 - <, 2 - <=, 3 - ==, 4 - !=, 5 - >, 6 - >=
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new RelationalExpression(this.expression1.deepCopy(), this.expression2.deepCopy(), this.operator);
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws MyException {
        Value value1 = this.expression1.evaluate(table, heap);
        Value value2 = this.expression2.evaluate(table, heap);
        if (!value1.getType().equals(value2.getType())){
            throw new MyException("Types of operands are not the same");
        }
        if (!value1.getType().equals(new IntValue(0).getType())){
            throw new MyException("Types of operands are not integers");
        }
        IntValue v1 = (IntValue) value1;
        IntValue v2 = (IntValue) value2;
           int n1 = v1.getValue();
              int n2 = v2.getValue();
        switch (this.operator){
            case "<":
                return new BoolValue(n1 < n2);
            case "<=":
                return new BoolValue(n1 <= n2);
            case "==":
                return new BoolValue(n1 == n2);
            case "!=":
                return new BoolValue(n1 != n2);
            case ">":
                return new BoolValue(n1 > n2);
            case ">=":
                return new BoolValue(n1 >= n2);
            default:
                throw new MyException("Invalid operator");
        }
    }
    @Override
    public String toString(){
        return this.expression1.toString() + this.operator + this.expression2.toString();
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = expression1.typecheck(typeEnv);
        type2 = expression2.typecheck(typeEnv);
        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new BoolType();
            }
            else throw new MyException("Second operand is not an integer");
        }
        else throw new MyException("First operand is not an integer");
    }
}
