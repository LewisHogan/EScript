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
import org.junit.jupiter.api.Test;

public class TestPythonCompiler {

    static ASTNode createAST(CharStream sourceCode) {
        escriptLexer lexer = new escriptLexer(sourceCode);
        escriptParser parser = new escriptParser(new CommonTokenStream(lexer));
        return (ASTNode) new ASTBuilder().visit(parser.start());
    }

    @Test
    public void TestGenPythonCode() {
        String source = "i = 10;\n" +
                "name = \"lewis\";\n" +
                "for i = 2; i < 5; i = i + 2 {\n" +
                "\tprint(\"test\");\n" +
                "}";
        CharStream sourceCode = CharStreams.fromString(source);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("BEFORE");
        System.out.println("-------------------------------------------------------------------");
        System.out.println(source);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("OUTPUT");
        System.out.println("-------------------------------------------------------------------");
        try {
            System.out.println(createAST(sourceCode).toStringTree());
            System.out.println(new PythonVisitor().visit(createAST(sourceCode)));
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        } catch (UndefinedVariableException e) {
            e.printStackTrace();
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------------------------------------------------");
    }
}
