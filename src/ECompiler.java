import escript.PythonCompilerVisitor;
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

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ECompiler {

    private static ASTNode createAST(CharStream source) {
        escriptLexer lexer = new escriptLexer(source);
        escriptParser parser = new escriptParser(new CommonTokenStream(lexer));
        return new ASTBuilder().visit(parser.start());
    }

    private static String compile(String inputFile) {
        try {
            ASTNode root = createAST(CharStreams.fromFileName(inputFile));
            try {
                return new PythonCompilerVisitor().visit(root);
            } catch (InvalidOperationException | UndefinedVariableException | InvalidIDException e) {
                e.printStackTrace();
            }
        } catch (IOException err) {
            System.err.println(err.getMessage());
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
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                writer.write(compile(args[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("USAGE: ECompiler input_file [output_file]");
        return;
    }
}