package org.example.service;

import org.example.model.Output;

import java.util.ArrayList;
import java.util.List;

public class HistoryImpl implements History {

    private final List<Output> history = new ArrayList<Output>();

    @Override
    public void addHistory(Output output) {
        history.add(output);
    }

    @Override
    public List<Output> getHistory() {
        return history;
    }
}
