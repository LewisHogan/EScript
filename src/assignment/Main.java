package assignment;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
//        main_manual(args);
        main_automated_pretty(args);
//        main_automated_polish(args);
    }

    public static void main_manual(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line;
        while (!(line = scan.nextLine()).equals("q")) {
            System.out.println(line);
            CharStream cs = CharStreams.fromString(line);
            assignmentLexer lexer = new assignmentLexer(cs);
            assignmentParser parser = new assignmentParser(new CommonTokenStream(lexer));
            Stack<String> results = new ReversePolishVisitor().visit(parser.start());

            for (String s : results) System.out.print(" " + s);
        }
    }

    public static void main_automated_polish(String[] args) {
//        CharStream cs = CharStreams.fromString("bc=(1+2+3*(2*2))*3");
        CharStream cs = CharStreams.fromString("5*23-1");
        assignmentLexer lexer = new assignmentLexer(cs);
        assignmentParser parser = new assignmentParser(new CommonTokenStream(lexer));
        Stack<String> results = new ReversePolishVisitor().visit(parser.start());

        for (String s : results) System.out.print(" " + s);
//        while (!results.isEmpty()) System.out.print(results.pop());
    }

    public static void main_automated_pretty(String[] args) {
        CharStream cs = CharStreams.fromString("hello_world = 1 + 3 **     5\n/2.2323+5.2");
        assignmentLexer lexer = new assignmentLexer(cs);
        assignmentParser parser = new assignmentParser(new CommonTokenStream(lexer));

        System.out.println(new PrettyPrintVisitor().visit(parser.start()));
    }
}
