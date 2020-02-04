import assignment.PrettyPrintVisitor;
import assignment.assignmentLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import assignment.*;

import java.io.IOException;

class PrettyPrintVisitorTest {

    private assignmentParser.StartContext GetStartContext(String source) {
        String backup = " a=(b=2*3);\n" +
                " a=2**b;\n" +
                " a=30/2**3-1;\n" +
                " name=\"hello world\";";
        CharStream cs;
        try {
            cs = CharStreams.fromFileName(source);
            System.out.println("INPUT");
            System.out.println(CharStreams.fromFileName(source));
        } catch (IOException err) {
            System.out.println(err.getMessage());
            System.out.println("Unable to load test file, resorting to string backup");
            System.out.println("INPUT");
            System.out.println(backup);
            cs = CharStreams.fromString(backup);
        }
        assignmentLexer lexer = new assignmentLexer(cs);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        assignmentParser parser = new assignmentParser(tokenStream);


        return parser.start();
    }

    @Test
    public void TestNoInput() {
        // An empty string should be returned if no input is provided.
        String input = "";
        String output = new PrettyPrintVisitor().visit(GetStartContext(input));
        Assertions.assertEquals("", output);
    }

    @Test
    public void TestMalformedInput() {
        // TODO: FIX this test as I'm not even sure if this is the correct output
        String input = ";;;;awdawdaw;d;;";
        String output = new PrettyPrintVisitor().visit(GetStartContext(input));
        Assertions.assertEquals("awdawdaw\nd", output);
    }

    @Test
    public void TestNewLine() {
        // TODO: REPLACE THIS TEST WITH ONE I KNOW IS CORRECT
        String input = "n1=10; n2=20; n3=30; name=\"Lewis\"; university=\"essex\";\n" +
                "simba=\"remember\n" +
                "who\n" +
                "you\n" +
                "are\";";
        String output = new PrettyPrintVisitor().visit(GetStartContext(input));
        Assertions.assertEquals(9, output.split("\n").length);
    }

    @Test
    public void TestTokenSpacing() {
        String output = new PrettyPrintVisitor().visit(GetStartContext("testpretty1.txt"));
        System.out.println("OUTPUT");
        System.out.println(output);
    }
}