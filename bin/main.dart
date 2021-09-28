import 'package:states_lang_parser/src/program.dart';

void main(List<String> args) {
  //Is number divisible by three state machine implementation
  String code = """
                state 0 =  true (
                1 => 1
                )
                                
                state 1 = false (
                0 => 2
                1 => 0
                )
                                
                state 2 = false (
                0 => 1
                )
                """;
  Program x = Program(code);
  print(x.execute("1001")); // 9
  print(x.execute("1010")); // 10
  print(x.execute("1011")); // 11
}
