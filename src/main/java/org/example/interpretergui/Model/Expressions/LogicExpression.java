package org.example.interpretergui.Model.Expressions;

import  org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import  org.example.interpretergui.Model.DataStructures.HeapInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Type.BoolType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.BoolValue;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.Value;

public class LogicExpression implements ExpressionInterface{
    private final ExpressionInterface left;
    private final ExpressionInterface right;
    private final int operator; //1 or, 2 and

    public LogicExpression(ExpressionInterface left, ExpressionInterface right, int operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }


    @Override
    public ExpressionInterface deepCopy() {
return new LogicExpression(left.deepCopy(), right.deepCopy(), operator);
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws MyException {
        Value leftValue, rightValue;
        leftValue =left.evaluate(table, heap);
        if(leftValue.getType().equals(new BoolType())){
            rightValue = right.evaluate(table, heap);
            if(rightValue.getType().equals(new BoolType())){
                boolean leftBool = ((BoolValue)leftValue).getValue();
                boolean rightBool = ((BoolValue)rightValue).getValue();
                if(operator == 1) return new BoolValue(leftBool || rightBool);
                if(operator == 2) return new BoolValue(leftBool && rightBool);
            }
            else throw new MyException("Second operand is not a boolean");
        }
        else throw new MyException("First operand is not a boolean");
        return null;
    }

    @Override
    public String toString() {
        String result = this.left.toString()+ " ";
        result += switch(this.operator) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> " ";
        };
        result += " " + this.right.toString();
        return result;
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = left.typecheck(typeEnv);
        type2 = right.typecheck(typeEnv);
        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType())){
                return new BoolType();
            }
            else throw new MyException("Second operand is not a boolean");
        }
        else throw new MyException("First operand is not a boolean");
    }
}
