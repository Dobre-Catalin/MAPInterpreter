package org.example.interpretergui.Model.Expressions;

import  org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import  org.example.interpretergui.Model.DataStructures.HeapInterface;
import  org.example.interpretergui.Model.Exceptions.MyException;
import  org.example.interpretergui.Model.Type.IntType;
import  org.example.interpretergui.Model.Type.Type;
import  org.example.interpretergui.Model.Value.Value;
import  org.example.interpretergui.Model.Value.IntValue;

public class ArithmeticExpression implements ExpressionInterface{
    private final ExpressionInterface expression1;
    private final ExpressionInterface expression2;

    private final int operator; // 1 = +, 2 = -, 3 = *, 4 = /

    public ArithmeticExpression(ExpressionInterface expression1, ExpressionInterface expression2, int operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ArithmeticExpression(expression1.deepCopy(), expression2.deepCopy(), operator);
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, HeapInterface heap) throws MyException {
        Value value1, value2;
        value1 = this.expression1.evaluate(table, heap);
        if(!value1.getType().equals(new IntType())){
            throw new MyException("First operand is not an integer");
        }
        value2 = this.expression2.evaluate(table, heap);
        if(!value2.getType().equals(new IntType())){
            throw new MyException("Second operand is not an integer");
        }
        if(this.operator < 1 || this.operator > 4){
            throw new MyException("Invalid arithmetic operator");
        }

        int first = ((IntValue)value1).getValue();
        int second = ((IntValue)value2).getValue();

        return switch(this.operator) {
            case 1 -> new IntValue(first + second);
            case 2 -> new IntValue(first - second);
            case 3 -> new IntValue(first * second);
            case 4 -> {
                if(second == 0){
                    throw new MyException("Division by zero attempted");
                }
                yield new IntValue(first / second);
            }
            default -> throw new MyException("Invalid arithmetic operator");
        };
    }

    @Override
    public String toString() {
        String result = this.expression1.toString() + " ";
        result += switch(this.operator) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> " ";
        };
        result += " " + this.expression2.toString();
        return result;
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = this.expression1.typecheck(typeEnv);
        type2 = this.expression2.typecheck(typeEnv);
        if (type1.equals(new IntType())){
            if (type2.equals(new IntType())){
                return new IntType();
            }
            else{
                throw new MyException("Second operand is not an integer");
            }
        }
        else{
            throw new MyException("First operand is not an integer");
        }
    }
}
