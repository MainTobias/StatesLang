package com.states.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class State {
    final public String name;
    final public boolean correct;
    final public Action[] actions;

    public State(String s) {
        s = clean(s);
        if (!s.startsWith("state ")) parseError(0, "Start with state");
        this.name = extractName(s.split("\\n")[0]);
        this.correct = extractCorrectness(s.split("\\n")[0]);
        this.actions = extractActions(s.substring(s.indexOf('(')+1, s.lastIndexOf(')')));
    }
    private static int countActions(String actions) {
        Pattern pattern = Pattern.compile("=>");
        Matcher matcher = pattern.matcher(actions);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static Action[] extractActions(String stateBody) {
        String[] ss = new String[countActions(stateBody)];
        Action[] actions = new Action[ss.length];
        Pattern pattern = Pattern.compile("\\w+\\s*=>\\s*\\w+");
        Matcher matcher = pattern.matcher(stateBody);
        for (int i = 0; i < ss.length; i++) {
            matcher.find();
            String match = matcher.group();
            actions[i] = new Action(match.split("=>")[0].strip(), match.split("=>")[1].strip());
        }
        return actions;
    }

    private static String extractName (String cleanedFirstLine) {
        return cleanedFirstLine.substring(6, cleanedFirstLine.indexOf(" ="));
    }
    private static boolean extractCorrectness (String cleanedFirstLine) {
        if (cleanedFirstLine.contains("true")) {
            return true;
        }
        return false;
    }
    private void parseError(int line, String error) {
        throw new Error("Line :" + line + "\n" + error);
    }
    private static String clean(String dirty) {
        return dirty.replaceAll("\\s+", " ").strip();
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", correct=" + correct +
                ", actions=" + Arrays.toString(actions) +
                '}';
    }
}

