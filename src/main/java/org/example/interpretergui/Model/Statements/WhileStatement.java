package org.example.interpretergui.Model.Statements;

import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.BoolType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.BoolValue;
import org.example.interpretergui.Model.Value.Value;

public class WhileStatement implements StatementInterface{
/*
    private final ExpressionInterface expression;
    private final StatementInterface statement;

    public WhileStatement(ExpressionInterface expression, StatementInterface statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStatement(this.expression.deepCopy(), this.statement.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if(!value.getType().equals(new BoolType()))
            throw new MyException("Expression does not evaluate to a boolean value");
        BoolValue boolValue = (BoolValue) value;
        if(boolValue.getValue()){
            state.getExecutionStack().push(new WhileStatement(this.expression, this.statement));
            state.getExecutionStack().push(this.statement);
        }
        return null;
    }

    @Override
    public String toString(){
        return "while(" + this.expression.toString() + ") " + this.statement.toString();
    }

 */

    ExpressionInterface exp;

    public WhileStatement(ExpressionInterface exp) {
        this.exp = exp;
    }

    public String toString() {
        return "while(" + exp.toString() + ") ";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value exp_val = exp.evaluate(state.getSymbolTable(), state.getHeap());
        if (!exp_val.getType().equals(new BoolType()))
            throw new MyException("<WHILE_STMT> Condition is not of type boolean!");
        BoolValue val = (BoolValue)exp_val;
        if (val.getValue() == true) {
            //state.getExeStack().pop();
            StatementInterface stmt = state.getExecutionStack().peek().deepCopy();
            //state.getExeStack().push(this);
            state.getExecutionStack().push(new WhileStatement(exp.deepCopy()));
            state.getExecutionStack().push(stmt);
        }
        else{
            state.getExecutionStack().pop();

        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStatement(exp.deepCopy());
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            return typeEnv;
        }
        else
            throw new MyException("The condition of WHILE has not the type bool");
    }
}
