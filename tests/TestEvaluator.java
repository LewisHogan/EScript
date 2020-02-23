import escript.*;
import escript.ast.ASTBuilder;
import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestEvaluator {

    public static final int EVALUATOR_TESTS = 1;

    private static List<ASTNode> trees;

    static ASTNode createAST(String sourceCode) {
        return createAST(CharStreams.fromString(sourceCode));
    }

    static ASTNode createAST(CharStream sourceCode) {
        escriptLexer lexer = new escriptLexer(sourceCode);
        escriptParser parser = new escriptParser(new CommonTokenStream(lexer));
        return new ASTBuilder().visit(parser.start());
    }

    @BeforeAll
    static void GenerateASTsFromSourceFiles() {
        trees = new ArrayList<>();
        for (int i = 0; i < EVALUATOR_TESTS; i++) {
            String fileToLoad = String.format("tests/evaluator/input_%d.txt", i);
            try {
                System.out.println(String.format("Adding test file tree from %s...", fileToLoad));
                trees.add(createAST(CharStreams.fromFileName(fileToLoad)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void TestGenASTMatchesExisting() {
        EvaluatorVisitor evaluator = new EvaluatorVisitor();

        int testNumber = 0;
        EvaluationOutput output = null;
        for (ASTNode root : trees) {
            try {
                output = (EvaluationOutput) evaluator.visit(root);
            } catch (InvalidIDException | InvalidOperationException | UndefinedVariableException err) {
                err.printStackTrace();
                Assertions.fail(err.getMessage());
            }

            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("OUTPUT: TEST %d", testNumber));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(output.getOutput().stream().collect(Collectors.joining("\n")));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("VARMAP: TEST %d", testNumber));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(output.getVariableMap());
            System.out.println("-----------------------------------------------------------------------");
            testNumber++;
        }
    }
}
