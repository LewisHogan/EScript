package expressionscript;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PrettyPrintVisitorTest {


    @Test
    void visitStart() {
        for (int i = 1; i < 3; i++) {
            String check = String.format("Checking \"testresources/prettyprint/unformatted_%d\".txt formatting matches expected output...", i);
            System.out.print(check);

            try {
                String correctlyFormatted = Files.readAllLines(Paths.get(String.format("testresources/prettyprint/formatted_%d.txt", i))).stream().collect(Collectors.joining("\n"));
                CharStream unformattedCS = CharStreams.fromFileName(String.format("testresources/prettyprint/unformatted_%d.txt", i));

                escriptLexer unformattedLexer = new escriptLexer(unformattedCS);
                CommonTokenStream tokenStream = new CommonTokenStream(unformattedLexer);
                escriptParser parser = new escriptParser(tokenStream);
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
    void visitStatementBlock() {
    }

    @Test
    void visitStatementAssignment() {
    }

    @Test
    void visitStatementBranch() {
    }

    @Test
    void visitStatementCondition() {
    }

    @Test
    void visitStatementReturn() {
    }

    @Test
    void visitConditionComparison() {
    }

    @Test
    void visitConditionValue() {
    }

    @Test
    void visitConditionExpression() {
    }

    @Test
    void visitConditionParens() {
    }

    @Test
    void visitConditionInverted() {
    }

    @Test
    void visitConditionLogic() {
    }

    @Test
    void visitExpressionValue() {
    }

    @Test
    void visitExpressionMath() {
    }

    @Test
    void visitExpressionParens() {
    }

    @Test
    void visitExpressionAssignment() {
    }
}