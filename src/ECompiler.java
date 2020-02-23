import escript.PythonTranspilerVisitor;
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

public class ECompiler {

    private static ASTNode createAST(CharStream source) {
        escriptLexer lexer = new escriptLexer(source);
        escriptParser parser = new escriptParser(new CommonTokenStream(lexer));
        return new ASTBuilder().visit(parser.start());
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("USAGE: ECompiler <input_file> <output_file>");
            return;
        }

        String inputFilename = args[0];
        String outputFilename = args[1];

        try {
            ASTNode root = createAST(CharStreams.fromFileName(inputFilename));
            try {
                String pythonCode = new PythonTranspilerVisitor().visit(root);
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename)))) {
                    writer.write(pythonCode);
                } catch (IOException err) {
                    System.err.println(err.getMessage());
                }
            } catch (InvalidOperationException e) {
                e.printStackTrace();
            } catch (UndefinedVariableException e) {
                e.printStackTrace();
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
        } catch (IOException err) {
            System.err.println(err.getMessage());
        }
    }
}
