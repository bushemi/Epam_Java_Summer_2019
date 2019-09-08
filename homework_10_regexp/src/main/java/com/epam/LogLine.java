package com.epam;

public class LogLine {
    private String dateTime;
    private String module;
    private String operation;
    private String executionTimeMsec;

    public LogLine() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getExecutionTimeMsec() {
        return executionTimeMsec;
    }

    public void setExecutionTimeMsec(String executionTimeMsec) {
        this.executionTimeMsec = executionTimeMsec;
    }

    @Override
    public String toString() {
        return "LogLine{" +
                "dateTime=" + dateTime +
                ", module='" + module + '\'' +
                ", operation='" + operation + '\'' +
                ", executionTimeMsec='" + executionTimeMsec + '\'' +
                '}';
    }

}
