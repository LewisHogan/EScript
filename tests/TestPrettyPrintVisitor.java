import escript.ast.ASTBuilder;
import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import escript.escriptLexer;
import escript.escriptParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestPrettyPrintVisitor {

    public static final int PRETTY_PRINT_TESTS = 5;

    private static List<ASTNode> trees;

    static ASTNode createAST(CharStream sourceCode) {
        escriptLexer lexer = new escriptLexer(sourceCode);
        escriptParser parser = new escriptParser(new CommonTokenStream(lexer));
        return (ASTNode) new ASTBuilder().visit(parser.start());
    }

    @BeforeAll
    static void GenerateASTsFromSourceFiles() {
        trees = new ArrayList<>();
        for (int i = 0; i < PRETTY_PRINT_TESTS; i++) {
            String fileToLoad = String.format("tests/prettyprint/input_%d.txt", i);
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
        PrettyPrintVisitor prettyprinter = new PrettyPrintVisitor();

        int testNumber = 0;
        String formattedSource = "";
        for (ASTNode root : trees) {
            ASTNode formattedRoot = null;
            try {
                formattedSource = prettyprinter.visit(root);
                formattedRoot = createAST(CharStreams.fromString(formattedSource));
            } catch (InvalidIDException | InvalidOperationException | UndefinedVariableException err) {
                err.printStackTrace();
                Assertions.fail(err.getMessage());
            }

            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("OUTPUT: %d", testNumber));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(formattedSource);
            System.out.println("-----------------------------------------------------------------------");
            System.out.printf("Checking test %s output matches original input in tree...", testNumber);
            Assertions.assertEquals(root.toStringTree(), formattedRoot.toStringTree());
            System.out.println("PASSED");
            testNumber++;
        }
    }
}
