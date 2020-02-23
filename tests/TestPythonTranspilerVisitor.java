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

public class TestPythonTranspilerVisitor {

    public static final int PYTHON_TESTS = 5;

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
        for (int i = 0; i < PYTHON_TESTS; i++) {
            String fileToLoad = String.format("tests/pythoncompiler/input_%d.txt", i);
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
        PythonTranspilerVisitor transpiler = new PythonTranspilerVisitor();

        int testNumber = 0;
        String transpiledSource = "";
        for (ASTNode root : trees) {
            try {
                transpiledSource = transpiler.visit(root);
            } catch (InvalidIDException | InvalidOperationException | UndefinedVariableException err) {
                err.printStackTrace();
                Assertions.fail(err.getMessage());
            }

            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("OUTPUT: %d", testNumber));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(transpiledSource);
            System.out.println("-----------------------------------------------------------------------");
            testNumber++;
        }
    }
}
