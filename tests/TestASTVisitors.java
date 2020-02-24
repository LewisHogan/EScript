import escript.*;
import escript.ast.ASTBuilder;
import escript.ast.exceptions.ASTBuildException;
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

public class TestASTVisitors {

    public static final int PRETTY_TESTS = 6;
    public static final int EVALUATOR_TESTS = 5;
    public static final int PYTHON_TESTS = 5;

    private static List<ASTNode> prettyTrees;
    private static List<ASTNode> evaluationTrees;
    private static List<ASTNode> pythonTrees;

    @BeforeAll
    static void LoadASTsFromSourceFiles() {
        prettyTrees = new ArrayList<>();
        evaluationTrees = new ArrayList<>();
        pythonTrees = new ArrayList<>();

        for (int i = 0; i < PRETTY_TESTS; i++) {
            String sourceFilePath = String.format("tests/prettyprint/input_%d.txt", i);
            try {
                System.out.println(String.format("Adding test file tree from %s...", sourceFilePath));
                prettyTrees.add(ASTBuilder.createAST(CharStreams.fromFileName(sourceFilePath)));
            } catch (IOException | ASTBuildException err) {
                System.err.println("Unable to load test file tree!");
            }
        }

        for (int i = 0; i < EVALUATOR_TESTS; i++) {
            String sourceFilePath = String.format("tests/evaluator/input_%d.txt", i);
            try {
                System.out.println(String.format("Adding test file tree from %s...", sourceFilePath));
                evaluationTrees.add(ASTBuilder.createAST(CharStreams.fromFileName(sourceFilePath)));
            } catch (IOException | ASTBuildException err) {
                System.err.println("Unable to load test file tree!");
            }
        }

        for (int i = 0; i < PYTHON_TESTS; i++) {
            String sourceFilePath = String.format("tests/pythoncompiler/input_%d.txt", i);
            try {
                System.out.println(String.format("Adding test file tree from %s...", sourceFilePath));
                pythonTrees.add(ASTBuilder.createAST(CharStreams.fromFileName(sourceFilePath)));
            } catch (IOException | ASTBuildException err) {
                System.err.println("Unable to load test file tree!");
            }
        }
    }

    @Test
    void TestEvaluator() {
        EvaluatorVisitor evaluator = new EvaluatorVisitor();

        int testNumber = 0;
        EvaluationOutput output = null;
        for (ASTNode root : evaluationTrees) {
            try {
                output = (EvaluationOutput) evaluator.visit(root);
            } catch (InvalidIDException | InvalidOperationException | UndefinedVariableException err) {
                err.printStackTrace();
                Assertions.fail(err.getMessage());
            }

            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("OUTPUT: EVAL TEST %d", testNumber));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(output.getOutput().stream().collect(Collectors.joining("\n")));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("VARMAP: EVAL TEST %d", testNumber));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(output);
            System.out.println("-----------------------------------------------------------------------");
            testNumber++;
        }
    }

    @Test
    void TestPythonCompiler() {
        PythonCompilerVisitor compiler = new PythonCompilerVisitor();

        int testNumber = 0;
        String transpiledSource = "";
        for (ASTNode root : pythonTrees) {
            try {
                transpiledSource = compiler.visit(root);
            } catch (InvalidIDException | InvalidOperationException | UndefinedVariableException err) {
                err.printStackTrace();
                Assertions.fail(err.getMessage());
            }

            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("OUTPUT: COMP TEST %d", testNumber));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(transpiledSource);
            System.out.println("-----------------------------------------------------------------------");
            testNumber++;
        }
    }

    @Test
    void TestPrettyPrint() {
        PrettyPrintVisitor prettyprinter = new PrettyPrintVisitor();

        int testNumber = 0;
        String formattedSource = "";
        for (ASTNode root : prettyTrees) {
            ASTNode formattedRoot = null;
            try {
                formattedSource = prettyprinter.visit(root);
                formattedRoot = ASTBuilder.createAST(CharStreams.fromString(formattedSource));
            } catch (InvalidIDException | InvalidOperationException | UndefinedVariableException | ASTBuildException err) {
                err.printStackTrace();
                Assertions.fail(err.getMessage());
            }

            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("OUTPUT: PRET TEST %d", testNumber));
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