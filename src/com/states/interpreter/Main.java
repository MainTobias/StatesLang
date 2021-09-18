package com.states.interpreter;

public class Main {

    public static void main(String[] args) {
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
        Program x = new Program(code);
        System.out.println(x.execute("1001")); // 9
        System.out.println(x.execute("1010")); // 10
        System.out.println(x.execute("1011")); // 11
    }
}
