package org.example.interpretergui.Model.Statements;


import javafx.scene.input.TouchPoint;
import org.example.interpretergui.Model.DataStructures.DictionaryInterface;
import org.example.interpretergui.Model.DataStructures.StackInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.ExpressionInterface;
import org.example.interpretergui.Model.Expressions.RelationalExpression;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Type.BoolType;
import org.example.interpretergui.Model.Type.StringType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.BoolValue;
import org.example.interpretergui.Model.Value.IntValue;
import org.example.interpretergui.Model.Value.StringValue;
import org.example.interpretergui.Model.Value.Value;
import org.example.interpretergui.Model.Statements.StatementInterface;
public class SwitchStatement implements StatementInterface{
    private final ExpressionInterface mainExpression;
    private final ExpressionInterface expression1;
    private final StatementInterface statement1;
    private final ExpressionInterface expression2;
    private final StatementInterface statement2;
    private final StatementInterface defaultStatement;


   public SwitchStatement(ExpressionInterface mainExpression, ExpressionInterface expression1, StatementInterface statement1, ExpressionInterface expression2, StatementInterface statement2, StatementInterface defaultStatement) {
        this.mainExpression = mainExpression;
        this.expression1 = expression1;
        this.statement1 = statement1;
        this.expression2 = expression2;
        this.statement2 = statement2;
        this.defaultStatement = defaultStatement;
    }

    @Override
    public StatementInterface deepCopy() {
        return new SwitchStatement(this.mainExpression.deepCopy(), this.expression1.deepCopy(), this.statement1.deepCopy(), this.expression2.deepCopy(), this.statement2.deepCopy(), this.defaultStatement.deepCopy());
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> exeStack = state.getExecutionStack();
        StatementInterface converted = new IfStatement(new RelationalExpression(mainExpression, expression1, "=="),
                statement1, new IfStatement(new RelationalExpression(mainExpression, expression2, "=="), statement2, defaultStatement));
        exeStack.push(converted);
        state.setExecutionStack(exeStack);
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type mainType = this.mainExpression.typecheck(typeEnv);
        Type type1 = this.expression1.typecheck(typeEnv);
        Type type2 = this.expression2.typecheck(typeEnv);
        if (mainType.equals(type1) && mainType.equals(type2)){
            this.statement1.typecheck(typeEnv.deepCopy());
            this.statement2.typecheck(typeEnv.deepCopy());
            this.defaultStatement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else{
            throw new MyException("The expression types don't match in the switch statement!");
        }
    }

    @Override
    public String toString() {
        return String.format("switch(%s){(case(%s): %s)(case(%s): %s)(default: %s)}", mainExpression, expression1, statement1, expression2, statement2, defaultStatement);
    }
}
