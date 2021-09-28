import 'package:states_lang_parser/src/returncode.dart';
import 'package:states_lang_parser/src/state.dart';

import 'execution_information.dart';

class Program {
  static final validProgramRegex = RegExp(
      r"(?:\s*state\s*\w+\s*=\s*(?:true|false)\s*\((?:\s*\w+\s*=>\s*\w+\s*)*\)\s*)+");
  static final betweenStatesRegex = RegExp(r"(?<=\))\s*(?=state)");
  late final List<State> states;

  Program(String code) {
    if (!validProgramRegex.hasMatch(code)) throw Exception("Not valid");
    states = parseStates(code);
  }

  List<State> parseStates(String code) {
    var statesStrings = code.split(betweenStatesRegex);
    var states = <State>[];
    for (int i = 0; i < statesStrings.length; i++) {
      states.add(State(statesStrings[i]));
    }
    return states;
  }

  State getState(String name) {
    for (int i = 0; i < states.length; i++) {
      if (name == states[i].name) {
        return states[i];
      }
    }
    throw Exception("State not found! Should not happen");
  }

  ExecutionInformation execute(String input) {
    State state = states[0];
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < state.actions.length; j++) {
        if (state.actions[j].matches(input[i])) {
          state = getState(state.actions[j].newState);
          break;
        }
      }
    }
    return ExecutionInformation(
        state.correct ? ReturnCode.SUCCESS : ReturnCode.FAILURE, state.name);
  }

  @override
  String toString() {
    return "Program{states=" + states.toString() + "}";
  }
}
