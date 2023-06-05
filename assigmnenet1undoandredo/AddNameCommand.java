package com.example.assigmnenet1undoandredo;

import javafx.collections.ObservableList;

public class AddNameCommand implements ListViewCommand{

    private final ObservableList<String> names;
    private final String name;

    public AddNameCommand(ObservableList<String> names, String name) {
        this.names = names;
        this.name = name;
    }

    @Override
    public void execute() {
        names.add(name);
    }
}