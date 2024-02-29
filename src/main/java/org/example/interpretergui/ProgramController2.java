package org.example.interpretergui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.interpretergui.Controller.Controller;
import org.example.interpretergui.Model.DataStructures.ADTHeap;
import org.example.interpretergui.Model.DataStructures.HeapInterface;
import org.example.interpretergui.Model.DataStructures.ListInterface;
import org.example.interpretergui.Model.DataStructures.SemaphoreTableInterface;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Statements.StatementInterface;
import org.example.interpretergui.Model.Value.IntValue;
import org.example.interpretergui.Model.Value.Value;

import java.util.*;
import java.util.stream.Collectors;

class Pair<T1, T2> {
    T1 first;
    T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}


public class ProgramController2 {
    private Controller controller;

    @FXML
    private TableView<Pair<Integer, Value>> heapTable;

    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> addressColumn;

    @FXML
    private TableColumn<Pair<Integer, Value>, String> valueColumn;

    @FXML
    private ListView<String> outputList;

    @FXML
    private ListView<String> fileList;

    @FXML
    private ListView<Integer> programStateList;

    @FXML
    private ListView<String> executionStackList;

    @FXML
    private TableView<Pair<String, Value>> symbolTable;

    @FXML
    private TableColumn<Pair<String, Value>, String> symVariableColumn;

    @FXML
    private TableColumn<Pair<String, Value>, String> symValueColumn;

    @FXML
    private TextField numberOfProgramStates;

    @FXML
    private Button oneStep;

    @FXML
    private TableView<Pair<Integer, Pair<Integer, List<Integer>>>> SemaphoreTable;

    @FXML
    public void initialize() {
        addressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().first).asObject());
        valueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        symVariableColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().first));
        symValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        oneStep.setOnAction(actionEvent -> {
            if(controller == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            boolean programStateLeft = Objects.requireNonNull(getCurrentProgramState()).getExecutionStack().isEmpty().getValue();
            if(programStateLeft){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            try {
                controller.takeAStepForGUI();
                populate();
            } catch (MyException interpreterError) {
                Alert alert = new Alert(Alert.AlertType.ERROR, interpreterError.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        });
        programStateList.setOnMouseClicked(mouseEvent -> populate());
    }

    private ProgramState getCurrentProgramState(){
        if (controller.getProgramStates().size().equals(0))
            return null;
        int currentId = programStateList.getSelectionModel().getSelectedIndex();
        if (currentId == -1) {
            try {
                return controller.getProgramStates().get(new IntValue(0));
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return controller.getProgramStates().get(new IntValue(currentId));
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
        populate();
    }

    private void populate() {
        populateHeap();
        populateProgramStateIdentifiers();
        populateFileTable();
        populateOutput();
        populateSymbolTable();
        populateExecutionStack();
        populateSemaphoreTable();
    }

    private void populateHeap() {
        HeapInterface heap;
        if (!controller.getProgramStates().isEmpty().getValue()) {
            try {
                heap = controller.getProgramStates().get(new IntValue(0)).getHeap();
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        else heap = new ADTHeap();
        List<Pair<Integer, Value>> heapTableList = new ArrayList<>();
        for (Map.Entry<Integer, Value> entry : heap.getDictionary().entrySet())
            heapTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        heapTable.setItems(FXCollections.observableList(heapTableList));
        heapTable.refresh();
    }

    private void populateProgramStateIdentifiers() {
        List<ProgramState> programStates = controller.getRepo().getProgramList().getList();
        var idList = programStates.stream().map(ProgramState::getId).collect(Collectors.toList());
        programStateList.setItems(FXCollections.observableList(idList));
        numberOfProgramStates.setText("" + programStates.size());
    }

    private void populateFileTable() {
        ArrayList<String> files;
        if (!controller.getProgramStates().isEmpty().getValue()) {
            try {
                files = new ArrayList<>(controller.getProgramStates().get(new IntValue(0)).getFileTable().keySet());
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        else files = new ArrayList<>();
        fileList.setItems(FXCollections.observableArrayList(files));
    }

    private void populateOutput() {
        ListInterface<Value> output;
        if (!controller.getProgramStates().isEmpty().getValue()) {
            try {
                output = controller.getProgramStates().get(new IntValue(0)).getOutput();
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        else output = new org.example.interpretergui.Model.DataStructures.ADTList<>();
        outputList.setItems(FXCollections.observableList(Collections.singletonList(String.valueOf(output.getList()))));
        outputList.refresh();
    }

    private void populateSymbolTable() {
        ProgramState state = getCurrentProgramState();
        List<Pair<String, Value>> symbolTableList = new ArrayList<>();
        if (state != null)
            for (Map.Entry<String, Value> entry : state.getSymbolTable().getDictionary().entrySet())
                symbolTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        symbolTable.setItems(FXCollections.observableList(symbolTableList));
        symbolTable.refresh();
    }

    void populateSemaphoreTable(){
        ProgramState programState = getCurrentProgramState();
        SemaphoreTableInterface semaphoreTable = Objects.requireNonNull(programState).getSemaphoreTable();
        List<Map.Entry<Integer, javafx.util.Pair<Integer, List<Integer>>>> semaphoreList = new ArrayList<>();
        for (Map.Entry<Integer, javafx.util.Pair<Integer, List<Integer>>> entry: semaphoreTable.getSemaphoreTable().entrySet()) {
            semaphoreList.add(entry);
        }
        SemaphoreTable.setItems(FXCollections.observableArrayList());
        SemaphoreTable.refresh();
    };

    private void populateExecutionStack() {
        ProgramState state = getCurrentProgramState();
        List<String> executionStackListAsString = new ArrayList<>();
        if (state != null)
            for(StatementInterface s : state.getExecutionStack().getStack()){
                executionStackListAsString.add(s.toString());
            }
        executionStackList.setItems(FXCollections.observableList(executionStackListAsString));
        executionStackList.refresh();
    }
}