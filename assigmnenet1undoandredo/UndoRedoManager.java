package com.example.assigmnenet1undoandredo;

import java.util.Stack;

public class UndoRedoManager {
    private final Stack<ListViewCommand> undoStack = new Stack<>();
    private final Stack<ListViewCommand> redoStack = new Stack<>();

    public void execute(ListViewCommand command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            ListViewCommand command = undoStack.pop();
            command.execute();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            ListViewCommand command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }
}
