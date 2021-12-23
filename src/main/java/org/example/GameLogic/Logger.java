package org.example.GameLogic;

public class Logger {

    private final StringBuilder stringBuilder;
    public Logger(){
        stringBuilder = new StringBuilder();
    }

    public void addLog(String log){
        stringBuilder.append(log).append("\n");
    }

    public String getLogs(){
        return stringBuilder.toString();
    }
}
