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
        String sourceCode = "a=3>2;if (a > 5) {mstg=2;} else if (b == c) {if (b == b) name=a;lARR=\"ha\";} else foo=true;";

        System.out.println("INPUT : " + sourceCode);
        ASTNode assignmentStatement = createAST(sourceCode);
//        System.out.println("OUTPUT: " + new PrettyPrintASTVisitor().visit(assignmentStatement));


        // The output we produce should allow use to recreate the output using itself as input
//        System.out.println(
//                assignmentStatement.toStringTree().equals(
//                        createAST(new PrettyPrintASTVisitor().visit(assignmentStatement)).toStringTree())
//        );


        System.out.println("ORIGINAL TREE: " + assignmentStatement.toStringTree());
//        System.out.println("REMADE TREE  : " + createAST(new PrettyPrintASTVisitor().visit(assignmentStatement)).toStringTree());

        // Random GUI stuff, TODO: Cleanup
        JFrame frame = new JFrame("PrettyPrintVisitor Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(""), assignmentStatement);
        treeViewer.setScale(1.5f);
        frame.add(treeViewer, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(500, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
