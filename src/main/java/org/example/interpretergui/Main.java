package org.example.interpretergui;

import  org.example.interpretergui.View.gui.list.ListController;
import  org.example.interpretergui.View.gui.program.ProgramController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        ///ViewInterface view = new View();
        ///view.mainMenu();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader listLoader = new FXMLLoader();
        listLoader.setLocation(getClass().getResource("list.fxml"));
        Parent root = listLoader.load();
        ListController2 listController = listLoader.getController();
        primaryStage.setTitle("Select");
        primaryStage.setScene(new Scene(root, 500, 550));
        primaryStage.show();

        FXMLLoader programLoader = new FXMLLoader();
        programLoader.setLocation(getClass().getResource("program.fxml"));
        Parent programRoot = programLoader.load();
        ProgramController2 programController = programLoader.getController();
        listController.setProgramController(programController);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Interpreter");
        secondaryStage.setScene(new Scene(programRoot, 1400, 1000));
        secondaryStage.show();
    }
}