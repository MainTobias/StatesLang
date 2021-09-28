import 'package:states_lang_parser/src/returncode.dart';

class ExecutionInformation {
  final ReturnCode code;
  final String stateName;

  ExecutionInformation(this.code, this.stateName);

  @override
  String toString() {
    return "State " + stateName + " was " + code.toString();
  }
}
