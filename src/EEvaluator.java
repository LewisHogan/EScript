import escript.EvaluationOutput;
import escript.EvaluatorVisitor;
import escript.PythonCompilerVisitor;
import escript.ast.ASTBuilder;
import escript.ast.exceptions.ASTBuildException;
import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import org.antlr.v4.runtime.CharStreams;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Standalone program that allows running EScript code on the command line.
 */
public class EEvaluator {

    private static String compile(String inputFile) {
        try {
            ASTNode root = ASTBuilder.createAST(CharStreams.fromFileName(inputFile));
            try {
                EvaluationOutput output = (EvaluationOutput) new EvaluatorVisitor().visit(root);
                return output.getOutput().stream().collect(Collectors.joining("\n"));
            } catch (InvalidOperationException | UndefinedVariableException | InvalidIDException e) {
                e.printStackTrace();
            }
        } catch (IOException | ASTBuildException err) {
            err.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println(compile(args[0]));
            return;
        }

        if (args.length == 2) {
            // Need to specify UTF8 otherwise characters like the £ symbol don't seem to be encoded correctly?
            // Note: this does not fix piping output, but it works as long as the programmer sticks to ASCII code.
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                writer.write(compile(args[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("USAGE: EEvaluator input_file [output_file]");
    }
}
