package com.states.interpreter;

public record ExecutionInformation(ReturnCode code, String stateName) {
    @Override
    public String toString() {
        return "State " + this.stateName + " was " + code;
    }
}
