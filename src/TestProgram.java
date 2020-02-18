import expressionscript.PrettyPrintVisitor;
import expressionscript.ReversePolishMaker;
import expressionscript.ast.ASTBuilder;
import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.expression.EExpressionOperator;
import expressionscript.ast.nodes.expression.ExpressionNode;
import expressionscript.escriptLexer;
import expressionscript.escriptParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TestProgram {

    public static ASTNode createAST(String source) {

        CharStream sourceCharStream = CharStreams.fromString(source);
        escriptLexer lexer = new escriptLexer(sourceCharStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        escriptParser parser = new escriptParser(commonTokenStream);

        ASTBuilder builder = new ASTBuilder();

        return new ASTBuilder().visit(parser.start());
    }

    public static void main(String[] args) {
//        String sourceCode = "a=4-2;foo=\"bar  \"+3;apple=a * 3 / -(2+1);banana=\"hello world\";z=a>2;";
//        String sourceCode = "a=3>2;b=a+2;c=b*a;a>b;";
//        String sourceCode = "a=3>2;if (a > 5) {mstg=2;} else if (b == c) {if (b == b) name=a;lARR=\"ha\";} else foo=true;";
        String sourceCode = "name = \"lh16674\";age = 22;\n" +
                "    money = 22 * 1000 - 9250;\n" +
                "is_bankrupt = money       <\n" +
                " 0;\n" +
                "\n" +
                " if (is_bankrupt == true)\n" +
                "    age = age + 50;\n" +
                "    else if (100 > 50){\n" +
                "        money = money * 10;\n" +
                "        money = money + 1;}\n" +
                "  else\n" +
                "    money = 9999;";
//        String sourceCode = "result=4+5-7;";

        System.out.println("INPUT : " + sourceCode);
        ASTNode assignmentStatement = createAST(sourceCode);

        String output = new PrettyPrintVisitor().visit((ASTNode) assignmentStatement);
        System.out.println("OUTPUT: " + output);

        // The output we produce should allow use to recreate the output using itself as input
//        System.out.println(
//                assignmentStatement.toStringTree().equals(
//                        createAST(new PrettyPrintASTVisitor().visit(assignmentStatement)).toStringTree())
//        );


        System.out.println("ORIGINAL TREE: " + assignmentStatement.toStringTree());
        System.out.println("REMADE TREE  : " + createAST(output).toStringTree());
        System.out.println("TREE MATCHES : " + assignmentStatement.toStringTree().equals(createAST(output).toStringTree()));

        // Random GUI stuff, TODO: Cleanup
        JFrame frame = new JFrame("PrettyPrintVisitor Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(""), assignmentStatement);
        frame.setMinimumSize(new Dimension(300, 200));
        frame.getContentPane().add(treeViewer, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
