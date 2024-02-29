package org.example.interpretergui.View;

import java.io.BufferedReader;
import java.util.*;

import  org.example.interpretergui.Model.DataStructures.*;
import  org.example.interpretergui.Model.Expressions.*;
import  org.example.interpretergui.Model.ProgramState.ProgramState;
import  org.example.interpretergui.Model.Statements.*;
import  org.example.interpretergui.Model.Type.BoolType;
import  org.example.interpretergui.Model.Type.IntType;
import  org.example.interpretergui.Model.Type.StringType;
import  org.example.interpretergui.Model.Type.*;
import  org.example.interpretergui.Model.Value.BoolValue;
import  org.example.interpretergui.Model.Value.IntValue;
import  org.example.interpretergui.Model.Value.StringValue;
import  org.example.interpretergui.Model.Value.Value;
import  org.example.interpretergui.Model.Value.*;
import  org.example.interpretergui.Controller.Controller;
import  org.example.interpretergui.Repository.*;


///import View.Commands.*;
/*
public class View {

    public void run() throws Exception {
        StatementInterface ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        StatementInterface ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableExpression("b"),
                        new ConcurrentModificationException(
                                new AssignmentStatement("a",
                                        new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                                new ArithmeticExpression(new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5)), 3), 3)),
                                new CompoundStatement(
                                        new AssignmentStatement("b",
                                                new ArithmeticExpression(new VariableExpression("a"),
                                                        new ValueExpression(new IntValue(1)),1)),
                                        new PrintStatement(new VariableExpression("b"))))));
        StatementInterface ex3 = new CompundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompundStatement(
                                        new IfStatement(new VarExpression("a"),
                                                new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VarExpression("v"))))));

        String fileName = "test.in";
        StatementInterface ex4 = new CompundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompundStatement(new AssignStatement("varf", new ValueExpression(new StringValue(fileName))),
                        new CompundStatement(new OpenRFileStatement(new VarExpression("varf")),
                                new CompundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompundStatement(new readFileStatement(new VarExpression("varf"), "varc"),
                                                new CompundStatement(new PrintStatement(new VarExpression("varc")),
                                                        new CompundStatement(
                                                                new readFileStatement(new VarExpression("varf"),
                                                                        "varc"),
                                                                new CompundStatement(
                                                                        new PrintStatement(new VarExpression("varc")),
                                                                        new CloseFileStatement(
                                                                                new VarExpression("varf"))))))))));
        StatementInterface ex5 = new CompundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompundStatement(new VariableDeclarationStatement("x", new IntType()),
                        new CompundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                new CompundStatement(new AssignStatement("x", new ValueExpression(new IntValue(3))),
                                        new CompundStatement(new VariableDeclarationStatement("y", new IntType()),
                                                new CompundStatement(new VariableDeclarationStatement("z", new IntType()),
                                                        new CompundStatement(new AssignStatement("y",
                                                                new ValueExpression(new IntValue(1))),
                                                                new CompundStatement(new AssignStatement("z",
                                                                        new ValueExpression(new IntValue(2))),
                                                                        new CompundStatement(new PrintStatement(
                                                                                new RelationalExpression("<",
                                                                                        new VarExpression("v"),
                                                                                        new VarExpression("x"))),
                                                                                new CompundStatement(new PrintStatement(
                                                                                        new RelationalExpression("<",
                                                                                                new VarExpression(
                                                                                                        "v"),
                                                                                                new VarExpression(
                                                                                                        "y"))),
                                                                                        new PrintStatement(
                                                                                                new RelationalExpression(
                                                                                                        "<",
                                                                                                        new VarExpression(
                                                                                                                "v"),
                                                                                                        new VarExpression(
                                                                                                                "z")))))))))))));
        TextMenu menu = new TextMenu();
        PrgState prg1 = new PrgState(new Stack<StatementInterface>(), new Dict<String, Value>(), new List<Value>(),
                new Dict<String, BufferedReader>(), ex1);
        PrgState prg2 = new PrgState(new Stack<StatementInterface>(), new Dict<String, Value>(), new List<Value>(),
                new Dict<String, BufferedReader>(), ex2);
        PrgState prg3 = new PrgState(new Stack<StatementInterface>(), new Dict<String, Value>(), new List<Value>(),
                new Dict<String, BufferedReader>(), ex3);
        PrgState prg4 = new PrgState(new Stack<StatementInterface>(), new Dict<String, Value>(), new List<Value>(),
                new Dict<String, BufferedReader>(), ex4);
        PrgState prg5 = new PrgState(new Stack<StatementInterface>(), new Dict<String, Value>(), new List<Value>(),
                new Dict<String, BufferedReader>(), ex5);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        IRepository repo2 = new Repository(prg2, "log2.txt");
        IRepository repo3 = new Repository(prg3, "log3.txt");
        IRepository repo4 = new Repository(prg4, "log4.txt");
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctrl1 = new Controller(repo1, true);
        Controller ctrl2 = new Controller(repo2, true);
        Controller ctrl3 = new Controller(repo3, true);
        Controller ctrl4 = new Controller(repo4, true);
        Controller ctrl5 = new Controller(repo5, true);

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));
        menu.show();
    }

}
*/
public class View implements ViewInterface{
    public View(){
        try{
            run();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void run() throws Exception{
        //seventh program: Ref(int) v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        StatementInterface st54 = new VariableDeclarationStatement("v", new ReferenceType(new IntType())); // Ref(int) v;
        StatementInterface st55 = new NewStatement("v", new ValueExpression(new IntValue(20))); // new(v,20);
        StatementInterface st56 = new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))); //Ref(Ref(int)) a;
        StatementInterface st57 = new NewStatement("a", new VariableExpression("v")); // new(a,v);
        StatementInterface st58 = new PrintStatement(new VariableExpression("v")); // Print(v);
        StatementInterface st59 = new PrintStatement(new VariableExpression("a")); // Print(a);
        StatementInterface st60 = new CompoundStatement(st58,st59);
        StatementInterface st61 = new CompoundStatement(st57,st60);
        StatementInterface st62 = new CompoundStatement(st56,st61);
        StatementInterface st63 = new CompoundStatement(st55,st62);
        StatementInterface prog7 = new CompoundStatement(st54,st63);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        StatementInterface st64 = new VariableDeclarationStatement("v", new ReferenceType(new IntType())); // Ref(int) v;
        StatementInterface st65 = new NewStatement("v", new ValueExpression(new IntValue(20))); // new(v,20);
        StatementInterface st66 = new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))); //Ref(Ref(int)) a;
        StatementInterface st67 = new NewStatement("a", new VariableExpression("v")); // new(a,v);
        StatementInterface st68 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))); // Print(rH(v));
        StatementInterface st69 = new PrintStatement(
                new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
                        new ValueExpression(new IntValue(5)), 1)); // Print(rH(rH(a))+5);
        StatementInterface st70 = new CompoundStatement(st68,st69);
        StatementInterface st71 = new CompoundStatement(st67,st70);
        StatementInterface st72 = new CompoundStatement(st66,st71);
        StatementInterface st73 = new CompoundStatement(st65,st72);
        StatementInterface prog8 = new CompoundStatement(st64,st73);

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        StatementInterface st74 = new VariableDeclarationStatement("v", new ReferenceType(new IntType())); // Ref(int) v;
        StatementInterface st75 = new NewStatement("v", new ValueExpression(new IntValue(20))); // new(v,20);
        StatementInterface st76 = new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))); // Print(rH(v));
        StatementInterface st77 = new WriteHeapStatement("v", new ValueExpression(new IntValue(30))); // wH(v,30);
        StatementInterface st78 = new PrintStatement(
                new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")),
                        new ValueExpression(new IntValue(5)), 1)); // Print(rH(v)+5);
        StatementInterface st79 = new CompoundStatement(st77,st78);
        StatementInterface st80 = new CompoundStatement(st76,st79);
        StatementInterface st81 = new CompoundStatement(st75,st80);
        StatementInterface prog9 = new CompoundStatement(st74,st81);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        StatementInterface st82 = new VariableDeclarationStatement("v", new ReferenceType(new IntType())); // Ref(int) v;
        StatementInterface st83 = new NewStatement("v", new ValueExpression(new IntValue(20))); // new(v,20);
        StatementInterface st84 = new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))); //Ref(Ref(int)) a;
        StatementInterface st85 = new NewStatement("a", new VariableExpression("v")); // new(a,v);
        StatementInterface st86 = new NewStatement("v", new ValueExpression(new IntValue(30))); // new(v,30);
        StatementInterface st87 = new PrintStatement(new ReadHeapExpression(
                new ReadHeapExpression(new VariableExpression("a")))); // Print(rH(rH(a)));Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        StatementInterface st88 = new CompoundStatement(st86,st87);
        StatementInterface st89 = new CompoundStatement(st85,st88);
        StatementInterface st90 = new CompoundStatement(st84,st89);
        StatementInterface st91 = new CompoundStatement(st83,st90);
        StatementInterface prog10 = new CompoundStatement(st82,st91);






        String op = ">";
        String opminus = "-";
        StatementInterface ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(0))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(3))),
                                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("a"), new ValueExpression(new IntValue(0)), op)),
                                                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), 2)),
                                                                new PrintStatement(new VariableExpression("a")))))))));
       // HeapInterface<Integer, Value> heap7 = new ADTHeap(new HashMap<Integer, Value>());
        //StackInterface<StatementInterface> stk7 = new ADTStack<StatementInterface>(new Stack<StatementInterface>());
        ///ListInterface<Value> ot7 = new ADTList<Value>(new ArrayList<Value>());
        //ADTDictionary<String, Value> symtbl7 = new ADTDictionary<String, Value>(new HashMap<String, Value>());
        //ADTDictionary<String, BufferedReader> filetbl7 = new ADTDictionary<String, BufferedReader>(new HashMap<String, BufferedReader>());
        //ProgramState prg7 = new ProgramState(stk7, heap7, symtbl7, ot7, filetbl7, ex7);



        //int v; Ref int a; v=10;new(a,22);fork(wH(a,30);v=32;print(v);print(rH(a)));print(v);print(rH(a))
        StatementInterface  statement10 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(
                                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("v")),
                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        ProgramState prg10 = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                statement10, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());


        ProgramState prg1 = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                prog7, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());
        ProgramState prg2 = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                prog8, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());
        ProgramState prg3 = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                prog9, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());
        ProgramState prg4 = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                prog10, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());
        ProgramState prg5 = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                ex7, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());


        /*
      int a; int b; int c; switch(a*10) (case (b*c) print(a); print(b)) (case (10) print(100); print(200)) (default print(300)); print(300);
         */

        StatementInterface exam1 = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("c", new IntType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a", new ValueExpression(new IntValue(1))),
                                        new CompoundStatement(
                                                new AssignmentStatement("b", new ValueExpression(new IntValue(2))),
                                                new CompoundStatement(
                                                        new AssignmentStatement("c", new ValueExpression(new IntValue(5))),
                                                        new CompoundStatement(
                                                                new SwitchStatement(
                                                                        new ArithmeticExpression(
                                                                                new VariableExpression("a"),
                                                                                new VariableExpression("b"),
                                                                                3
                                                                        ),
                                                                        new ArithmeticExpression(
                                                                                new VariableExpression("b"),
                                                                                new VariableExpression("c"),
                                                                                3
                                                                        ),
                                                                        new PrintStatement(new VariableExpression("a")),
                                                                        new ValueExpression(new IntValue(10)),
                                                                        new PrintStatement(new VariableExpression("b")),
                                                                        new PrintStatement(new VariableExpression("c"))
                                                                ),
                                                                new PrintStatement(new ValueExpression(new IntValue(300)))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );


        StatementInterface statementExample = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                        new CompoundStatement(
                                new SwitchStatement(
                                        new ArithmeticExpression(
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(3)),
                                                3
                                        ),
                                        new ArithmeticExpression(
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(10)),
                                                3
                                        ),
                                        new PrintStatement(new ValueExpression(new IntValue(100))),
                                        new ArithmeticExpression(
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(10)),
                                                3
                                        ),
                                        new PrintStatement(new ValueExpression(new IntValue(1000))),
                                        new PrintStatement(new ValueExpression(new IntValue(10000)))
                                ),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );

        ProgramState prgExam1 = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                statement10, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());


        RepositoryInterface repo1 = new MemoryRepository(prg1, new StringValue("log.txt"));
        RepositoryInterface repo2 = new MemoryRepository(prg2, new StringValue("log.txt"));
        RepositoryInterface repo3 = new MemoryRepository(prg3, new StringValue("log.txt"));
        RepositoryInterface repo4 = new MemoryRepository(prg4, new StringValue("log.txt"));
        RepositoryInterface repo5 = new MemoryRepository(prg5, new StringValue("log.txt"));
        RepositoryInterface repo10 = new MemoryRepository(prg10, new StringValue("log.txt"));
        RepositoryInterface repoExamen1 = new MemoryRepository(prgExam1, new StringValue("l0g.txt"));

        Controller ctrl1 = new Controller(repo1, new BoolValue(true));
        Controller ctrl2 = new Controller(repo2, new BoolValue(true));
        Controller ctrl3 = new Controller(repo3, new BoolValue(true));
        Controller ctrl4 = new Controller(repo4, new BoolValue(true));
        Controller ctrl5 = new Controller(repo5, new BoolValue(true));
        Controller ctrl10 = new Controller(repo10, new BoolValue(true));
        Controller ctrlExamen1 = new Controller(repoExamen1, new BoolValue(true));

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", prog7.toString(), ctrl1));
        menu.addCommand(new RunExample("2", prog8.toString(), ctrl2));
        menu.addCommand(new RunExample("3", prog9.toString(), ctrl3));
        menu.addCommand(new RunExample("4", prog10.toString(), ctrl4));
        menu.addCommand(new RunExample("5", ex7.toString(), ctrl5));
        menu.addCommand(new RunExample("6", statement10.toString(), ctrl10));
        menu.addCommand(new RunExample("7", exam1.toString(), ctrlExamen1));
        menu.show();
        }

    @Override
    public void mainMenu() {

    }

    @Override
    public void run(ArrayList<StatementInterface> programs, int typeToRun) {

    }

    @Override
    public ArrayList<StatementInterface> selectProgram() {
        return null;
    }

    @Override
    public void setDisplayFlag(BoolValue value) {

    }
}

