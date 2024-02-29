package org.example.interpretergui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.example.interpretergui.Controller.Controller;
import org.example.interpretergui.Model.DataStructures.*;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.Expressions.*;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Statements.*;
import org.example.interpretergui.Model.Type.BoolType;
import org.example.interpretergui.Model.Type.IntType;
import org.example.interpretergui.Model.Type.ReferenceType;
import org.example.interpretergui.Model.Value.BoolValue;
import org.example.interpretergui.Model.Value.IntValue;
import org.example.interpretergui.Model.Value.StringValue;
import org.example.interpretergui.Model.Value.Value;
import org.example.interpretergui.Repository.MemoryRepository;
import org.example.interpretergui.Repository.RepositoryInterface;
import org.example.interpretergui.View.ExitCommand;
import org.example.interpretergui.View.RunExample;
import org.example.interpretergui.View.TextMenu;
import org.example.interpretergui.View.gui.program.ProgramController;

import java.io.BufferedReader;
import java.io.IOException;


public class ListController2 {
    private ProgramController2 programController;

    public void setProgramController(ProgramController2 programController) {
        this.programController = programController;
    }

    @FXML
    private ListView<StatementInterface> statements;

    @FXML
    private Button displayButton;

    @FXML
    public void initialize() {
        //seventh program: Ref(int) v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        StatementInterface st54 = new VariableDeclarationStatement("v", new ReferenceType(new BoolType())); // Ref(int) v;
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


        StatementInterface exam1 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new VariableDeclarationStatement("c", new IntType()),
                                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(1))),
                                        new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(2))),
                                                new CompoundStatement(new AssignmentStatement("c", new ValueExpression(new IntValue(5))),
                                                        new CompoundStatement(new SwitchStatement(
                                                                new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(10)), 3),
                                                                new ArithmeticExpression(new VariableExpression("b"), new VariableExpression("c"), 3),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b"))),
                                                                new ValueExpression(new IntValue(10)),
                                                                new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))), new PrintStatement(new ValueExpression(new IntValue(200)))),
                                                                new PrintStatement(new ValueExpression(new IntValue(300)))
                                                        ), new PrintStatement(new ValueExpression(new IntValue(300))))))))));


        StatementInterface exam2 = new CompoundStatement(
                new VariableDeclarationStatement("v1", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new VariableDeclarationStatement("cnt", new IntType()),
                        new CompoundStatement(
                                new NewStatement("v1", new ValueExpression(new IntValue(1))),
                                new CompoundStatement(
                                        new CreateSemaphoreStatement("cnt", new ReadHeapExpression(new VariableExpression("v1"))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new AcquireStatement("cnt"),
                                                                new CompoundStatement(
                                                                        new WriteHeapStatement("v1", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), 3)),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                new ReleaseStatement("cnt")
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new AcquireStatement("cnt"),
                                                                        new CompoundStatement(
                                                                                new WriteHeapStatement("v1", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), 3)),
                                                                                new CompoundStatement(
                                                                                        new WriteHeapStatement("v1", new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(2)),3)),
                                                                                        new CompoundStatement(
                                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                                new ReleaseStatement("cnt")
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        ),
                                                        new CompoundStatement(
                                                                new AcquireStatement("cnt"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(1)),2)),
                                                                        new ReleaseStatement("cnt")
                                                                )
                                                        )
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

        ProgramState exam1State = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                exam1, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());

        ProgramState exam2State = new ProgramState(new ADTStack<StatementInterface>(), new ADTDictionary<String, Value>(), new ADTList<Value>(),
                exam2, new ADTDictionary<String, BufferedReader>(), new ADTHeap(), new SemaphoreTable());

        RepositoryInterface repo1 = new MemoryRepository(prg1, new StringValue("log.txt"));
        RepositoryInterface repo2 = new MemoryRepository(prg2, new StringValue("log.txt"));
        RepositoryInterface repo3 = new MemoryRepository(prg3, new StringValue("log.txt"));
        RepositoryInterface repo4 = new MemoryRepository(prg4, new StringValue("log.txt"));
        RepositoryInterface repo5 = new MemoryRepository(prg5, new StringValue("log.txt"));
        RepositoryInterface repo10 = new MemoryRepository(prg10, new StringValue("log.txt"));
        RepositoryInterface exam1Repo = new MemoryRepository(exam1State, new StringValue("log.txt"));
        RepositoryInterface exam2Repo = new MemoryRepository(exam2State, new StringValue("log.txt"));

        Controller ctrl1 = new Controller(repo1, new BoolValue(true));
        Controller ctrl2 = new Controller(repo2, new BoolValue(true));
        Controller ctrl3 = new Controller(repo3, new BoolValue(true));
        Controller ctrl4 = new Controller(repo4, new BoolValue(true));
        Controller ctrl5 = new Controller(repo5, new BoolValue(true));
        Controller ctrl10 = new Controller(repo10, new BoolValue(true));
        Controller exam1Controller = new Controller(exam1Repo, new BoolValue(true));
        Controller exam2Controller = new Controller(exam2Repo, new BoolValue(true));

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", prog7.toString(), ctrl1));
        menu.addCommand(new RunExample("2", prog8.toString(), ctrl2));
        menu.addCommand(new RunExample("3", prog9.toString(), ctrl3));
        menu.addCommand(new RunExample("4", prog10.toString(), ctrl4));
        menu.addCommand(new RunExample("5", ex7.toString(), ctrl5));
        menu.addCommand(new RunExample("6", statement10.toString(), ctrl10));
        menu.addCommand(new RunExample("7", exam1.toString(), exam1Controller));
        menu.addCommand(new RunExample("8", exam2.toString(), exam2Controller));

        StatementInterface[] statementProgs = new StatementInterface[]{prog7, prog8, prog9, prog10, ex7, statement10, exam1, exam2};

        statements.setItems(FXCollections.observableArrayList(statementProgs));
        displayButton.setOnAction(actionEvent -> {
            int index = statements.getSelectionModel().getSelectedIndex();
            if (index < 0)
                return;

            StackInterface<StatementInterface> executionStack = new ADTStack<>();
            executionStack.push(statementProgs[index]);

            ProgramState state = new ProgramState(executionStack);

            RepositoryInterface repository = new MemoryRepository(state, new StringValue("log.txt"));

            Controller controller = new Controller(repository, new BoolValue(true));

            try{
                controller.runTypeChecker();
                programController.setController(controller);
            } catch (MyException interpreterError) {
                Alert alert = new Alert(Alert.AlertType.ERROR, interpreterError.getMessage(), ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
        });
    }
}