import 'action.dart';

class State {
  late final String name;
  late final bool correct;
  late final List<Action> actions;

  State(String s) {
    s = clean(s);
    if (!s.startsWith("state ")) parseError(0, "Start with state");
    name = extractName(s.split("\\n")[0]);
    correct = extractCorrectness(s.split("\\n")[0]);
    actions =
        extractActions(s.substring(s.indexOf('(') + 1, s.lastIndexOf(')')));
  }

  static List<Action> extractActions(String stateBody) {
    var ss = <String>[];
    var actions = <Action>[];
    var reg = RegExp(r"\w+\s*=>\s*\w+");
    var matches = reg.allMatches(stateBody);
    for (var m in matches) {
      String match = m.group(0)!;
      actions.add(
          Action(match.split("=>")[0].trim(), match.split("=>")[1].trim()));
    }
    return actions;
  }

  static String extractName(String cleanedFirstLine) {
    return cleanedFirstLine.substring(6, cleanedFirstLine.indexOf(" ="));
  }

  static bool extractCorrectness(String cleanedFirstLine) {
    if (cleanedFirstLine.contains("true")) {
      return true;
    }
    return false;
  }

  void parseError(int line, String error) {
    throw Exception("Line :" + line.toString() + "\n" + error);
  }

  static String clean(String dirty) {
    return dirty.replaceAll("\\s+", " ").trim();
  }

  @override
  String toString() {
    return "State{" "name='" +
        name +
        '\'' +
        ", correct=" +
        correct.toString() +
        ", actions=" +
        actions.toString() +
        '}';
  }
}
