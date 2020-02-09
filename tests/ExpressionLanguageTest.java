import assignment.PrettyPrintVisitor;
import assignment.assignmentLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import assignment.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

class ExpressionLanguageTest {

    /**
     * Checks that formatting files ends up with the same results as expected.
     */
    @Test
    public void TestFormatter() {
        for (int i = 1; i < 3; i++) {
            String check = String.format("Checking \"testfiles/unformatted_%d\".txt formatting matches expected output...", i);
            System.out.print(check);

            try {
                String correctlyFormatted = Files.readAllLines(Paths.get(String.format("testfiles/formatted_%d.txt", i))).stream().collect(Collectors.joining("\n"));
                CharStream unformattedCS = CharStreams.fromFileName(String.format("testfiles/unformatted_%d.txt", i));

                assignmentLexer unformattedLexer = new assignmentLexer(unformattedCS);
                CommonTokenStream tokenStream = new CommonTokenStream(unformattedLexer);
                assignmentParser parser = new assignmentParser(tokenStream);
                String output = new PrettyPrintVisitor().visit(parser.start());

                Assertions.assertEquals(correctlyFormatted, output);
                System.out.println("PASSED");

            } catch (IOException err) {
                System.out.println("FAILED");
                err.printStackTrace();
            }
        }
    }

    @Test
    public void TestEvaluator() {
        String expression = "a=!true;4>2;";
        CharStream cs = CharStreams.fromString(expression);
        assignmentLexer lexer = new assignmentLexer(cs);
        assignmentParser parser = new assignmentParser(new CommonTokenStream(lexer));
        System.out.println("INPUT\n");
        System.out.println(expression);
        System.out.println("OUTPUT\n");
        new EvaluatorVisitor().visit(parser.start());
    }

    /**
     * Checks that formatting files ends up with the same results as expected.
     */
    @Test
    public void TestCompiler() {
        for (int i = 2; i < 3; i++) {
            String check = String.format("Checking \"testfiles/compiler/output_%d\".txt matches expected output...", i);
            System.out.print(check);

            try {
                String correctlyCompiled = Files.readAllLines(Paths.get(String.format("testfiles/compiler/output_%d.txt", i))).stream().collect(Collectors.joining("\n"));
                CharStream sourceCS = CharStreams.fromFileName(String.format("testfiles/compiler/input_%d.txt", i));

                assignmentLexer sourceLexer = new assignmentLexer(sourceCS);
                CommonTokenStream tokenStream = new CommonTokenStream(sourceLexer);
                assignmentParser parser = new assignmentParser(tokenStream);
                String output = new PythonCompilerVisitor().visit(parser.start());

                System.out.println("OUTPUT");
                System.out.println(output);
                Assertions.assertEquals(correctlyCompiled, output);
                System.out.println("PASSED");

            } catch (IOException err) {
                System.out.println("FAILED");
                err.printStackTrace();
            }
        }
    }
}