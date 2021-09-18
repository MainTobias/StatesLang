package com.states.interpreter;

public class Action {
    final public String pattern;
    final public String newState;
    public Action(String pattern, String newState) {
        this.pattern = pattern;
        this.newState = newState;
    }

    public boolean matches(String s) {
        return s.equals(pattern);
    }
    public boolean matches(char c) {
        return String.valueOf(c).equals(pattern);
    }

    @Override
    public String toString() {
        return "Action{" +
                "pattern='" + pattern + '\'' +
                ", newState='" + newState + '\'' +
                '}';
    }
}