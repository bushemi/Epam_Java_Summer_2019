package com.epam;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic line of parsed logs
 * <p>
 * Note: this class has a natural ordering that is inconsistent with equals.
 */
public class LogLine implements Serializable, Comparable {
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
    public int compareTo(Object o) {
        LogLine logLine = (LogLine) o;
        Integer executionTimeObjects = Integer.parseInt(logLine.getExecutionTimeMsec());
        Integer executionTimeCurrent = Integer.parseInt(this.getExecutionTimeMsec());
        return executionTimeObjects - executionTimeCurrent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogLine)) return false;
        LogLine logLine = (LogLine) o;
        return Objects.equals(getDateTime(), logLine.getDateTime()) &&
                Objects.equals(getOperation(), logLine.getOperation()) &&
                Objects.equals(getExecutionTimeMsec(), logLine.getExecutionTimeMsec());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateTime(), getOperation(), getExecutionTimeMsec());
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
