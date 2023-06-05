package com.example.assigmnenet1undoandredo;

import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

        private final ObservableList<String> names = FXCollections.observableArrayList();
        private final ListProperty<String> namesProperty = new SimpleListProperty<>(names);
        private final UndoRedoManager undoRedoManager = new UndoRedoManager();

        @Override
        public void start(Stage primaryStage){
            ListView<String> listView = new ListView<>();

            TextField textField = new TextField();
            textField.setPromptText("Enter a name");
            listView.setItems(namesProperty);

            Button addButton = new Button("Add");
            addButton.setOnAction(event -> {
                String name = textField.getText().trim();
                if (!name.isEmpty()) {
                    AddNameCommand command = new AddNameCommand(names, name);
                    undoRedoManager.execute(command);
                    textField.clear();
                }
            });

            Button undoButton = new Button("Undo");
            undoButton.setOnAction(event -> undoRedoManager.undo());

            Button redoButton = new Button("Redo");
            redoButton.setOnAction(event -> undoRedoManager.redo());

            HBox buttonsBox = new HBox(10, addButton, undoButton, redoButton);
            buttonsBox.setAlignment(Pos.CENTER);

            VBox root = new VBox(10, listView, textField, buttonsBox);
            root.setPrefSize(300, 300);
            root.setPadding(new Insets(10));

            // Add keyboard shortcuts for undo and redo operations
            root.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
                if (event.isShortcutDown() && event.getCode() == KeyCode.Z) {
                    undoRedoManager.undo();
                } else if (event.isShortcutDown() && event.getCode() == KeyCode.Y) {
                    undoRedoManager.redo();
                }
            });
            Scene scene=new Scene(root,500,400);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    public static void main(String[] args) {
        launch();
    }
}