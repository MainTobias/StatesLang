class Action {
  final String pattern;
  final String newState;

  Action(this.pattern, this.newState);

  bool matches(String s) {
    return s == pattern;
  }
  @override
  String toString() {
    return "Action{" +
        "pattern='" + pattern + '\'' +
        ", newState='" + newState + '\'' +
        '}';
  }
}
