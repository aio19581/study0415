package org.example.service;

import org.example.model.Output;

import java.util.List;

public interface History {
    //기록
    public void addHistory(Output output);
    //출력
    public List<Output> getHistory();
}
