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

class EvaluationVisitorTest {
    @Test
    void visitStart() {
        for (int i = 1; i < 3; i++) {
            String check = String.format("Checking \"testresources/evaluator/script_%d\".txt formatting matches expected output...", i);
            System.out.print(check);

            try {
                String correctResults = Files.readAllLines(Paths.get(String.format("testresources/evaluator/result_%d.txt", i))).stream().collect(Collectors.joining("\n"));
                CharStream inputCS = CharStreams.fromFileName(String.format("testresources/evaluator/script_%d.txt", i));

                escriptLexer unformattedLexer = new escriptLexer(inputCS);
                CommonTokenStream tokenStream = new CommonTokenStream(unformattedLexer);
                escriptParser parser = new escriptParser(tokenStream);
                String output = (String) new EvaluationVisitor().visit(parser.start());

                Assertions.assertEquals(correctResults, output);
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