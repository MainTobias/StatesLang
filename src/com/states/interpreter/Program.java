package com.states.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    final private static String VALID_PROGRAM_REGEX = "(?>\\s*state\\s*\\w+\\s*=\\s*(?>true|false)\\s*\\((?>\\s*\\w+\\s*=>\\s*\\w+\\s*)*\\)\\s*)+";
    final private static String BETWEEN_STATES_REGEX = "(?<=\\))\\s*(?=state)";
    private final State[] states;
    public Program(String code) {
        if (!code.matches(VALID_PROGRAM_REGEX)) throw new IllegalArgumentException();
        states = parseStates(code);
    }

    private static State[] parseStates(String code) {
        String[] statesStrings = code.split(BETWEEN_STATES_REGEX);
        State[] states = new State[statesStrings.length];
        for (int i = 0; i < statesStrings.length; i++) {
            states[i] = new State(statesStrings[i]);
        }
        return states;
    }

    private State getState(String name) {
        for (State state : states) {
            if (name.equals(state.name)) {
                return state;
            }
        }
        throw new Error("State not found! Should not happen");
    }

    ExecutionInformation execute(String input) {
        State state = states[0];
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < state.actions.length; j++) {
                if (state.actions[j].matches(input.charAt(i))) {
                    state = getState(state.actions[j].newState);
                    break;
                }
            }
        }
        return new ExecutionInformation(state.correct ? ReturnCode.Success : ReturnCode.Failure, state.name);
    }

    @Override
    public String toString() {
        return "Program{" +
                "states=" + Arrays.toString(states) +
                '}';
    }
}
